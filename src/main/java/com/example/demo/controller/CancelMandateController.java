package com.example.demo.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Dao.BankDetailsNachRepository;
import com.example.demo.Dao.BanksConfigurationRepository;
import com.example.demo.Dao.MdtInitRequestH2hRepository;
import com.example.demo.Dao.SequenceCounterRepository;
import com.example.demo.Service.CancelServiceImpl;
import com.example.demo.entity.BankDetailsNach;
import com.example.demo.entity.CatCodeList;

import com.example.demo.entity.MdtInitRequestH2h;
import com.example.demo.entity.User;
import com.example.demo.utilities.DateMaster;
import com.example.demo.utilities.PathsMaster;
import com.example.demo.utilities.SessionChecker;
import com.example.demo.utilities.XML_Cancel;
import com.example.demo.utilities.sendMandateSms;

@Controller
public class CancelMandateController {
	
	@Autowired
	private CancelServiceImpl service;	
	
	@Autowired
	MdtInitRequestH2hRepository repo;
	
	@Autowired
	BanksConfigurationRepository configRepo;
	
	@Autowired
	BankDetailsNachRepository banksdao;
	
	@Autowired
	SessionChecker sessionCheck;
	
	BankDetailsNach details;
	
	@Value("${app.MANDTINPFILES}") 
	String filename;
	
	@Value("${app.INP_MDT_SIGNED_TO_NPCI}")
	String zipfilepath;
	
	@Autowired
	SequenceCounterRepository seqrepo;
	private static final Logger logger = Logger.getLogger(CancelMandateController.class);
	
	@PostMapping(value = "/Y25jbG1hbmRhdGU=")
	public String cancelMandate(Model model,@RequestParam("Mndt_Canc") String canclopt,@RequestParam("Dbopt") String opt,
			@RequestParam("SelectedMsgId") String selectedmsgId,MdtInitRequestH2h customer,HttpSession session,
			@RequestParam("deleteMandateVar") String deleteMandateVar,@RequestParam("customerId") String customerId) 
	{
		try
		{
			int seq = 0;
			String varchar_seq_num = "";
			User attribute = (User) session.getAttribute("user");
			String bank_name = attribute.getUsername();
			String reason_code = customer.getReason_code();
			String sms_flag = attribute.getSms_flag();
			String smsbankusername = attribute.getSmsbankusername();
			String smsbankuserid = attribute.getSmsbankuserid();
			String smsbankpassword = attribute.getSmsbankpassword();
			logger.info("Reason code"+reason_code);
			 if(deleteMandateVar!=null && deleteMandateVar.equalsIgnoreCase("yes"))
			 {
				
				if(customerId != null) {
					customer = repo.findByUniqueId(customerId);
					customer.setReason_code(reason_code);
					logger.info("Customer:"+customer);
					
					PathsMaster pm = new PathsMaster(configRepo);
					String destpath = pm.getMndtInpFiles(bank_name, filename);
					logger.info("Destination path would be -->"+destpath);
					String zip_path = pm.getInpMdtSignedToNPCI(bank_name,zipfilepath);
					logger.info("Zip path would be -->"+zip_path);
					
					details = banksdao.findByBankname(bank_name);
					
					String shortcode = details.getShortcode();
					String bankloginid = details.getBankloginid();
					
					//Adding sequence number to the database
					seq = seqrepo.getseqcount(bank_name,DateMaster.getDate2());
					logger.info("Sequence num-->"+seq);
					seq++;
					logger.info("<--- Seq number -->"+seq);
					int rows = seqrepo.insertseq(seq,bank_name,"CANCEL",DateMaster.getDate2());
					if(rows > 0)
					{
						logger.info("Sequence inserted");
					}
					else
					{
						logger.info("Failed to insert sequence");
					}
					
					if(bank_name != null) 
					{
						varchar_seq_num = String.valueOf(seq);
						int len = varchar_seq_num.length();
						for (int j = 0; j < 6 - len; j++) 
						{
							
							varchar_seq_num = "0".concat(varchar_seq_num);
							logger.info("Sequence Number -->"+varchar_seq_num);
						}
						
					}
					
				
					
					
					logger.info("Customer Details are"+customer.getAmount());
					
					if(new XML_Cancel().cancelMandateInputXML(customer, bank_name,shortcode,bankloginid,destpath,zip_path,varchar_seq_num) && service.updateMdt_Int_Requestdata(customer, customerId)) 
					{
						if(sms_flag.equalsIgnoreCase("Y"))
						{
							logger.info("Sending sms...");
			 				if(new sendMandateSms().sendSMS(customer,"CANCEL",smsbankusername, smsbankuserid, smsbankpassword))
			 				{
			 					customer = repo.findByUniqueId(customerId);
			 					customer.setIs_sms_sent("Y");
			 					if(repo.save(customer) != null) 
			 					{
			 						logger.info("Message sent successfully");
			 					}
			 					else
			 					{
			 						logger.info("Unable to add details in database");
			 					}
			 				}
			 				else
			 				{
			 					    logger.info("Message not sent");
		 							customer = repo.findByUniqueId(customerId);
				 					customer.setIs_sms_sent("NS");
				 					if(repo.save(customer) != null)
				 					{
				 						logger.info("Message status saved in database");
				 					}
				 					else
				 					{
				 						logger.info("Unable to save message status in database");
				 					}
				 						
		 						
			 				}
						}
						model.addAttribute("Unique_id","Mandate Cancelled Successfully having Uniqueid:'"+customer.getUniqueId()+"'");
					}
					else 
					{
						model.addAttribute("Unique_id","Exception occured. Please contact System Administrator");
					}
					
					
				}
				
			
				
			}
			
			
		}
		catch(Exception e)
		{
			logger.error("Exception occurred while cancellation"+e);
		}
		return "Message";
		
		 
		
	}	
	
	
	@PostMapping(value = "/c2VhcmNoZGF0YQ==")
	public String searchMandate(Model model,@RequestParam("searchType") String canclopt,@RequestParam("divsearch") String opt,
			@RequestParam("SelectedMsgId") String selectedmsgId,MdtInitRequestH2h customer,HttpSession session) 
	{
		
	
			service.search_data(session,model,canclopt,opt);
			return "SearchAmend";

	}
	
	
	@GetMapping(value = "/ZGVsbWFuZGF0ZQ==")
	public String deleteMandate(Model model,@RequestParam("searchType") String canclopt,@RequestParam("divsearch") String opt,
			@RequestParam("SelectedMsgId") String selectedmsgId,MdtInitRequestH2h customer,HttpSession session) 
	{
		
	
		String bank_name = "";
		User attribute = (User) session.getAttribute("user");
	    bank_name = attribute.getUsername();

		
		
		if(selectedmsgId != null && !selectedmsgId.equals("")) 
		{
			
			service.insertdataIntoMdtBkp(selectedmsgId); 
			
			customer = repo.findByUniqueId(selectedmsgId);
			List<CatCodeList> catCodeList = service.codeList();
			model.addAttribute("customer", customer);
			model.addAttribute("cat_code", catCodeList);
			model.addAttribute("selected_catcode", customer.getCategory_code());
			bank_name = customer.getBank_name();
			
			

		}
		return "Delmndt";
	
	
		
	}		
		
}
	
	
	

