package com.example.demo.Dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.BankdetailsAch;



@Repository
public interface BankDetailsAchRepository extends JpaRepository<BankdetailsAch, String>{
//	 
	 @Query(value = "SELECT SHORTCODE, BANKLOGINID FROM BANKDETAILSACH WHERE BANKNAME = :bankName", nativeQuery = true)
	 List<String> getSortandIdByBANKN(String bankName);
	 
	 @Query(value = " select CBSSERVER_IP,CBSSERVER_PORT from BANKDETAILSACH where BANKNAME= :bankName and rownum<2", nativeQuery = true)
	 List<String> getCbsDetails(String bankName);
	
}
