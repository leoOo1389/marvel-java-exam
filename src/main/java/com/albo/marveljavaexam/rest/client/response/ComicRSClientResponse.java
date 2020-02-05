package com.albo.marveljavaexam.rest.client.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class ComicRSClientResponse {

    private int id;

    private String title;

    private ResultListItemRSClientResponse<CreatorRSClientResponse> creators;

    private ResultListItemRSClientResponse<CharacterRSClientResponse> characters;

}
