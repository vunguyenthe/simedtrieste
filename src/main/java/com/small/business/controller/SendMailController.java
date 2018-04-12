package com.small.business.controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
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
	static Scheduler scheduler1;
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
		Trigger trigger1 = TriggerBuilder.newTrigger()
				.withIdentity("cronTrigger1", "group1")
				//.withSchedule(CronScheduleBuilder.cronSchedule("1 5 1 * * ?")) //23:10:01 am
				//second minute hour day of month month of year day of week year [1..7]
				.withSchedule(CronScheduleBuilder.cronSchedule("0 30 10 ? * FRI")) 
				.build();
				//0 0 12 * * 1 //12 hour sunday
		try {
			scheduler1 = new StdSchedulerFactory().getScheduler();
			scheduler1.start();
			scheduler1.scheduleJob(job1, trigger1);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}
}
