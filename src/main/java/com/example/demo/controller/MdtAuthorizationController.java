package com.example.demo.controller;

import java.util.List;
import com.example.demo.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.Service.AuthoriseButtonService;
import com.example.demo.Service.mdtAuthService;
import com.example.demo.entity.mandate_master_imd_h2h;
import com.example.demo.entity.mdtBean;
import com.example.demo.utilities.SessionChecker;

@Controller
public class MdtAuthorizationController {
	private final Logger logger = Logger.getLogger(MdtAuthorizationController.class);

	@Autowired
	mdtAuthService mdtAuthService;

	@Autowired
	SessionChecker sessionChecker;
	@Autowired
	AuthoriseButtonService mdtaccptauth;

	@Value("${WAR_NAME}")
	String WAR_NAME;

	@PostMapping("/auth")
	public String acceptMdt(@ModelAttribute("Customer") mandate_master_imd_h2h Customer,
			@RequestParam("rsncodes") String[] rsncodes, @RequestParam("accept_chk") String[] accept_chk,
			@RequestParam("reject_chk") String[] reject_chk, @RequestParam("umrn") List<String> umrn, Model model,
			HttpSession session, RedirectAttributes redirectAttributes, mandate_master_imd_h2h mandatedata,
			HttpServletRequest request) {
		if (sessionChecker.isSessionExpire(session)) {
			return "redirect:/";
		} else {
			try {
				logger.info("Data Selected for Authorization process :");
				User attribute = (User) session.getAttribute("user");
				String bankname = attribute.getBankname();
				String zipName = ((String) session.getAttribute("zipfilename"));
				String Action = ((String) session.getAttribute("action"));
				request.setAttribute(zipName, "zipfilename");
				mdtBean MDT = new mdtBean();
				MDT.setBANK_NAME(bankname);
				MDT.setMDT_REQ_FOR(Action);
				MDT.setMDT_ZIP_FILE_NAME(zipName);
				mdtaccptauth.AccptMdt(MDT, bankname, zipName, model, rsncodes, accept_chk, reject_chk);
			} catch (Exception e) {
				logger.error("error occured while getting data for Authorization " + e);
				e.printStackTrace();
			}
			return "redirect:/processMandate";
		}
	}
}