package com.albo.marveljavaexam.service;

import com.albo.marveljavaexam.dto.CronTaskDTO;

import java.util.List;

public interface IMarvelService {

    List<CronTaskDTO> status();

    String createCronTask();

    void syncComics();
}
