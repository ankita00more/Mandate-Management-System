package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.mdt_amend_request;

@Repository
public interface MandateAmendRepository extends JpaRepository<mdt_amend_request, String> 
{
	
	mdt_amend_request findByUniqueId(String uniqueId);

}
