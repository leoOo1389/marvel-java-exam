package com.albo.marveljavaexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComicDTO {

    private int id;

    private String name;

    private List<CreatorDTO> creators;

    private List<CharacterDTO> characters;
}
