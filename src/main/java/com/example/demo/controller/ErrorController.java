package com.example.demo.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.entity.User;
 
  
  @ControllerAdvice 
  public class ErrorController 
  {
	  private final Logger logger = Logger.getLogger(ErrorController.class);
  
	  @ExceptionHandler(Exception.class)
	  public String handleException(Exception ex,HttpServletRequest request) 
	  {
		
		 logger.error("An exception occured in your application :"+ex);
		 HttpSession session = request.getSession();
		 session.invalidate();
		 return "Error_Page"; 
		  
	  }
  
  }

