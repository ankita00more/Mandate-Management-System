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
import com.example.demo.entity.User;
import com.example.demo.entity.mandate_transaction_data;
import com.example.demo.utilities.DateMaster;

@Controller
public class DeleteUmrnController {
	
	@Autowired
	private MdtransactionRepository repo;
	
	@Autowired
	private MandatetransactiondataRepository dao;
	
	 private static final Logger logger = Logger.getLogger(DeleteUmrnController.class);
	 
	 
	
	
	@PostMapping("/ZGVsVW1ybg==")
	public String DeleteUmrn(@RequestParam("umrn") String UMRN,Model model,HttpSession session) 
	{
		
		User attribute = (User) session.getAttribute("user");
		String bank_name = attribute.getBankname();
		mandate_transaction_data transaction = new mandate_transaction_data();
		int count = 0;
		int rows = 0;
		String message = "";
		String date = DateMaster.getDate2();
		count = repo.getCount(UMRN);
		logger.debug("Count will be -->"+count);
		if(count > 0) {
			rows = repo.deleteumrn(UMRN);
			transaction.setACTION("REMOVED");
			transaction.setADDBY_BANK("Y");
			transaction.setBANKNAME(bank_name);
			transaction.setUMRN(UMRN);
			transaction.setCREATION_DATE(date);
			
			if(dao.save(transaction) != null && rows > 0) 
			{
				logger.debug("UMRN is Successfully Removed : " + UMRN);
				message = "UMRN is Successfully Removed : " + UMRN;
				model.addAttribute("UMRN",message);
				
			}
			else {
				logger.debug("Error Removing Umrn : " + UMRN);
				message = "Error Removing Umrn :"+UMRN;
				model.addAttribute("UMRN",message);
			}
			
		}
		else {
			logger.debug("UMRN NOT PRESENT IN TODAY'S TRANSACTION " + UMRN);
			message = "UMRN NOT PRESENT IN TODAY'S TRANSACTION"+UMRN;
			model.addAttribute("UMRN",message);
		}
		
		
		return "Addumrn";
		
	}
		

}
