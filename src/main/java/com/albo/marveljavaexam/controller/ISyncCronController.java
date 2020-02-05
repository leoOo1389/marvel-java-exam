package com.albo.marveljavaexam.controller;

import com.albo.marveljavaexam.response.CronTaskResponse;

import java.util.List;

public interface ISyncCronController {

    List<CronTaskResponse> status();

    String createCron();

}
