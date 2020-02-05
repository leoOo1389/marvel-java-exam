package com.albo.marveljavaexam.domain.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SyncCharacterDetail {

    private String characterName;

    private int comicsAvailable;

    private int comicsSync;

}
