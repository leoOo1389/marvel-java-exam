package com.albo.marveljavaexam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDTO {

    private int id;

    private String name;

    private int totalAvailableComics;

    private List<CreatorDTO> editors;

    private List<CreatorDTO> writers;

    private List<CreatorDTO> colorists;

    private List<CharacterDTO> characters;

    private List<String> comics;

    private Date lastSync;
}
