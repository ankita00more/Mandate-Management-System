package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.CatCodeListRepository;
import com.example.demo.Dao.UserRepository;
import com.example.demo.entity.CatCodeList;
import com.example.demo.entity.User;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CatCodeListRepository codeList;

	public User getUser(String name, String password) {
		// TODO Auto-generated method stub
		User user = userRepo.findByUsernameAndPassword(name, password);
		return user;
	}
	
	

	public List<CatCodeList> codeList() {
		// TODO Auto-generated method stub
		List<CatCodeList> catCodeList = codeList.findAll();
		return catCodeList;
	}
	
	
}
