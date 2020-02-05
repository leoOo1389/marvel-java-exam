package com.albo.marveljavaexam.repository;

import com.albo.marveljavaexam.domain.document.CronTask;
import com.albo.marveljavaexam.enums.CronStatusEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CronTaskRepository extends MongoRepository<CronTask, String> {

    List<CronTask> findByStatus(CronStatusEnum status);
}
