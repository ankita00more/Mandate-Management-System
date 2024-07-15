package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Dao.MdtInitRequestH2hRepository;
import com.example.demo.Dao.MdtransactionRepository;
import com.example.demo.Service.ReportsService;
import com.example.demo.Service.ReportsServiceImpl;
import com.example.demo.entity.MdtInitRequestH2h;
import com.example.demo.entity.User;

@Controller
public class ReportsController 
{
	//logger
	private static final Logger logger = Logger.getLogger(ReportsController.class);
	
	@Autowired
	MdtransactionRepository transrepo;
	
	@Autowired
	MdtInitRequestH2hRepository initrepo;
	
	@Autowired
	ReportsServiceImpl service;
	
	@GetMapping("/dHJhbnN0YXR1cw==")
	public String MandateFileStatus() 
	{
		
		logger.info("Checkin Mandate Transaction File Status");
		return "MandateTransactionFileStatus";
		
	}
	
	@PostMapping("/dHJhbnN0YXR1cw==")
	public String MandateFileStatusData(HttpSession session,@RequestParam("date") String creation_date,Model model,
			@RequestParam("creditor_acc_no") String creditor_acc_no) throws ParseException
	{
		User attribute = (User) session.getAttribute("user");
		String bank_name = attribute.getBankname();
		List<Object[]> filedata = null;
		logger.info("Creation Date :"+creation_date+" Creditor account no:"+creditor_acc_no);
		int cnt = 0;
		if (!creation_date.equals("")) 
		{
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	          Date date = sdf.parse(creation_date);
	          sdf = new SimpleDateFormat("dd-MM-yy");
	          creation_date = sdf.format(date);
	          logger.info("Final Creation_date would be: "+creation_date);
	          filedata = transrepo.getstatusdatabycredt(bank_name,creation_date);
	          if(filedata != null)
	           cnt = filedata.size();
	    } 
		else if(!creditor_acc_no.equals(""))
		{
			filedata = transrepo.getstatusbycreditoracc(bank_name, creditor_acc_no);
			if(filedata != null)
				cnt = filedata.size();
		}
		
		
		
		if(cnt > 0)
		{
			logger.debug("Entering in transaction array..");
			model.addAttribute("list",filedata);
			model.addAttribute("no_of_records",cnt);
		}
		else
		{
			model.addAttribute("message","No data avaliable");
		}
		return "MandateTransactionFileStatus";
		
	}
	
	@GetMapping("/dG9kYXlzdGF0dXM=")
	public String TodayMandateFileStatus(HttpSession session,Model model) 
	{
		User attribute = (User) session.getAttribute("user");
		String bank_name = attribute.getBankname();
		List<Object[]> todaydata = null;
		int cnt = 0;
		
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
		logger.info("Today's date"+today);
		todaydata = transrepo.gettodaystransstatus(today,bank_name);
		logger.info("Data Size :"+todaydata.size());
		cnt = todaydata.size();
		if(todaydata != null && cnt > 0)
		{
			logger.debug("Entering in transaction array..");
			model.addAttribute("list",todaydata);
			model.addAttribute("no_of_records",cnt);
		}
		else
		{
			model.addAttribute("message","No data avaliable");
		}
		logger.info("Checking toady's Mandate Transaction File Status");
		return "TodayMandateTransaction";
		
	}
	
	@GetMapping("/bW5kdHN0YXR1cw==")
	public String MandateStatus(HttpSession session,Model model) 
	{
		model.addAttribute("message","No data found");       
		return "MandateStatus";
				
	}
	
	
	@PostMapping("/bW5kdHN0YXR1cw==")
	public String DisplayMandateStatus(HttpSession session,Model model,@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate,@RequestParam("product") String productType) 
	{
		try
		{
			boolean flag = service.MandateStatus(session,model,fromDate,toDate,productType);
		}
		catch(Exception e)
		{
			logger.error("Error occured :"+e);
		}
		
		return "MandateStatus";
		
	}
	
	
}
