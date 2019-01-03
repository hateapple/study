package com.hateapple.myspringboot.base.controller;

import com.hateapple.myspringboot.quartz.JobManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quartz")
public class QuartzController {
    @Autowired
    private JobManager jobManager;

    @RequestMapping("/stopJob")
    public void stopJob(String jobName){
        jobManager.pauseJob(jobName);
    }

    @RequestMapping("/deleteJob")
    public void deleteJob(String jobName){
        jobManager.deleteJob(jobName);
    }

    @RequestMapping("/addJob")
    public void addJob(String jobName){
        jobManager.addJob(jobName);
    }
}
