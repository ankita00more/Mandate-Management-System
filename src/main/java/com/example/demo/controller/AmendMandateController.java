package com.example.demo.controller;


import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Dao.BankDetailsNachRepository;
import com.example.demo.Dao.BanksConfigurationRepository;
import com.example.demo.Dao.MdtInitRequestH2hRepository;
import com.example.demo.Dao.SequenceCounterRepository;
import com.example.demo.Service.AmendServiceImpl;
import com.example.demo.entity.BankDetailsNach;
import com.example.demo.entity.MdtInitRequestH2h;
import com.example.demo.utilities.SessionChecker;



@Controller
public class AmendMandateController {

	@Autowired
	private AmendServiceImpl service;

	@Autowired
	MdtInitRequestH2hRepository repo;
	
	@Autowired
	BanksConfigurationRepository configRepo;
	
	@Autowired
	SequenceCounterRepository seqrepo;
	
	@Value("${app.MANDTINPFILES}") 
	String inpfilename;
	
	@Autowired
	BankDetailsNachRepository banksdao;
	
	BankDetailsNach details;
	
	@Autowired
	SessionChecker sessionChecker;
	
	
	
	private final Logger logger = Logger.getLogger(AmendMandateController.class);
	
	
	@GetMapping(value ="/YW1lbmRfMQ==")
    public String amendMandate(HttpSession session) 
	{
		if(sessionChecker.isSessionExpire(session)) 
		{
			logger.info("Session is invalid");
			return "redirect:/logout";
		}
		else 
		{
			return "SearchAmend";
		}
		 
		
		  
	}
	
	@PostMapping(value = "/YW1lbmQ=")
	public String amend_Mandate(Model model, @RequestParam("divsearch") String data,
			@RequestParam("searchType") String value, @RequestParam("SelectedMsgId") String selectedMsgId,
			HttpSession session) 
	{	
		
		if(sessionChecker.isSessionExpire(session)) 
		{
			logger.info("Session is invalid");
			return "redirect:/logout";
		}
		else
		{
			logger.info("Selected message id -->"+selectedMsgId);
			logger.info("Data will be -->"+data);
			service.getData(session,value,data,model);
			return "SearchAmend";
		}
			
		
			
	}
	
	
	
	@GetMapping(value = "/ZmluZGFtZW5k")
	public String updatedata(MdtInitRequestH2h customer,Model model,@RequestParam(name="SelectedMsgId",required = false) String selectedMsgId,HttpSession session) 
	{
		
		if(sessionChecker.isSessionExpire(session)) 
		{
			logger.info("Session is invalid");
			return "redirect:/logout";
		}
		else 
		{
			if(selectedMsgId==null) 
			{
				logger.info("after:  "+session.getAttribute("SelectedMsgId"));
				service.search_data(customer,(String)session.getAttribute("SelectedMsgId"),model);
				return "Customer_Amend";
			}
			else
			{
				session.setAttribute("SelectedMsgId", selectedMsgId);
				logger.info("Unique_id after encryption -->"+selectedMsgId);
				service.search_data(customer,selectedMsgId,model);
				return "Customer_Amend";
			}
		}

	}


	@PostMapping(value = "/ZmluZGFtZW5k")
	public String Amend(Model model, @RequestParam("jpg_image") MultipartFile jpg_image,@RequestParam("SelectedMsgId") String selectedMsgId,
			@RequestParam("tiff_image") MultipartFile tiff_image,HttpSession session,@RequestParam("differentaingParam") String differentaingParam,
			@ModelAttribute("customer") MdtInitRequestH2h customer,@RequestParam("Umrnval") String umrn) throws ParserConfigurationException, IOException 
	{
		if(sessionChecker.isSessionExpire(session)) 
		{
			logger.info("Session is invalid");
			return "redirect:/logout";
		}
		else
		{
			logger.info("Data would be --> "+customer);
			//Amend Mandate
			try 
			{
				service.AmendMandate(session,selectedMsgId,model,jpg_image,tiff_image,customer,umrn);
			} 
			catch (IOException | ParserConfigurationException | URISyntaxException e) 
			{
				logger.error("An exception occured while trying to amend"+e);
			}
			return "Message";
		}
			
		
			
	}
	
	
	
		

}
