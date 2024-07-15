package com.example.demo.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.Dao.BankDetailsAchRepository;
import com.example.demo.Service.DeleteMdtService;
import com.example.demo.entity.User;
import com.example.demo.utilities.SessionChecker;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;


@Controller
public class CancelUmrController {
	private final Logger logger = Logger.getLogger(CancelUmrController.class);

	@Autowired
	DeleteMdtService ser;
	
	@Autowired
	BankDetailsAchRepository bankAch;
	
	 @Autowired
	 SessionChecker sessionChecker;
		
	@PostMapping("/getMandate")
	public String searchMdt(String umrn,Model model, HttpSession session) {
	  	if(sessionChecker.isSessionExpire(session)) {
    		return "redirect:/";
    	}else {
		logger.info("SEARCH MANDATE FOR CANCEL :");
		User attribute = (User) session.getAttribute("user");
		//String username = attribute.getUsername();
		String bankname = attribute.getBankname();
		List<Object[]> data = ser.getUmrn(bankname, umrn);
		 model.addAttribute("Data", data);
		 
		 
		 if (data != null) {
			 model.addAttribute("umrn", umrn);
			 model.addAttribute("isExist", "Y");
			 model.addAttribute("Customer_Data", data);
			} else {
				 model.addAttribute("isExist", "N");
			}
		return "cancel_Mandate";}
	}
	@PostMapping("/deleteMandate")
	public String CancelMdt(@RequestParam("umrn")String umrn,Model model, HttpSession session) throws ParserConfigurationException, TransformerException, Exception {	
	  	if(sessionChecker.isSessionExpire(session)) {
    		return "redirect:/";
    	}
    	else {
    	logger.info(" DELETE MANDATE :");
		User attribute = (User) session.getAttribute("user");
		String bankname = attribute.getBankname();
		String status = ser.createXlxForDltMdt(umrn, bankname);
		if (status.equals("SUCCESS")) {
			status = ser.deleteumrn(umrn);
			if (status.equals("SUCCESS")) {
				model.addAttribute("isDeleted", "Y");
			} else {
				model.addAttribute("isDeleted", "N");
			}
	
	}
		 return "cancel_Mandate";
    	}	
	}}

