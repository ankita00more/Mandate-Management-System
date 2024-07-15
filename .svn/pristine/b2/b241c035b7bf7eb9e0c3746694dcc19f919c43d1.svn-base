package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Mdt_rsn_codes;

@Repository
public interface ReasonCodeRepository extends JpaRepository<Mdt_rsn_codes,String> {
	
	@Query(value = "SELECT * FROM mdt_rsn_codes where ACT_FLAG = 'Y' ",nativeQuery = true)
	List<Mdt_rsn_codes> getdata();
	

}
