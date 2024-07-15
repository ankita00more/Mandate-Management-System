package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.entity.BanksConfiguration;

@SpringBootApplication
public class Demo1Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
//		BanksConfiguration banksConfiguration = new BanksConfiguration();
		
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		// TODO Auto-generated method stub
		return application.sources(Demo1Application.class);
	}
}
