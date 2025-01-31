package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.example.demo.Dao.BanksConfigurationRepository;
import com.example.demo.Dao.CatCodeListRepository;
import com.example.demo.Dao.MdtValidityRepository;
import com.example.demo.Dao.ReasonCodeRepository;
import com.example.demo.Service.EncryptionServiceImpl;
import com.example.demo.Service.LoginServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.utilities.SessionChecker;
import com.example.demo.entity.CatCodeList;
import com.example.demo.entity.MdtInitRequestH2h;

@Controller
public class LoginController {

	@Autowired
	private LoginServiceImpl loginService;

	@Autowired
	private CatCodeListRepository codeList;

	@Autowired
	BanksConfigurationRepository configRepo;

	@Autowired
	MdtValidityRepository validityrepo;
	
	@Value("${app.maxsessiontime}")
	int sessiontime;

	@Autowired
	ReasonCodeRepository rsn_code;

	@Autowired
	SessionChecker sessionChecker;
	
	@Autowired
	EncryptionServiceImpl encryptionService;

	private final Logger logger = Logger.getLogger(LoginController.class);

	public static Set<String> activeLoginNames = new HashSet<>();

	@GetMapping(value = "/")
	public String login() {
		logger.debug("Entering login page");
		return "login";
	}

	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {

		if (sessionChecker.isSessionExpire(session)) {
			logger.debug("Session is invalid");
			return "redirect:/";
		} else {
			User user = (User) session.getAttribute("user");
			String username = user.getUsername();
			activeLoginNames.remove(username);
			session.invalidate();
			return "login";

		}

	}

	@PostMapping("/login")
	public String loginaction(@RequestParam("username") String name, @RequestParam("password") String password,
			Model model, HttpServletRequest request, @RequestParam("opt") String option, HttpSession session) 
	{
		String decryptedpass = EncryptionServiceImpl.decrypt(password);
		session.setAttribute("option",option);
		if (option.equalsIgnoreCase("Sponsor")) 
		{
			HttpSession sponsorSession = request.getSession();
			sponsorSession.setAttribute("sponsorSession", true);
			sponsorSession.setMaxInactiveInterval(sessiontime);
			User user = loginService.getUser(name,decryptedpass);
			if (user != null && name.equals(user.getUsername()) && decryptedpass.equals(user.getPassword())
					&& activeLoginNames.add(name + option)) 
			{
				String banknamefullname = configRepo.getBankfullname(user.getBankname());

				sponsorSession.putValue("loginName", name + option);
				activeLoginNames.add(name + option);
				sponsorSession.setAttribute("user", user);
				sponsorSession.setAttribute("bankfullname", banknamefullname);

				String passflag = user.getPass_flag();
				if (passflag.equals("0")) 
				{
					logger.info("Changing  password: " + user.getBankname() + " for: " + option);
					return "redirect:/changepass";
				} 
				else 
				{
					MdtInitRequestH2h mdtInitRequestH2h = new MdtInitRequestH2h();
					model.addAttribute("customer", mdtInitRequestH2h);
					List<CatCodeList> catCodeList = codeList.findAll();
					model.addAttribute("cat_code", catCodeList);
					logger.info("Logged in: " + user.getBankname() + " for: " + option);
					return "redirect:/Y3JlYXRl";
					
				}
			} 
			else if (user != null && !activeLoginNames.add(name + option)) 
			{

				long loginInitial = sponsorSession.getCreationTime();

				long lastAccesed = sponsorSession.getLastAccessedTime();
				
				long timeDiff = (lastAccesed - loginInitial) / 1000L;
				
				logger.info("Time diff :"+timeDiff);

				int maxSec = (int) timeDiff;

				if (maxSec < sessiontime) {

					int difference = sponsorSession.getMaxInactiveInterval() - maxSec;
					System.out.println("Difference in time:"+difference);
					int diffSeconds = difference /60;
					String err = "User has already Logged in. You can log in now after " + difference + " seconds.";
					model.addAttribute("msg", err);
					return "login";
				} else {
					if (sponsorSession != null) {
						sponsorSession.invalidate();
					}

					return "login";
				}
			} else {
				String msg = "Invalid Username or password !!!";
				model.addAttribute("msg", msg);
				return "login";
			}

		} else if (option.equalsIgnoreCase("Destination")) {
			HttpSession destinationSession = request.getSession();
			destinationSession.setAttribute("destinationSession", true);
			destinationSession.setMaxInactiveInterval(sessiontime);
			User user = loginService.getUser(name,decryptedpass);
			if (user != null && name.equals(user.getUsername()) && decryptedpass.equals(user.getPassword())
					&& activeLoginNames.add(name + option)) {
				String banknamefullname = configRepo.getBankfullname(user.getBankname());
				destinationSession.putValue("loginName", name + option);
				activeLoginNames.add(name + option);
				destinationSession.setAttribute("user", user);
				destinationSession.setAttribute("bankfullname", banknamefullname);

				String passflag = user.getPass_flag();

				if (passflag.equals("0")) {
					logger.info("Changing  password: " + user.getBankname() + " for: " + option);
					return "redirect:/changepass";
				} else {
					MdtInitRequestH2h mdtInitRequestH2h = new MdtInitRequestH2h();
					model.addAttribute("customer", mdtInitRequestH2h);
					List<CatCodeList> catCodeList = codeList.findAll();
					model.addAttribute("cat_code", catCodeList);
					logger.info("Logged in: " + user.getBankname() + " for: " + option);
					return "redirect:/processMandate";
				}

			} else if (user != null && !activeLoginNames.add(name + option)) {
				long loginInitial = destinationSession.getCreationTime();
				long lastAccesed = destinationSession.getLastAccessedTime();
				long timeDiff = (lastAccesed - loginInitial) / 1000L;
				int maxSec = (int) timeDiff;
				if (maxSec < sessiontime) {
					int difference = destinationSession.getMaxInactiveInterval() - maxSec;
					int diffSeconds = difference / 60;
					String err = "User has already Logged in. You can log in now after " + difference + " seconds.";
					model.addAttribute("msg", err);
					return "login";
				} else {
					if (destinationSession != null) {
						destinationSession.invalidate();
					}
					return "login";
				}
			} else {
				String msg = "Invalid Username or password !!!";
				model.addAttribute("msg", msg);
				return "login";
			}
		} else {
			String msg = "Valid option not selected !!!";
			model.addAttribute("msg", msg);
			return "login";

		}
	}
}