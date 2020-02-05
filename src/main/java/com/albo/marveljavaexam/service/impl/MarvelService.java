package com.albo.marveljavaexam.service.impl;

import com.albo.marveljavaexam.config.MarvelSyncConfig;
import com.albo.marveljavaexam.domain.document.AssociateCharacter;
import com.albo.marveljavaexam.domain.document.AssociateCreator;
import com.albo.marveljavaexam.domain.document.Character;
import com.albo.marveljavaexam.domain.document.Creator;
import com.albo.marveljavaexam.domain.document.CronTask;
import com.albo.marveljavaexam.domain.document.SyncCharacterDetail;
import com.albo.marveljavaexam.dto.CharacterDTO;
import com.albo.marveljavaexam.dto.ComicDTO;
import com.albo.marveljavaexam.dto.CronTaskDTO;
import com.albo.marveljavaexam.enums.CronStatusEnum;
import com.albo.marveljavaexam.exceptions.CronTaskException;
import com.albo.marveljavaexam.gateway.IMarvelGateway;
import com.albo.marveljavaexam.mapper.ICronMapper;
import com.albo.marveljavaexam.mapper.IMarvelMapper;
import com.albo.marveljavaexam.repository.AssociateCharacterRepository;
import com.albo.marveljavaexam.repository.AssociateCreatorRepository;
import com.albo.marveljavaexam.repository.CharacterRepository;
import com.albo.marveljavaexam.repository.ComicRepository;
import com.albo.marveljavaexam.repository.CronTaskRepository;
import com.albo.marveljavaexam.service.IMarvelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class MarvelService implements IMarvelService {

    private final static String COLORIST_ROLE = "colorist";
    private final static String EDITOR_ROLE = "editor";
    private final static String WRITER_ROLE = "writer";
    private final Logger logger = LoggerFactory.getLogger(MarvelService.class);
    private final IMarvelGateway marvelGateway;
    private final CharacterRepository characterRepository;
    private final ComicRepository comicRepository;
    private final AssociateCreatorRepository associateCreatorRepository;
    private final AssociateCharacterRepository associateCharacterRepository;
    private final IMarvelMapper mapper;
    private final MarvelSyncConfig marvelSyncConfig;
    private final CronTaskRepository cronRepository;
    private final ICronMapper cronMapper;

    public MarvelService(IMarvelGateway marvelGateway, CharacterRepository characterRepository, ComicRepository comicRepository, AssociateCreatorRepository associateCreatorRepository, AssociateCharacterRepository associateCharacterRepository, IMarvelMapper mapper, MarvelSyncConfig marvelSyncConfig, CronTaskRepository cronRepository, ICronMapper cronMapper) {
        this.marvelGateway = marvelGateway;
        this.characterRepository = characterRepository;
        this.comicRepository = comicRepository;
        this.associateCreatorRepository = associateCreatorRepository;
        this.associateCharacterRepository = associateCharacterRepository;
        this.mapper = mapper;
        this.marvelSyncConfig = marvelSyncConfig;
        this.cronRepository = cronRepository;
        this.cronMapper = cronMapper;
    }

    @Override
    public List<CronTaskDTO> status() {
        List<CronTask> pendingCrons = cronRepository.findAll();
        return cronMapper.cronTaskListToCronTaskDTOList(pendingCrons.stream().limit(5).collect(Collectors.toList()));
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void syncComics() {
        try {
            createCronTask();
        } catch (Exception e) {
            logger.error("Error al ejecutar el cron", e);
        }
    }

    @Override
    public String createCronTask() {
        logger.info((new Date()).toString());
        List<CronTask> pendingCrons = cronRepository.findByStatus(CronStatusEnum.IN_PROGRESS);
        if (pendingCrons.size() == 0) {
            CronTask newCronTask = CronTask.builder()
                    .id(UUID.randomUUID().toString())
                    .status(CronStatusEnum.IN_PROGRESS)
                    .startDate(new Date())
                    .details(new ArrayList<>())
                    .build();
            try {
                cronRepository.save(newCronTask);
                logger.info("Start cron " + newCronTask.getId());
                List<Character> characters = characterRepository.findAll();
                List<CharacterDTO> characterDTOList = mapper.characterListToCharacterDTOList(characters);
                if (characterDTOList.isEmpty()) {
                    logger.info("User default characters ", marvelSyncConfig.getDefaultCharacters().toString());
                    for (int initDefault = 0; initDefault < marvelSyncConfig.getDefaultCharacters().size(); initDefault++) {
                        characterDTOList.add(CharacterDTO.builder().name(marvelSyncConfig.getDefaultCharacters().get(initDefault)).build());
                    }
                }
                characterDTOList.forEach(c -> {
                    SyncCharacterDetail cronDetail = syncMarvelCharacterComics(c);
                    newCronTask.getDetails().add(cronDetail);
                });
            } catch (Exception e) {
                logger.error("Error", e);
            } finally {
                newCronTask.setEndDate(new Date());
                newCronTask.setStatus(CronStatusEnum.SUCCESS_FINISHED);
                cronRepository.save(newCronTask);
                return newCronTask.getId();
            }
        } else {
            logger.error("There is a cron in progress. Check the status and try later");
            throw new CronTaskException("There is a cron in progress. Check the status and try later");
        }
    }

    private SyncCharacterDetail syncMarvelCharacterComics(CharacterDTO characterDTO) {
        logger.info("Start sync character process to " + characterDTO.getName());
        Date startDate = new Date();
        // Obtain characater info from Marvel
        characterDTO = marvelGateway.getCharacter(characterDTO.getName());
        Character character = mapper.characterDTOToCharacter(characterDTO);

        // Set total of pages to call the API Marvel
        int totalPages = Math.round(characterDTO.getTotalAvailableComics() / marvelSyncConfig.getPageSize());
        logger.info("Total calls needed " + totalPages);
        int offset = 0;
        int totalSynComics = 0;

        // Clean temporal data of associated creators and characters
        associateCreatorRepository.deleteByCharacterId(character.getId());
        associateCharacterRepository.deleteByCharacterId(character.getId());
        for (int page = 0; page <= totalPages; page++) {
            offset = page * marvelSyncConfig.getPageSize();
            List<ComicDTO> comics = marvelGateway.getCharacterComics(character.getId(), marvelSyncConfig.getPageSize(), offset);
            // Save sync comics
            comicRepository.saveAll(mapper.comicDTOListToComicList(comics));

            // Save temporal auxiliar info of asssociated creators and characters
            comics.stream().forEach(c -> {
                List<AssociateCreator> creators = mapper.creatorDTOListToAssociateCreatorList(c.getCreators());
                creators.stream().forEach(cr -> cr.setCharacterId(character.getId()));
                associateCreatorRepository.saveAll(creators);
                List<AssociateCharacter> characters = mapper.characterDTOListToAssociateCharacterList(c.getCharacters());
                characters.stream().forEach(cr -> {
                    cr.setCharacterId(character.getId());
                    cr.setComicName(c.getName());
                });
                associateCharacterRepository.saveAll(characters);
            });
            totalSynComics += comics.size();
        }
        logger.info("Calls to API Marvel finished");

        setCreatorsInfo(character);
        setCharactersInfo(character);

        character.setLastSync(new Date());
        characterRepository.save(character);

        Date endDate = new Date();
        logger.info("Sync process finished " + characterDTO.getName());
        logger.info("FINISHED TIME IN SECONDS " + getDifferenceInSecondsBetweenTwoDates(startDate, endDate));
        return SyncCharacterDetail.builder()
                .characterName(characterDTO.getName())
                .comicsAvailable(characterDTO.getTotalAvailableComics())
                .comicsSync(totalSynComics)
                .build();
    }

    private void setCreatorsInfo(Character character) {
        logger.info("updateCreatorsInfo " + character.getName());
        List<AssociateCreator> associateCreators = associateCreatorRepository.findByCharacterId(character.getId());

        // Obtain info of colorists
        Map<String, Long> colorists = associateCreators.stream().filter(c -> c.getRole().toUpperCase().equalsIgnoreCase(COLORIST_ROLE))
                .collect(groupingBy(AssociateCreator::getName, counting()));
        character.setColorists(colorists.entrySet().stream()
                .map(e -> Creator.builder().name(e.getKey()).numberOfComicsCollaborations(e.getValue()).role(COLORIST_ROLE).build())
                .collect(Collectors.toList()));

        // Obtain info of writers
        Map<String, Long> writers = associateCreators.stream().filter(c -> c.getRole().toUpperCase().equalsIgnoreCase(WRITER_ROLE))
                .collect(groupingBy(AssociateCreator::getName, counting()));
        character.setWriters(writers.entrySet().stream()
                .map(e -> Creator.builder().name(e.getKey()).numberOfComicsCollaborations(e.getValue()).role(WRITER_ROLE).build())
                .collect(Collectors.toList()));

        // Obtain info of editors
        Map<String, Long> editors = associateCreators.stream().filter(c -> c.getRole().toLowerCase().equalsIgnoreCase(EDITOR_ROLE))
                .collect(groupingBy(AssociateCreator::getName, counting()));
        character.setEditors(editors.entrySet().stream()
                .map(e -> Creator.builder().name(e.getKey()).numberOfComicsCollaborations(e.getValue()).role(EDITOR_ROLE).build())
                .collect(Collectors.toList()));
    }

    private void setCharactersInfo(Character character) {
        logger.info("updateCharactersInfo " + character.getName());
        List<AssociateCharacter> associateCharacters = associateCharacterRepository.findByCharacterId(character.getId());

        Map<String, List<AssociateCharacter>> characters = associateCharacters.stream().filter(c -> !c.getName().toUpperCase().equalsIgnoreCase(character.getName().toUpperCase()))
                .collect(groupingBy(AssociateCharacter::getName));
        character.setCharacters(characters.entrySet().stream()
                .map(e -> Character.builder().name(e.getKey()).comics(e.getValue().stream().map(AssociateCharacter::getComicName).collect(Collectors.toList())).build())
                .collect(Collectors.toList()));
    }

    private int getDifferenceInSecondsBetweenTwoDates(Date startDate, Date endDate) {
        long diffTime = endDate.getTime() - startDate.getTime();
        return (int) TimeUnit.SECONDS.convert(diffTime, TimeUnit.MILLISECONDS);
    }
}
