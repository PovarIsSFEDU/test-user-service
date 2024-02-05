package com.plukash.testuserservice.Jobs;

import com.plukash.testuserservice.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@RequiredArgsConstructor
public class CashLoadJob implements Job {
    private final AccountService service;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        service.updateBalance();
    }
}
