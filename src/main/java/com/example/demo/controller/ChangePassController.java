package com.example.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.demo.Service.ChangePassServiceImpl;

@Controller
public class ChangePassController 
{
	
	
	@Autowired
	ChangePassServiceImpl service;
	
	private final Logger logger = Logger.getLogger(ChangePassController.class);
	
	@GetMapping(value = "/changepass")
	public String changepassword() 
	{
		return "change_password";
	}
	
	@PostMapping("/changepass")
	public String ChangePassword(@RequestParam("username") String username,@RequestParam("password") String password,Model model) 
	{
		Boolean checkupdate = service.ChangePass(username,password);
		
		if(checkupdate == true) 
		{
			logger.info("Data updated successfully");
			String confirm = "Password updated successfully";
			model.addAttribute("message",confirm);
			return "confirm";
		}
		else 
		{
			String err = "Error occured cannot update password try again";
			model.addAttribute("err",err);
			return "change_password";
		}
		
		
		
	}
	
}
