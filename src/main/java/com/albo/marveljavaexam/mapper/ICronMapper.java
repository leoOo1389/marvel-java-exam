package com.albo.marveljavaexam.mapper;

import com.albo.marveljavaexam.domain.document.CronTask;
import com.albo.marveljavaexam.domain.document.SyncCharacterDetail;
import com.albo.marveljavaexam.dto.CronTaskDTO;
import com.albo.marveljavaexam.dto.SyncCharacterDetailDTO;
import com.albo.marveljavaexam.response.CronTaskResponse;
import com.albo.marveljavaexam.response.SyncCharacterDetailResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ICronMapper {

    List<CronTaskDTO> cronTaskListToCronTaskDTOList(List<CronTask> cronTaskList);

    CronTaskDTO cronTaskToCronTaskDTO(CronTask cronTaskList);

    SyncCharacterDetailDTO syncCharacterDetailToSyncCharacterDetailDTO(SyncCharacterDetail syncCharacterDetail);

    List<CronTaskResponse> cronTaskDTOResponseListToCronTaskResponseList(List<CronTaskDTO> cronTaskDTOList);

    CronTaskResponse cronTaskDTOToCronTaskResponse(CronTaskDTO cronTaskDTO);

    SyncCharacterDetailResponse syncCharacterDetailDTOToSyncCharacterDetailResponse(SyncCharacterDetailDTO syncCharacterDetailDTO);
}
