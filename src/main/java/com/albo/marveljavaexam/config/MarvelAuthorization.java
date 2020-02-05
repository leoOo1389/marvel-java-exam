package com.albo.marveljavaexam.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarvelAuthorization {

    private String ts;

    private String apiKey;

    private String hash;
}
