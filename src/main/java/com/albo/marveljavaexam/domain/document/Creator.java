package com.albo.marveljavaexam.domain.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Creator {

    private String name;

    private String role;

    private long numberOfComicsCollaborations;
}
