package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.Dao.DestinationRepository;
import com.example.demo.entity.User;
import com.example.demo.entity.MdtAuthBean;

@Controller
public class DestinationController {
	
	@Autowired
	DestinationRepository DestiRepo;
	@Autowired
	private ResourceLoader resourceLoader;
	
	private final Logger logger = Logger.getLogger(DestinationController.class);
	
	@GetMapping("/mandateauthorizationstatus")
	public String getTodayMTran(Model model) {
		return "DestinationReport";
	}
	
	@PostMapping("/mandateauthorizationstatus")
	public String getTodayMTran1(  @RequestParam(value = "search", required = false) String search,
	          @RequestParam(value = "from-date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String fromDate,
	          @RequestParam(value = "to-date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String toDate,
	          @RequestParam(value = "account-number", required = false) String accountNumber,
	          @RequestParam(value = "umrn-number", required = false) String umrn,
	          HttpSession session,
	          Model model) throws SQLException, IOException {
		logger.info("Entered in Destination Report");
    	logger.info("searching via-"+search+"fromdate"+fromDate+"and"+"todate"+toDate+"accountNumber"+accountNumber+"umrnradio"+umrn);
		if(search.equalsIgnoreCase("date_radio")){
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  //it means date should parse from String to localdate obj
			LocalDate dt = LocalDate.parse(fromDate, dtf);                     //parsed from strings to LocalDate objects using dtf
			LocalDate dt1 = LocalDate.parse(toDate, dtf);                      //parsed from strings to LocalDate objects using dtf
			fromDate = dt.format(dtf.ofPattern("dd-MM-yyyy"));    
			System.out.println("fromDate formate after conversion"+fromDate);
			toDate = dt1.format(dtf.ofPattern("dd-MM-yyyy"));
			System.out.println("toDate formate after conversion"+toDate);
		}
		User attribute = (User) session.getAttribute("user");
		String username = attribute.getUsername();
		String bankname = attribute.getBankname();

		List<MdtAuthBean> list = DestiRepo.getMdtAuthDetails (fromDate, toDate, accountNumber, umrn, bankname, search);
  	System.out.println("came out from repo");     
  	if(list == null) {
  		logger.info("Destination report List is empty");
      }
      model.addAttribute("list", list);
      return "DestinationReport";
	}
}