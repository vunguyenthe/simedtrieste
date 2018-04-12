package com.small.business.controller;

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
		 //+---------------- minute (0 - 59)
		 //|  +------------- hour (0 - 23)
		 // |  |  +---------- day of month (1 - 31)
		 //|  |  |  +------- month (1 - 12)
		 //|  |  |  |  +---- day of week (0 - 6) (Sunday=0 or 7)
		 //|  |  |  |  |
		 // *  *  *  *  *  command to be executed		
		//30 2 * * * /your/command
		System.out.println("=====start job schedule...========");
		JobDetail job1 = JobBuilder.newJob(MailJob.class)
				.withIdentity("job1", "group1").build();
		Trigger trigger1 = TriggerBuilder.newTrigger()
				.withIdentity("cronTrigger1", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule("15 8 * * * ?"))
				.build();
				//cronSchedule(5 8 * * 0?) //sunday
		try {
			scheduler1 = new StdSchedulerFactory().getScheduler();
			scheduler1.start();
			scheduler1.scheduleJob(job1, trigger1);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}
}
