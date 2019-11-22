package com.example.todoservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableAsync
@Component
public class SchedulerService {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    @Async
    @Scheduled(fixedDelayString = "${app.tasks.execution-delay}")
    public void fixedDelayTask() {
        logger.debug("Fixed delay task - " + System.currentTimeMillis() / 1000);
    }
}
