package com.albo.marveljavaexam.response;

import com.albo.marveljavaexam.enums.CronStatusEnum;
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
public class CronTaskResponse {

    private String id;

    private CronStatusEnum status;

    private Date startDate;

    private Date endDate;

    private List<SyncCharacterDetailResponse> details;
}
