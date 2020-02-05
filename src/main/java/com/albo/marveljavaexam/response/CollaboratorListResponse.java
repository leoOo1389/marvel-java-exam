package com.albo.marveljavaexam.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class CollaboratorListResponse {

    private Date lastSync;

    private List<CollaboratorResponse> editors;

    private List<CollaboratorResponse> writers;

    private List<CollaboratorResponse> colorists;
}
