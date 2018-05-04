package com.small.business.controller;

import javax.annotation.PostConstruct;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simedtrieste.dao.constant.Const;
import com.small.business.job.MailJob;
import com.small.business.model.user.Contact;

@Controller
@RequestMapping("/api")
public class SendMailController {
	 Scheduler scheduler1;
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public @ResponseBody boolean sendMail(@RequestBody Contact contact) {
		System.out.println(">> sendMail content: " + contact.toString());
		return Mailer.send(Const.mailServer, Const.mailPass, contact.getEmail(), Const.mailTitle, contact.getContent());
	}
	@PostConstruct
	public void init() {

		System.out.println("=====start job schedule...========");
		JobDetail job1 = JobBuilder.newJob(MailJob.class)
				.withIdentity("job1", "group1").build();
		Trigger trigger2 = TriggerBuilder
        .newTrigger()
        .withIdentity("dummyTriggerName", "group1")
        .forJob(job1)//<--- this line is the new addition
        .withSchedule(
        //CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
		CronScheduleBuilder.cronSchedule("0 0 14 ? * FRI")).build();
		//CronScheduleBuilder.cronSchedule("1 2 * * * ?")).build();
		
		try {
			scheduler1 = new StdSchedulerFactory().getScheduler();
			scheduler1.start();
			scheduler1.scheduleJob(job1, trigger2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
