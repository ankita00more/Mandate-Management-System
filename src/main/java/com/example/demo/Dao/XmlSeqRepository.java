package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.xmlSeq;

@Repository
public interface XmlSeqRepository extends JpaRepository<xmlSeq, String>{
	
	//MOnika original code
	   @Query(value = "SELECT FILE_SEQ FROM XML_SEQ WHERE FILE_DATE = :fileDate AND BANKNAME = :bankName", nativeQuery = true)
	    String findFileSeqByFileDateAndBankName(@Param("fileDate") String fileDate, @Param("bankName") String bankName);
	
	   @Query(value = "SELECT * FROM XML_SEQ WHERE FILE_DATE = :fileDate AND BANKNAME = :bankName", nativeQuery = true)
	   xmlSeq findByIdandDate(@Param("fileDate") String fileDate, @Param("bankName") String bankName);	
	
}
