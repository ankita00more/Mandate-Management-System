package com.example.demo.utilities;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.stereotype.Component;
import com.example.demo.controller.LoginController;


 @Component 
 public class SessionListener implements HttpSessionListener 
 {

	 @Override 
	 public void sessionCreated(HttpSessionEvent event) 
	 {
		 System.out.println("User session created for username----" +
		 event.getSession().getValue("loginName"));
		 event.getSession().setMaxInactiveInterval(1800);
  
	 }
  
	 @Override 
	 public void sessionDestroyed(HttpSessionEvent event) 
	 {
		 	  System.out.println("User session destroyed for username-----" +
			  event.getSession().getValue("loginName"));
		 	  LoginController.activeLoginNames.remove(event.getSession().getValue("loginName"));
	 } 
  }

