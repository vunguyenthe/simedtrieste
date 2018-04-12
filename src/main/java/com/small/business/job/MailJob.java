package com.small.business.job;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.quartz.Job;

import org.quartz.JobExecutionContext;

import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.simedtrieste.dao.constant.Const;

public class MailJob implements Job {

	private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/simed";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "123456";
	// private static final String URL =
	// "jdbc:mysql://simedtrieste:3306/sampledb";
	// private static final String USER_NAME = "user4KB";
	// private static final String PASSWORD = "FRlXfYdoeMJob4Cc";

	public void execute(JobExecutionContext context) throws JobExecutionException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			float totalEnergy = 0.0f;
			float avgTotalEnergy = 0.0f;
			int num = 0;
			Connection con = (Connection) DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			Statement stmt = (Statement) con.createStatement();
			//for (Long roomId = 1L; roomId <= 1; roomId++) 
			{
				ResultSet rs = stmt
						.executeQuery("select * from energy  ORDER BY month DESC LIMIT 1");
				while (rs.next()) {
					System.out.println(rs.getInt(1) + "  " + rs.getInt(2) + "  " + rs.getString(3) + "  " + rs.getInt(4)
							+ "  " + rs.getFloat(5));
					if (rs.getFloat(5) > 0) {
						totalEnergy += rs.getFloat(5);
						System.out.println("totalEnergy: " + totalEnergy);
						num++;
					}
				}
			}
			avgTotalEnergy = totalEnergy / num;
			String content = "Respectful Ms. Silvia Ussai, please find your weekly report. Summary energy compliance: "
					+ avgTotalEnergy + "%. Best regards, Atisan";
			// Ussai.silvia@gmail.com
			System.out.println("============send mail==========");
			Mailer.send(Const.mailServer, Const.mailPass, "vunguyenthe1976@gmail.com", Const.mailTitle, content);
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
