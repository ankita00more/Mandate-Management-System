package com.example.demo.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.MdtSeq;


@Repository
public interface MandateSequenceRepository extends JpaRepository<MdtSeq, String>{
    
	@Query(value = "SELECT MAX(SEQ) FROM mandate_sequence WHERE BANKNAME = ?1 AND CREATION_DATE = ?2", nativeQuery = true)
    String checkMaxseqPerDayandBank(String bankName, String creationDate);

}
