package com.albo.marveljavaexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SyncCharacterDetailDTO {

    private String characterName;

    private int comicsAvailable;

    private int comicsSync;
}
