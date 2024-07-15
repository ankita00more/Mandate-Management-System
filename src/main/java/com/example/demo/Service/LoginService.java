package com.example.demo.Service;

import java.util.List;

import com.example.demo.entity.CatCodeList;
import com.example.demo.entity.User;

public interface LoginService {

	public User getUser(String name, String password);
	
	public List<CatCodeList> codeList();
}
