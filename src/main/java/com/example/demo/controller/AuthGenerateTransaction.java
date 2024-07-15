package com.example.demo.controller;


import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Dao.BankDetailsNachRepository;
import com.example.demo.Dao.BanksConfigurationRepository;
import com.example.demo.Dao.MdtransactionRepository;
import com.example.demo.Service.AuthGenServiceImpl;

import com.example.demo.entity.User;
import com.example.demo.entity.inputdebitsequence_h2h;
import com.example.demo.entity.mdt_transaction_request_h2h;
import com.example.demo.utilities.DateMaster;

import com.example.demo.utilities.SessionChecker;

@Controller
public class AuthGenerateTransaction {
	@Autowired
	private AuthGenServiceImpl service;

	@Autowired
	private MdtransactionRepository repo;

	@Autowired
	BankDetailsNachRepository banksdao;
	
	@Autowired
	BanksConfigurationRepository configRepo;
	
	@Value("${app.INPToNPCI}")
	String filepath;
	
	
	
	
	@Autowired
	SessionChecker sessionChecker;
	
	
	private final Logger logger = Logger.getLogger(AuthGenerateTransaction.class);
	
	
	@GetMapping(value ="/YXV0aG1hbmRhdGU=")
	 public String authmandate(HttpSession session,Model model)
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
			logger.info("Bankname will be -->"+bank_name);
			String creation_date = DateMaster.getDateonly();
			ArrayList<mdt_transaction_request_h2h> transactionlists = new ArrayList<>();
			transactionlists = repo.getdata(bank_name,creation_date.trim());
			
			model.addAttribute("generateInput",transactionlists);
			model.addAttribute("generateInputsize",Integer.valueOf(transactionlists.size()));
			return "Transaction_Data";
		}
			 
			
		
		
	 }

	@PostMapping(value = "/YXV0aG1hbmRhdGU=")
	public String AuthGen(@RequestParam("generateInputFilevar") String generateInputFilevar, HttpSession session,
			@RequestParam("transactionDataArray") String[] transactionDataArray, Model model,
			inputdebitsequence_h2h inpseq) 
	{
		if(sessionChecker.isSessionExpire(session)) 
		{
			logger.info("Session is invalid");
			return "redirect:/logout";
		}
		else 
		{
		
			logger.info("Inside update amount");
			User attribute = (User) session.getAttribute("user");
			String bank_name = attribute.getBankname();
			logger.info("Bankname would be -->"+bank_name);
			// updating amount in mdt_transaction_request_h2h table
			service.updateMaxAmount(transactionDataArray, model,session);
			return "Transaction_Data";
		}
		
		

	}

	
	  @PostMapping(value ="/Z2VuaW5wdXQ=") 
	  public String GenerateInpFile(HttpSession session,Model model)
	  { 
		  if(sessionChecker.isSessionExpire(session)) 
		  {
				logger.info("Session is invalid");
				return "redirect:/logout";
		  }
		  else 
		  {
		  
		  
			  logger.info("Inside Generate Input");
			  User attribute = (User) session.getAttribute("user");
			  String username = attribute.getUsername(); 
			  String bank_name = attribute.getBankname();
				
			  if(service.InpFileGen(bank_name,username,session))
			  {
				 logger.info("INP Creation Successful");
			  }
			  else
			  {
				  model.addAttribute("error","Exception occured, Please Contact System Administrator");
			  }
			  return "InputDownload";
		  }
			  
		  
		  
	  
	  }
	  
	  @PostMapping(value = "/ZG93bmxvYWQ=")
	  public String filedownload(@RequestParam("downloaddt") String seldate,HttpSession session,Model model) 
	  {
		  if(sessionChecker.isSessionExpire(session)) 
		  {
				logger.info("Session is invalid");
				return "redirect:/logout";
		  }
		  
		  else
		  {
			  if(service.downloadFile(session,filepath,seldate,model))
				{
					logger.info("Data output avaliable");
					
				}
				
			    return "InputDownload";
		  }
			
	  }
	  
	  @RequestMapping(value = "/downloadfile")
	 public String downloadfile(@RequestParam("filename") String filename,@RequestParam("path") String path,HttpServletRequest request,HttpServletResponse response) {
			
		 
		  try 
		  {
			  
			 
			 logger.info("Download path would be .."+path);
			 logger.info("Filename will be -->"+filename);
			 response.reset();
			 response.setContentType("APPLICATION/OCTET-STREAM");
			 response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
			 java.io.FileInputStream  inputStream = new java.io.FileInputStream(path+ filename);
			 int c; 
				while ((c = inputStream.read()) != -1) {
				 	  response.getWriter().write(c);
				} 
			  if (inputStream != null) {
			 		inputStream.close();
				}
				response.getWriter().close();
			 
			 
			 
			  
		  }
		  catch(Exception e) 
		  {
			  e.printStackTrace();
		  }
		  
		  
		return "InputDownload";
		  
	  }
	  
	  
	 

}

