package com.yyx.quartz.example1;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 这是一个简单的定时JOB的例子：to say "hello world"
 * @author yaoyuxiao
 *
 * 2016-4-1 下午02:54:55
 */
public class HelloJob implements Job{

	//通用的slf4j的接口
	private static Logger logger =LoggerFactory.getLogger(HelloJob.class);
	
	//空的构造函数 （Quartz要求一个public的空的构造函数以使得调度器在需要的时候可以实例化这个类）
	public HelloJob(){
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("定时任务执行：hello world! - "+new Date());
	}
	
}
