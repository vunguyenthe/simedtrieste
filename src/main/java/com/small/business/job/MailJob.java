package com.small.business.job;

import org.quartz.Job;

import org.quartz.JobExecutionContext;

import org.quartz.JobExecutionException;


public class MailJob implements Job{

    public void execute(JobExecutionContext context) throws JobExecutionException {
    		System.out.println("=========job is running...========");
        }

}
