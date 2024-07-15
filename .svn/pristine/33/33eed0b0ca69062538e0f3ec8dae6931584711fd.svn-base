package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
public class UpdateRejectedInpController {
	
	@Autowired
	private MdtransactionRepository repo;
	
	@Autowired
	private MandatetransactiondataRepository dao;
	
	 private static final Logger logger = Logger.getLogger(UpdateRejectedInpController.class);
	 
	 @GetMapping(value ="/dXBkdHJlamlucA==")
	 public String updaterejectedinpfile() 
	 {
		return "Updaterejectedinpfile";
		 
	 }
	
	@PostMapping(value = "/dXBkdHJlamlucA==")
	public String updaterejectedinpfile(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,Model model,
			HttpSession session,mandate_transaction_data transaction) 
	{
		
		 DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yy");
		 String finaldt = date.format(format);
		 String message = "";
		 String creationdt = DateMaster.Datetoday();
 		 List<String> ackflag = new ArrayList<>();
 		 
 		User attribute = (User) session.getAttribute("user");
		String username = attribute.getUsername();
		logger.debug("Username will  be -->"+username);
		String bank_name = attribute.getBankname();
 		 
 		ackflag = repo.ackflagval(bank_name, finaldt);
 		
 		logger.debug("Size of array -->"+ackflag.size());
 		
 		if(ackflag.contains("Rejected")) {
 			
 			int cnt = repo.updateInpfile(bank_name,finaldt);
 			logger.debug("Count of updated rows -->"+cnt);
 			transaction.setUMRN("REJECTED FILE");
			transaction.setADDBY_BANK("Y");
			transaction.setCREATION_DATE(creationdt);
			transaction.setBANKNAME(bank_name);
			transaction.setACTION("REJECTED");
 			
 			if(cnt > 0) {
 				logger.debug("Updating in mandate transaction");
 				
 				if(dao.save(transaction) != null)
 				{
 					message = "Please regenerate file now data has been updated for:" + finaldt;
 			        model.addAttribute("err", message);
 				}
 			}
 			else {
 				message = "Exception occured while adding data please contact psmg";
			    model.addAttribute("err", message);
 				
 			}
 			
 		}
 		else {
 			logger.debug("None of the file is rejected");
 			message = "No need to generate File as File is not Rejected for :" + finaldt;
		    model.addAttribute("err", message);
 			
 		}
		 
		return "Updaterejectedinpfile";
		
	}

}
