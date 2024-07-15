package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BanksConfiguration;

@Repository
public interface BanksConfigurationRepository extends JpaRepository<BanksConfiguration, String>{
	
	@Query(value = "Select ACH_MMS_ROOTPATH from BanksConfiguration where BANKNAME = :BANKNAME",nativeQuery=true)
	public String getRootpath(@Param("BANKNAME") String BANKNAME);
	
	
	@Query(value = "Select BANKFULLNAME from BanksConfiguration where BANKNAME = :BANKNAME",nativeQuery=true)
	public String getBankfullname(@Param("BANKNAME") String BANKNAME);
	
	
	
	

}
