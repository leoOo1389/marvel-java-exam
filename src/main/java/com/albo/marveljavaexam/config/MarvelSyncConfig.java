package com.albo.marveljavaexam.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties("marvelsyncconfig")
public class MarvelSyncConfig {

    private int pageSize;
    private List<String> defaultCharacters;
}
