package com.example.demo.utilities;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;

@Service
public class SessionChecker 
{
	
	public boolean isSessionExpire(HttpSession session) {
		boolean flag = false;
		User attribute = (User) session.getAttribute("user");
		if(attribute == null) 
		{
			System.out.println("Session expired");
			session.removeAttribute("user");
		    session.invalidate();
			flag = true;
		}
		else {
			flag = false;
		}
		return flag;
		
	}

}
