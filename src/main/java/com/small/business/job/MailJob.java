package com.small.business.job;

import java.net.MalformedURLException;
import java.net.URI;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.quartz.Job;

import org.quartz.JobExecutionContext;

import org.quartz.JobExecutionException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.simedtrieste.dao.constant.Const;
import java.net.URL;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URLConnection;
import org.xml.sax.SAXException;
public class MailJob implements Job {

	private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	//private static final String URL = "jdbc:mysql://localhost:3306/simed";
	//private static final String USER_NAME = "root";
	//private static final String PASSWORD = "123456";
	 //private static final String URL ="jdbc:mysql://simedtrieste:3306/sampledb";
	 //private static final String USER_NAME = "user4KB";
	 //private static final String PASSWORD = "FRlXfYdoeMJob4Cc";

	public void execute(JobExecutionContext context) throws JobExecutionException {

		System.out.println("==========execute job=========");
		try {
		    // open a connection to the site
		    URL url = new URL("http://simed5-simed3.a3c1.starter-us-west-1.openshiftapps.com/mailjob.php?keypass=ntR@simed");
		    URLConnection con = url.openConnection();
		    // activate the output
		    con.setDoOutput(true);
		    PrintStream ps = new PrintStream(con.getOutputStream());
		    // send your parameters to your site
		    ps.print("firstKey=firstValue");
		    ps.print("&secondKey=secondValue");
		 
		    // we have to get the input stream in order to actually send the request
		    con.getInputStream();
		 
		    // close the print stream
		    ps.close();
		    } catch (MalformedURLException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }		
	}

}
