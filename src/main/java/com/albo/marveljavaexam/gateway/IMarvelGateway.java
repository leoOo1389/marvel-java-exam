package com.albo.marveljavaexam.gateway;

import com.albo.marveljavaexam.dto.CharacterDTO;
import com.albo.marveljavaexam.dto.ComicDTO;

import java.util.List;

public interface IMarvelGateway {

    CharacterDTO getCharacter(String name);

    List<ComicDTO> getCharacterComics(int characterId, int limit, int offset);
}
