package com.albo.marveljavaexam.service.impl;

import com.albo.marveljavaexam.domain.document.Character;
import com.albo.marveljavaexam.dto.CharacterDTO;
import com.albo.marveljavaexam.exceptions.APIException;
import com.albo.marveljavaexam.mapper.IMarvelMapper;
import com.albo.marveljavaexam.repository.CharacterRepository;
import com.albo.marveljavaexam.service.ICollaboratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollaboratorService implements ICollaboratorService {

    private final Logger logger = LoggerFactory.getLogger(MarvelService.class);
    private final CharacterRepository characterRepository;
    private final IMarvelMapper mapper;

    public CollaboratorService(CharacterRepository characterRepository, IMarvelMapper mapper) {
        this.characterRepository = characterRepository;
        this.mapper = mapper;
    }

    public CharacterDTO getCollaborator(String name) {
        logger.info("getCollaborator" + name);
        Character character = characterRepository.findByName(name);
        if (character == null) {
            throw new APIException("Character not found");
        }
        return mapper.characterToCharacterDTO(character);
    }

    public List<CharacterDTO> getCollaborators() {
        logger.info("getCollaborators");
        List<Character> characters = characterRepository.findAll();
        return mapper.characterListToCharacterDTOList(characters);
    }
}
