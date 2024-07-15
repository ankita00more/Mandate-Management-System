package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.example.demo.Dao.MandateMasterImdh2hRepository;
import com.example.demo.entity.User;
import com.example.demo.utilities.SessionChecker;
import com.example.demo.Service.mdtAuthService;
import com.example.demo.Dao.ReasonCodeRepositoryDest;

@Controller
public class ProcessMadController {
	private final Logger logger = Logger.getLogger(ProcessMadController.class);

	@Autowired
	SessionChecker sessionChecker;

	@Autowired
	mdtAuthService mdtAuthService;

	@Autowired
	ReasonCodeRepositoryDest rsncodesRepo;

	@Autowired
	MandateMasterImdh2hRepository mdtRepo;

	@Autowired
	MandateMasterImdh2hRepository repo1;

	@GetMapping("/processMandate")
	public String processMandat(HttpSession session, Model model) {

		if (sessionChecker.isSessionExpire(session)) {
			return "redirect:/";
		} else {
			User attribute = (User) session.getAttribute("user");
			String bankname = attribute.getBankname();
			try {
				logger.info("Getting inward zip file names: " + bankname);
				List<Object[]> AllFileNames = repo1.findDistinctByBankNameAndFlags(bankname);
				Object[] firstRecord = AllFileNames.get(0);
				session.setAttribute("action", (String) firstRecord[1]);
				
				List<Object[]> formattedFileNames = new ArrayList<>();

				for (Object[] file : AllFileNames) {
				    Date creationDate = (Date) file[2];
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				    String formattedCreationDate = sdf.format(creationDate);
				    file[2] = formattedCreationDate;
				    formattedFileNames.add(file);
				}
				model.addAttribute("allfiles", AllFileNames);
			} catch (Exception e) {
				logger.error("error occured while getting file names for " + bankname + " :" + e);
				e.printStackTrace();
			}
			return "process_Mandate";
		}
	}

	@GetMapping("/mandateAuth")
	public String mdtAuth(@RequestParam(value = "zipfilename", required = false) String zipfilename, HttpSession session, Model model) {
		if (sessionChecker.isSessionExpire(session)) {
			return "redirect:/";
		} else {
			User attribute = (User) session.getAttribute("user");
			String bankname = attribute.getBankname();
			
			if (zipfilename == null) {
	            zipfilename = (String) session.getAttribute("zipfilename");
	        } else {
	            session.setAttribute("zipfilename", zipfilename);
	        }
			try {
				List<Object[]> data = mdtAuthService.fetchDataForZipFileName(zipfilename, bankname);
				List<Object[]> reasonCodes = rsncodesRepo.findDistinctStatusRsnCodes();
				model.addAttribute("datalist", data);
				model.addAttribute("rsncodes", reasonCodes);
			} catch (Exception e) {
				logger.debug("error occured while getting Zipfile Data " + zipfilename + " :" + e);
				e.printStackTrace();
			}
			return "MDT_auth_DATA";
		}
	}

	@GetMapping("/cancelMandate")
	public String cancelMandate(@ModelAttribute("user") String bank_name, User attribute, HttpSession session) {
		if (sessionChecker.isSessionExpire(session)) {
			return "redirect:/";
		} else {
			logger.info("In Cancel Mandate Page");
			if (sessionChecker.isSessionExpire(session)) {
				return "redirect:/";
			} else {
				return "cancel_Mandate";
			}
		}
	}
}
