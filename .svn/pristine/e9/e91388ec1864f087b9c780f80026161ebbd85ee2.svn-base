package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Dao.MandatetransactiondataRepository;
import com.example.demo.Dao.MdtransactionRepository;
import com.example.demo.Service.AddUmrnServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.entity.mandate_transaction_data;
import com.example.demo.utilities.DateMaster;
import com.example.demo.utilities.SessionChecker;

@Controller
public class AddUmrnController 
{
	
	@Autowired
	private MdtransactionRepository repo;
	
	@Autowired
	private MandatetransactiondataRepository dao;
	
	@Autowired
	private AddUmrnServiceImpl service;
	
	@Autowired
	SessionChecker sessionChecker;
	
	private final Logger logger = Logger.getLogger(AddUmrnController.class);
	
	
	@GetMapping(value ="/YWRkVW1ybg==")
	public String addUmrn(HttpSession session) 
	{
		if(sessionChecker.isSessionExpire(session)) 
		{
			logger.info("Session is invalid");
			return "redirect:/logout";
		}
		else
		{
			return "Addumrn";
		}
		 	
	}
	
	
	@PostMapping(value = "/YWRkVW1ybg==")
	public String AddUmrn(@RequestParam("umrn") String UMRN,mandate_transaction_data transaction,Model model,HttpSession session) 
	{
		if(sessionChecker.isSessionExpire(session)) 
		{
			logger.info("Session is invalid");
			return "redirect:/logout";
		}
		else
		{
		
			User attribute = (User) session.getAttribute("user");
			String bank_name = attribute.getBankname();
			int count = 0;
			int rows1 = 0;
			String message = "";
			boolean flag = false;
			String date = DateMaster.getDate2();
			count = repo.getCount(UMRN);
			
			if(count == 0) 
			{
				
			    flag = service.updatedata(UMRN,bank_name);
				rows1 = repo.updatetransactino(UMRN);
				
				transaction.setUMRN(UMRN);
				transaction.setBANKNAME(bank_name);
				transaction.setADDBY_BANK("Y");
				transaction.setCREATION_DATE(date);
				transaction.setACTION("ADDED");
				if(flag = true && rows1 > 0) {
					if(dao.save(transaction) != null) 
					{
						message = "UMRN IS SUCCESSFULLY ADDED : " +UMRN;
						logger.info("Message :"+message);
				        model.addAttribute("UMRN", message);
					}
					else {
						model.addAttribute("UMRN","Unable to add data!");
					}
				}
				else {
					
					message = "Error while adding UMRN : " + UMRN;
					logger.info("Error while adding umrn in today's transactions.");
					model.addAttribute("UMRN", message);
					
				}
				

			}
			else {
				
				model.addAttribute("UMRN","UMRN is already there in today's transaction : "+UMRN);
				
			}
			
			
			return "Addumrn";
			
		}
	}
		


}
