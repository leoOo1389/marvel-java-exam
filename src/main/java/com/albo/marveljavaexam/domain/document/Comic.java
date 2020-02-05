package com.albo.marveljavaexam.domain.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comics")
public class Comic {

    @Id
    private int id;

    private List<Creator> creators;

    private List<Character> characters;
}
