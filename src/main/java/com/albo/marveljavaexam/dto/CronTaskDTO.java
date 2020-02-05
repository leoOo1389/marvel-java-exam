package com.albo.marveljavaexam.dto;

import com.albo.marveljavaexam.enums.CronStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CronTaskDTO {

    private String id;

    private CronStatusEnum status;

    private Date startDate;

    private Date endDate;

    private List<SyncCharacterDetailDTO> details;
}
