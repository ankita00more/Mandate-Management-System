package com.example.demo.Dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.AcctImages;

@Repository
public interface AcctImagesRepository extends JpaRepository<AcctImages, String>{
	
	@Query(value = "SELECT * FROM acct_images WHERE ACCT_NO = :debtorAccNum", nativeQuery = true)
	List<Object[]> getCBSData(@Param("debtorAccNum") String debtorAccNum);
	
	
	@Query(value = "SELECT IMG_SG FROM acct_images WHERE ACCT_NO = :debtorAccNum", nativeQuery = true)
	List<Object[]> getCBSdata1(@Param("debtorAccNum") String debtorAccNum);

}
