package com.hateapple.myspringboot.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;


public class MyJob02 implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("定时任务  my job02 执行了：" + new Date());
    }
}
