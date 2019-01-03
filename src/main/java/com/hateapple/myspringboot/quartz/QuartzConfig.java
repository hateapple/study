package com.hateapple.myspringboot.quartz;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    @Bean("myJob")
    public JobDetailFactoryBean buildJobDetailFactoryBean(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setGroup("myJob");
        jobDetailFactoryBean.setName("myJob");
        jobDetailFactoryBean.setJobClass(MyJob.class);
        jobDetailFactoryBean.setDurability(false);
        jobDetailFactoryBean.setApplicationContextJobDataKey("applicationContext");
         return jobDetailFactoryBean;
    }
    @Bean("cronTrigger")
    public CronTriggerFactoryBean buildCronTriggerFactoryBean(@Qualifier("myJob") JobDetailFactoryBean jobDetailFactoryBean){
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setName("myTrigger");
        cronTriggerFactoryBean.setGroup("myTrigger");
        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        cronTriggerFactoryBean.setCronExpression("0/10 * * * * ?");
        return cronTriggerFactoryBean;
    }

    @Bean("schedulerFactoryBean")
    public SchedulerFactoryBean buildSchedulerFactoryBean(@Qualifier("cronTrigger") CronTriggerFactoryBean cronTriggerFactoryBean){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(cronTriggerFactoryBean.getObject());
        return schedulerFactoryBean;
    }

}
