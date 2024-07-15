package com.example.demo.Dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Sequence_Counter;


@Repository
public interface SequenceCounterRepository extends JpaRepository<Sequence_Counter,Integer>
{
	
	@Query(value = "select NVL(max(SEQUENCE),0) as max_seq_num from Sequence_Counter where bankname = :bankname and "
			+ "CREATION_DATE = :creation_date and MDT_REQUEST='AMEND'",nativeQuery=true)
	public int getseqcount(@Param("bankname") String bankname,@Param("creation_date") String creation_date);
	
	@Query(value = "select NVL(max(SEQUENCE),0) as max_seq_num from Sequence_Counter where bankname = :bankname and "
			+ "CREATION_DATE = :creation_date and MDT_REQUEST='CANCEL'",nativeQuery=true)
	public int getcnclseqcount(@Param("bankname") String bankname,@Param("creation_date") String creation_date);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO Sequence_Counter(SEQUENCE,BANKNAME,MDT_REQUEST,CREATION_DATE) VALUES (:sequence,:bankname,:mdt_request,:creation_date)",
			nativeQuery = true)
	public int insertseq(@Param("sequence") int sequence,@Param("bankname") String bankname,@Param("mdt_request") String mdt_request,
			@Param("creation_date") String creation_date);
}
