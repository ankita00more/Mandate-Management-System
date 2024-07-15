package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

	User findByUsernameAndPassword(String username, String password);
	
	User findByUsername(String username);
}
