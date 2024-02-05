package com.plukash.testuserservice.Jobs;

import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class QuartzTask {
    private final Scheduler scheduler;

    public void scheduleTask() throws SchedulerException {
        JobDetail job = JobBuilder.newJob(CashLoadJob.class)
                .withIdentity("CashLoad", "group1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("CashTrigger", "group1")
//                .startNow()
                .startAt(Date.from(LocalDateTime.now(ZoneId.systemDefault()).plusSeconds(10).atZone(ZoneId.systemDefault()).toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(30)
                        .repeatForever())
                .build();

        scheduler.scheduleJob(job, trigger);
    }
}

