package com.albo.marveljavaexam.domain.document;

import com.albo.marveljavaexam.enums.CronStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CronTask {

    @Id
    private String id;

    private CronStatusEnum status;

    private Date startDate;

    private Date endDate;

    private List<SyncCharacterDetail> details;
}
