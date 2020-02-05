package com.albo.marveljavaexam.domain.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "characters")
public class Character {

    @Id
    private int id;

    private String name;

    private int totalAvailableComics;

    private List<Creator> editors;

    private List<Creator> writers;

    private List<Creator> colorists;

    private List<Character> characters;

    private List<String> comics;

    private Date lastSync;
}
