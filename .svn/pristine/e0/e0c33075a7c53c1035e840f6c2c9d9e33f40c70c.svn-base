package com.example.demo.Dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.inputdebitsequence_h2h;



@Repository
public interface InputDebitSequenceRepository extends JpaRepository<inputdebitsequence_h2h,String> {
	
	@Query(value = "select count(seqno) from inputdebitsequence_h2h where bankname= :bankname and sdate= :sdate",nativeQuery = true)
	public int getseqcount(@Param("bankname") String bankname,@Param("sdate") String sdate);
	
	@Query(value = "select seqno from inputdebitsequence_h2h where bankname= :bankname and sdate= :sdate",nativeQuery = true)
	public int getcount(@Param("bankname") String bankname, @Param("sdate") String sdate );
	
	@Transactional
    @Modifying
	@Query(value = "UPDATE inputdebitsequence_h2h set seqno= :seqno where  bankname = :bankname ",nativeQuery = true)
	public int updatedata(@Param("seqno") int seqno,@Param("bankname") String bankname);

}
