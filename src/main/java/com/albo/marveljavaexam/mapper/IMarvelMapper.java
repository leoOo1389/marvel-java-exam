package com.albo.marveljavaexam.mapper;

import com.albo.marveljavaexam.domain.document.AssociateCharacter;
import com.albo.marveljavaexam.domain.document.AssociateCreator;
import com.albo.marveljavaexam.domain.document.Character;
import com.albo.marveljavaexam.domain.document.Comic;
import com.albo.marveljavaexam.dto.CharacterDTO;
import com.albo.marveljavaexam.dto.ComicDTO;
import com.albo.marveljavaexam.dto.CreatorDTO;
import com.albo.marveljavaexam.rest.client.response.CharacterRSClientResponse;
import com.albo.marveljavaexam.rest.client.response.ComicRSClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface IMarvelMapper {

    List<CharacterDTO> characterRSClientResponseListToCharacterDTOList(List<CharacterRSClientResponse> characterRSClientResponseList);

    List<CharacterDTO> characterListToCharacterDTOList(List<Character> characterList);

    @Mapping(source = "comics.available", target = "totalAvailableComics")
    @Mapping(target = "comics", ignore = true)
    CharacterDTO characterRSClientResponseToCharacterDTO(CharacterRSClientResponse characterRSClientResponse);

    Character characterDTOToCharacter(CharacterDTO characterDTO);

    CharacterDTO characterToCharacterDTO(Character character);

    List<ComicDTO> comicRSClientResponseListToComicDTOList(List<ComicRSClientResponse> comicRSClientResponseList);

    @Mapping(source = "creators.items", target = "creators")
    @Mapping(source = "characters.items", target = "characters")
    @Mapping(source = "title", target = "name")
    ComicDTO comicRSClientResponseToComicDTO(ComicRSClientResponse comicRSClientResponse);

    List<Comic> comicDTOListToComicList(List<ComicDTO> comicDTO);

    Comic comicDTOToComic(ComicDTO comicDTO);

    List<AssociateCreator> creatorDTOListToAssociateCreatorList(List<CreatorDTO> creatorDTOList);

    AssociateCreator creatorDTOToAssociateCreator(CreatorDTO creatorDTO);

    List<AssociateCharacter> characterDTOListToAssociateCharacterList(List<CharacterDTO> characterDTOList);

    AssociateCharacter characterDTOToAssociateCharacter(CharacterDTO characterDTO);

}
