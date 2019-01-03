package com.hateapple.myspringboot.quartz;


import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Component
public class JobManager {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    public void pauseJob(String jobName) {
        if (StringUtils.isEmpty(jobName)) {
            try {
                schedulerFactoryBean.getScheduler().pauseAll();
                System.out.println("所有任务已暂停");

            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        } else {
            JobKey jobKey = JobKey.jobKey(jobName, jobName);
            try {
                schedulerFactoryBean.getScheduler().pauseJob(jobKey);
                System.out.println(jobName + "已暂停");
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }

    public void restartJob(String jobName) {
        if (StringUtils.isEmpty(jobName)) {
            try {
                schedulerFactoryBean.getScheduler().resumeAll();
                System.out.println("所有重新启动");

            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        } else {
            JobKey jobKey = JobKey.jobKey(jobName, jobName);
            try {
                schedulerFactoryBean.getScheduler().resumeJob(jobKey);
                System.out.println(jobName + "重新启动");
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteJob(String jobName) {

        JobKey jobKey = JobKey.jobKey(jobName, jobName);
        try {
            schedulerFactoryBean.getScheduler().pauseJob(jobKey);
            schedulerFactoryBean.getScheduler().deleteJob(jobKey);
            System.out.println(jobName + "已删除");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void addJob(String jobName) {

        JobDetail jobDetail =
                JobBuilder.newJob().withIdentity(jobName, jobName).ofType(MyJob02.class).build();
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setName("myTrigger");
        cronTriggerFactoryBean.setGroup("myTrigger");
        cronTriggerFactoryBean.setJobDetail(jobDetail);
        cronTriggerFactoryBean.setCronExpression("0/9 * * * * ?");

        Trigger trigger = TriggerBuilder.newTrigger().startNow().build();

        try {
            schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
