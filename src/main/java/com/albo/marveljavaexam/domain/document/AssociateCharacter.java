package com.albo.marveljavaexam.domain.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "associate_characters")
public class AssociateCharacter {

    private int characterId;

    private String name;

    private String comicName;

}
