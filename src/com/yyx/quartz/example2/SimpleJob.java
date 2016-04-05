package com.yyx.quartz.example2;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;


/**
 * 
 * @author yaoyuxiao
 *
 * 2016年4月5日 上午10:56:33
 */
public class SimpleJob implements Job {

    private static Logger log = LoggerFactory.getLogger(SimpleJob.class);

    public SimpleJob() {
    }

    public void execute(JobExecutionContext context)
        throws JobExecutionException {

        JobKey jobKey = context.getJobDetail().getKey();
        log.info("SimpleJob says: " + jobKey + " executing at " + new Date());
    }

}
