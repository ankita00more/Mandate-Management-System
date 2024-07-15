package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BankDetailsNach;


@Repository
public interface BankDetailsNachRepository extends JpaRepository<BankDetailsNach, Long>{

	BankDetailsNach findByBankname(String bankname);
	


}
