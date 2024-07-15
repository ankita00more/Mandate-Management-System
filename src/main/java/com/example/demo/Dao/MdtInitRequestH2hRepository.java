package com.example.demo.Dao;


import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.MdtInitRequestH2h;

@Repository
public interface MdtInitRequestH2hRepository extends JpaRepository<MdtInitRequestH2h, String>{
	
	MdtInitRequestH2h findByUniqueId(String uniqueId);
	
	MdtInitRequestH2h findByUmrn(String UMRN);
	
		
	@Query(value ="select NVL(max(seq_no),0) from MDT_INIT_REQUEST_H2H where bank_name = :bank_name and creation_date = TO_DATE (SYSDATE, 'DD-MM-YY')", nativeQuery = true)
	public int getSeqNo(@Param("bank_name") String bank_name);
	
	
	
	@Query(value = "SELECT unique_id FROM MDT_INIT_REQUEST_H2H " +
            "WHERE debtor_account_no = :debtor_account_no " +
            "AND mdt_request != 'CANCEL' " +
            "AND bank_name = :bank_name",
            nativeQuery = true)
	public List<String> getuniqueidbydebtor_account_no(@Param("debtor_account_no") String debtor_account_no,@Param("bank_name") String bank_name);
	
	@Query(value = "SELECT unique_id FROM MDT_INIT_REQUEST_H2H " +
            "WHERE umrn = :umrn " +
            "AND mdt_request != 'CANCEL' " +
            "AND bank_name = :bank_name",
            nativeQuery = true)
	public List<String> getuniqueidbyumrn(@Param("umrn") String umrn,@Param("bank_name") String bank_name);
	
	@Query(value = "SELECT unique_id FROM MDT_INIT_REQUEST_H2H " +
            "WHERE unique_id = :unique_id " +
            "AND mdt_request != 'CANCEL' " +
            "AND bank_name = :bank_name",
            nativeQuery = true)
	public List<String> getuniqueid(@Param("unique_id") String unique_id,@Param("bank_name") String bank_name);
	
	@Query(value = "Insert into MDT_INIT_REQUEST_H2H_BKP (select UNIQUE_ID,TRANSACTION_TYPE,OCCURENCE,FREQUENCY,"
			+ "FIRST_COLLECTION_DATE,FINAL_COLLECTION_DATE,AMOUNT_TYPE,AMOUNT,CREDITOR_NAME,CREDITOR_UTILITY_CODE,"
			+ "CREDITOR_IDENTIFICATION,CREDITOR_IDENTIFICATION_NO,DEBTOR_NAME,DEBTOR_ACCOUNT_NO,DEBTOR_ACCOUNT_TYPE,"
			+ "DEBTOR_BANK_NAME,DEBTOR_IDENTIFICATION,UMRN,STATUS,BENIFICIARY_STATUS,REJECT_REASON,"
			+ "DEBTOR_IDENTIFICATION_NO,SEQ_NO,CREATION_DATE,LAST_MODIFIED_DATE,MDT_REQUEST,BANK_NAME,"
			+ "JPG_IMAGE,TIFF_IMAGE,REASON_CODE,CATEGORY_CODE,CREDITOR_ACCOUNT_NO,CREDITOR_ACCOUNT_NO_TYPE "
			+ "from MDT_INIT_REQUEST_H2H where mdt_init_request_h2h.unique_id = :unique_id",nativeQuery=true)
	public void getdatabkp(@Param("unique_id") String unique_id);
		

	@Query(value = "select m.* "
			+ "from mdt_init_request_h2h m where m.bank_name= :bank_name and "
			+ "m.creation_date between TO_DATE(:fromdt,'dd-MM-YY') AND TO_DATE(:todate,'dd-MM-YY')",nativeQuery=true)
	public List<MdtInitRequestH2h> getmandatestatus(@Param("bank_name") String bank_name,@Param("fromdt") String fromdt,@Param("todate") String todate);
	
	
	@Query(value = "select rsn_desc from mdt_rsn_codes where status_rsn_code = :status_rsn_code",nativeQuery=true)
	public String getreasondesc(@Param("status_rsn_code") String status_rsn_code);
		
}
	
	

	

