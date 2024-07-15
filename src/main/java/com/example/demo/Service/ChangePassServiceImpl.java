package com.example.demo.Service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.UserRepository;
import com.example.demo.controller.ErrorController;
import com.example.demo.entity.User;

@Service
public class ChangePassServiceImpl implements ChangePassService
{
	@Autowired
	private UserRepository userRepo;
	
	private final Logger logger = Logger.getLogger(ChangePassServiceImpl.class);
	public boolean ChangePass(String username,String newpassword) 
	{
		boolean flag = false;
		try
		{
			User user = userRepo.findByUsername(username);
			
			user.setPass_flag("1");
			user.setPassword(newpassword);
			
			if(userRepo.save(user) != null) 
			{
				flag = true;
			}
			else 
			{
				flag = false;
			}
			
			
		
		}
		catch(Exception e)
		{
			flag = false;
			logger.error("Exception occurred while changing password");
		}
		
		return flag;
	}

}
