package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.reasonCode;

@Repository
public interface ReasonCodeRepositoryDest extends JpaRepository<reasonCode, String>{
	
	@Query(value = "SELECT DISTINCT status_rsn_code, rsn_desc, rsn_cat FROM MDT_RSN_CODES WHERE act_flag = 'Y' ORDER BY status_rsn_code ASC", nativeQuery = true)
    List<Object[]> findDistinctStatusRsnCodes();
    
}
    
    

