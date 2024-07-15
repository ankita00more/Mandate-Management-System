package com.example.demo.controller;


import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Dao.MdtransactionRepository;
import com.example.demo.Service.MandateServiceImpl;
import com.example.demo.entity.CatCodeList;
import com.example.demo.entity.MdtInitRequestH2h;
import com.example.demo.entity.User;
import com.example.demo.utilities.SessionChecker;
import com.example.demo.utilities.sendMandateSms;


@Controller
public class CreateMandateController 
{
	
	@Autowired
	private MandateServiceImpl mandateService;
	@Autowired
	SessionChecker sessionChecker;
	
	Properties prop = new Properties();
	byte[] jpg_bytes = null;
	byte[] tif_bytes = null;
	
	@Autowired
	MdtransactionRepository repo;
	
	
	//logger
	private static final Logger logger = Logger.getLogger(CreateMandateController.class);
	
	@GetMapping("/Y3JlYXRl")
	public String createMandate(Model model,HttpSession sponsorSession) 
	{
		    logger.info("Enter in create mandate getmapp");
		  		
		  		if(sessionChecker.isSessionExpire(sponsorSession)) 
				{
					logger.info("Session is invalid");
					return "redirect:/logout";
				}
			  	
			  	else
			  	{
			  		Boolean spoSession = (Boolean) sponsorSession.getAttribute("sponsorSession");
			  		
			  		if (spoSession != null && spoSession) 
			  		{
			  			  User attribute = (User) sponsorSession.getAttribute("user"); 
						  String validity = attribute.getValidity_flag();
						  logger.info("Validity flag value will be -->"+validity); 
						  String bank_name = attribute.getBankname();
						  logger.info("Bankname will be -->"+bank_name);
						  
						  MdtInitRequestH2h customer = new MdtInitRequestH2h();
						  
						  List<CatCodeList> catCodeList = mandateService.codeList();
						  
						  model.addAttribute("customer", customer); 
						  model.addAttribute("cat_code",catCodeList); 
						   
						  return "Customer_Input";
			  			
			  		}
			  		else
			  		{
			  			 logger.info("Illegal Redirection");
				  		 return "redirect:/logout";
			  		}
			  	}
	}
	
	
	
	
	
	@PostMapping(value = "/Y3JlYXRl")
	public String createMandate(@ModelAttribute("customer") MdtInitRequestH2h customer,Model model, HttpSession session,
			 @RequestParam("jpg_image") MultipartFile jpg_image,@RequestParam("tiff_image") MultipartFile tif_image) 
	{
		if(sessionChecker.isSessionExpire(session)) 
		{
			logger.info("Session is invalid");
			return "redirect:/logout";
		}
		else 
		{
			
			
			logger.info("Details "+customer);
			mandateService.createMandate(session,customer, jpg_image, tif_image,model);
			return "Message";
		}
			
		

		
			
	}
}
