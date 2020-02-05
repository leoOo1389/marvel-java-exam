package com.albo.marveljavaexam.controller.impl;

import com.albo.marveljavaexam.controller.ISyncCronController;
import com.albo.marveljavaexam.mapper.ICronMapper;
import com.albo.marveljavaexam.response.CronTaskResponse;
import com.albo.marveljavaexam.service.IMarvelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crons")
public class SyncCronController implements ISyncCronController {

    private final IMarvelService service;
    private final ICronMapper cronMapper;

    public SyncCronController(IMarvelService service, ICronMapper cronMapper) {
        this.service = service;
        this.cronMapper = cronMapper;
    }

    @Override
    @GetMapping(value = "/status")
    public List<CronTaskResponse> status() {
        return cronMapper.cronTaskDTOResponseListToCronTaskResponseList(service.status());
    }

    @Override
    @PostMapping
    public String createCron() {
        return service.createCronTask();
    }
}
