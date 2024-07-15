package com.example.demo.controller;


import java.text.ParseException;
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

import com.example.demo.Dao.MdtransactionRepository;
import com.example.demo.entity.User;
import com.example.demo.entity.mdt_transaction_request_h2h;


@Controller
public class InpFileStatusController {
	
	@Autowired
	private MdtransactionRepository repo;
	
	ArrayList<mdt_transaction_request_h2h> transaction = new ArrayList<>();
	
	 private static final Logger logger = Logger.getLogger(InpFileStatusController.class);
	 
	 @GetMapping(value ="/inpfilestatus")
	 public String InpfileStatus() 
	 {
		return "Inpfilestatus";
		 
	 }
	
	@PostMapping(value ="/inpfilestatus")
	public String FileStatus(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,Model model,HttpSession session) throws ParseException 
	{
		logger.debug("Date in input will be -->"+date);
		
		 DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yy");
		 
		 String finaldt = date.format(format);

		logger.debug("Date in query is -->"+finaldt);
		User attribute = (User) session.getAttribute("user");
		String username = attribute.getUsername();
		System.out.println("Username will  be -->"+username);
		String bank_name = attribute.getBankname();
		
		
			int count = repo.getinpcount(finaldt,bank_name);
		
			logger.debug("Count will be -->"+count);
		
		
		 List<Object[]> distinctData = repo.getinpdata(finaldt, bank_name);
		 
		 for (Object[] row : distinctData) 
		 {
			 
			 String ackFlag = (String) row[0];
			 logger.debug("Data -->"+ackFlag);
			 
		 }
		  
		int cnt = distinctData.size();
		  
		System.out.println("Size of arraylist is -->"+cnt);
		if(cnt > 0) {
			logger.debug("Entering in transaction array..");
			model.addAttribute("list",distinctData);
			model.addAttribute("no_of_records",count);
			
		}
		else {
			model.addAttribute("message","No Data Found");
		}

		return "Inpfilestatus";
		
	}

}
