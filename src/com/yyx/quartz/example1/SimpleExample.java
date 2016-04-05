package com.yyx.quartz.example1;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.DateBuilder.evenMinuteDate;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yaoyuxiao
 * @date 2016年4月5日 上午10:59:54
 */
public class SimpleExample {

	// 通用的slf4j的接口
	private static Logger logger = LoggerFactory.getLogger(SimpleExample.class);

	public void run() throws Exception {
		logger.info("-------初始化开始---------");

		// 第一步：实例化StdSchedulerFactory工厂，从工厂中得到StdScheduler
		StdSchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		logger.info("-------初始化完成---------");

		// computer a time that is on the next round minute
		Date runTime = evenMinuteDate(new Date());

		logger.info("-------scheduling Job 定时任务开始---------");

		// 定义一个job，并让这个job与HelloJob类相关联
		JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1")
				.build();

		// 触发器：在下一轮的时间里触发这个定时job
		Trigger trigger = newTrigger().withIdentity("trigger1", "group1")
				.startAt(runTime).build();

		// 告诉 quartz调度这个 job使用我们自定义的 trigger
		sched.scheduleJob(job, trigger);
		logger.info(job.getKey() + "将在: " + runTime + " 时间内运行 ");

		// 启动调度器
		sched.start();

		logger.info("------- 调度器 开始执行 -----------------");

		// 等待到事先约定的时间 调度器 去执行这个定时job 等待 65 秒 .
		logger.info("------- 等待 65 秒 ... -------------");
		try {
			// wait 65 seconds to show job
			Thread.sleep(65L * 1000L);
			// executing...
		} catch (Exception e) {
			//
		}

		// 关闭调度器
		logger.info("------- 正在关闭 scheduler ---------------------");
		sched.shutdown(true);
		logger.info("------- 关闭  scheduler 完成  -----------------");
	}

	public static void main(String[] args) throws Exception {

		SimpleExample example = new SimpleExample();
		example.run();

	}

}
