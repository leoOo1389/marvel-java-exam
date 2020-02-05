package com.albo.marveljavaexam.gateway.impl;

import com.albo.marveljavaexam.dto.CharacterDTO;
import com.albo.marveljavaexam.dto.ComicDTO;
import com.albo.marveljavaexam.gateway.IMarvelGateway;
import com.albo.marveljavaexam.mapper.IMarvelMapper;
import com.albo.marveljavaexam.rest.client.response.ComicRSClientResponse;
import com.albo.marveljavaexam.rest.client.response.ResultCharactersRSClientResponse;
import com.albo.marveljavaexam.rest.client.response.ResultComicsRSClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class MarvelGateway implements IMarvelGateway {

    private final static int LIMIT_TO_CHARACTERS_BY_NAME = 1;
    private final Logger logger = LoggerFactory.getLogger(MarvelGateway.class);
    private final RestTemplate restTemplate;
    private final IMarvelMapper mapper;

    @Value(value = "${marvel-api.characters-path}")
    private String CHARACTERS_ENDPOINT;

    @Value(value = "${marvel-api.comics-path}")
    private String COMICS_ENDPOINT;

    public MarvelGateway(RestTemplate restTemplate, IMarvelMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    @Override
    public CharacterDTO getCharacter(String name) {
        logger.info("getCharacter ", name);
        HttpHeaders headers = new HttpHeaders();
        final HttpEntity<String> entity = new HttpEntity<String>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(CHARACTERS_ENDPOINT)
                .queryParam("name", name)
                .queryParam("limit", LIMIT_TO_CHARACTERS_BY_NAME);
        URI uri = URI.create(builder.toUriString());
        ResponseEntity<ResultCharactersRSClientResponse> result = restTemplate.exchange(uri, HttpMethod.GET, entity, ResultCharactersRSClientResponse.class);
        List<CharacterDTO> characterDTOList = mapper.characterRSClientResponseListToCharacterDTOList((result.getBody().getData().getResults()));
        if (characterDTOList.size() < 1) {
            // TODO Throw exception
        }
        return characterDTOList.get(0);
    }

    @Override
    public List<ComicDTO> getCharacterComics(int characterId, int limit, int offset) {
        logger.info("getCharacterComics ", characterId);
        HttpHeaders headers = new HttpHeaders();
        final HttpEntity<String> entity = new HttpEntity<String>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(COMICS_ENDPOINT)
                .queryParam("characters", characterId)
                .queryParam("limit", limit)
                .queryParam("offset", offset);
        ResponseEntity<ResultComicsRSClientResponse> result = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, ResultComicsRSClientResponse.class);
        List<ComicRSClientResponse> comicRSClientResponseList = result.getBody().getData().getResults();
        List<ComicDTO> comicDTOList = mapper.comicRSClientResponseListToComicDTOList(comicRSClientResponseList);
        return comicDTOList;
    }
}
