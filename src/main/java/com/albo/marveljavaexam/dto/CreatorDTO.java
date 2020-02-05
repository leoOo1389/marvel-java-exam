package com.albo.marveljavaexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatorDTO {

    private String name;

    private String role;

    private long numberOfComicsCollaborations;
}
