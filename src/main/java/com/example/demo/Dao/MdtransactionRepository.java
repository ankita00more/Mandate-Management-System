package com.example.demo.Dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.mdt_transaction_request_h2h;

@Repository
public interface MdtransactionRepository  extends JpaRepository<mdt_transaction_request_h2h, String>{
	
	
	 
	 //ArrayList<mdt_transaction_request_h2h> findByBankNameAndAUTHORIZE_STATUS(String bank_name,String Auth_status);
	 
	mdt_transaction_request_h2h findByUMRN(String UMRN);
	 
	@Query(value = "SELECT * FROM MDT_TRANSACTION_REQUEST_H2H WHERE trim(AUTHORIZE_STATUS) ='A' and "
	 		+ "CREATION_DATE = TO_DATE(:CREATION_DATE, 'DD-MM-YY') and AMOUNT_TYPE='MAXIMUM'"
	 		+ " and bank_name = :bank_name",nativeQuery=true)
	 public ArrayList<mdt_transaction_request_h2h> getdata(@Param("bank_name") String bank_name,@Param("CREATION_DATE") String CREATION_DATE);
	 

		@Query(value = "SELECT * FROM MDT_TRANSACTION_REQUEST_H2H WHERE trim(AUTHORIZE_STATUS) ='A'and AMOUNT_TYPE='MAXIMUM' and "
		 		+ "CREATION_DATE = TO_DATE(:CREATION_DATE, 'DD-MM-YY')"
		 		+ " and bank_name = :bank_name",nativeQuery=true)
	   public mdt_transaction_request_h2h getnewdata(@Param("bank_name") String bank_name,@Param("CREATION_DATE") String CREATION_DATE);
		

		@Query(value = "SELECT * FROM MDT_TRANSACTION_REQUEST_H2H WHERE trim(AUTHORIZE_STATUS) ='A' and "
		 		+ "CREATION_DATE = TO_DATE(:CREATION_DATE, 'DD-MM-YY')"
		 		+ " and bank_name = :bank_name",nativeQuery=true)
	   public ArrayList<mdt_transaction_request_h2h> getinpfiledata(@Param("bank_name") String bank_name,@Param("CREATION_DATE") String CREATION_DATE);
	 
	 
		
		  @Transactional
	 	  @Modifying
		  @Query(value = "update mdt_transaction_request_h2h " +
		  "set AMOUNT= :AMOUNT, AUTHORIZE_STATUS ='A'  where UNIQUE_ID = :UNIQUE_ID and AMOUNT_TYPE = 'MAXIMUM'" +
		  "and CREATION_DATE = TO_DATE(:CREATION_DATE, 'DD-MM-YY')",nativeQuery=true)
		 
		 public int updatedata(@Param("AMOUNT") String AMOUNT,@Param("UNIQUE_ID") String UNIQUE_ID,@Param("CREATION_DATE") String CREATION_DATE);
		 
	 	@Transactional
	   	@Modifying
		@Query(value = "UPDATE MDT_TRANSACTION_REQUEST_H2H set AUTHORIZE_STATUS='Y',INPFILENAME= :INPFILENAME,"
				+ "TRANSACTION_REF_NUMBER= :TRANSACTION_REF_NUMBER  where BANK_NAME= :BANK_NAME"
				+ " and UMRN = :UMRN and AUTHORIZE_STATUS='A'and TO_CHAR(CREATION_DATE,'dd-mm-yy')= :CREATION_DATE",nativeQuery=true)
	 	public int updatefiledata(@Param("INPFILENAME") String INPFILENAME,@Param("TRANSACTION_REF_NUMBER") String TRANSACTION_REF_NUMBER,
	 			@Param("BANK_NAME") String BANK_NAME,@Param("UMRN") String UMRN,@Param("CREATION_DATE") String CREATION_DATE );
	 	
	 	
	 	
	 	
	 	@Query(value = "select count (*) from MDT_TRANSACTION_REQUEST_H2H where CREATION_DATE = TO_DATE (SYSDATE, 'DD-MM-YY') "
	 			+ "and UMRN = :UMRN",nativeQuery=true)
	 	public int getCount(@Param("UMRN") String UMRN);
	 	
	 	
	 	
		
	 	
	 	@Transactional
	 	@Modifying
	 	@Query(value = "UPDATE MDT_TRANSACTION_REQUEST_H2H SET CREATION_DATE = TO_DATE (SYSDATE, 'DD-MM-YY'),"
	 			+ "LAST_MODIFIED_DATE= TO_DATE (SYSDATE, 'DD-MM-YY'), AUTHORIZE_STATUS = 'A' WHERE UMRN = :UMRN"
	 			+ " and AUTHORIZE_STATUS is null",nativeQuery=true)
	 	public int updatetransactino(@Param("UMRN") String UMRN);
	 	
	 	
	 	@Transactional
	 	@Modifying
	 	@Query(value = "DELETE FROM mdt_transaction_request_h2h where UMRN = :UMRN AND  "
	 			+ "CREATION_DATE = TO_DATE (SYSDATE, 'DD-MM-YY')",nativeQuery=true)
	 	public int deleteumrn(@Param("UMRN") String UMRN);
	 	
	 	
		/*
		 * @Query(value =
		 * "select ACKFLAG,INPFILENAME,INPFILENAMEUPLOADTONPCI from mdt_transaction_request_h2h where CREATION_DATE = TO_DATE (:CREATION_DATE, 'DD-MM-YY')"
		 * +
		 * "AND BANK_NAME = :BANK_NAME AND ACKFLAG IN ('Accepted','Rejected','Recieved') AND AUTHORIZE_STATUS='Y'"
		 * + "AND INPFILENAME is not null",nativeQuery=true) public
		 * ArrayList<mdt_transaction_request_h2h> getinpdata(@Param("CREATION_DATE")
		 * String CREATION_DATE,@Param("BANK_NAME") String BANK_NAME);
		 * 
		 *
		 */
	 	
	 	
	 	@Query(value = "SELECT DISTINCT ACKFLAG,INPFILENAME,INPFILENAMEUPLOADTONPCI,BANK_NAME FROM mdt_transaction_request_h2h"
	 			+ " WHERE CREATION_DATE = TO_DATE (:CREATION_DATE, 'DD-MM-YY')"
	 			+ " AND BANK_NAME = :BANK_NAME "
	 			+ " AND ACKFLAG IN ('Accepted','Rejected','Recieved')"
	 			+ " AND AUTHORIZE_STATUS='Y'"
	 			+ " AND INPFILENAME IS NOT NULL "
	 			+ " GROUP BY ACKFLAG,INPFILENAME,INPFILENAMEUPLOADTONPCI,BANK_NAME",nativeQuery=true)
	 	List<Object[]> getinpdata(@Param("CREATION_DATE") String CREATION_DATE,@Param("BANK_NAME") String BANK_NAME);
	 	
	 	
	 	
	 	@Query(value = "SELECT COUNT(*) FROM mdt_transaction_request_h2h WHERE CREATION_DATE = TO_DATE (:CREATION_DATE, 'DD-MM-YY') "
	 			+ "AND BANK_NAME = :BANK_NAME AND ACKFLAG IN ('Accepted','Rejected','Recieved') AND AUTHORIZE_STATUS='Y' "
	 			+ "AND INPFILENAME is not null ",nativeQuery=true)
	 	public int getinpcount(@Param("CREATION_DATE") String CREATION_DATE,@Param("BANK_NAME") String BANK_NAME);
	 	
	 	
	 	@Query(value = "select distinct (ackflag) FROM mdt_transaction_request_h2h where "
	 			+ "BANK_NAME = :BANK_NAME and CREATION_DATE = to_Date (:CREATION_DATE,'DD-MM-YY')",nativeQuery=true)
	 	public List<String> ackflagval(@Param("BANK_NAME") String BANK_NAME,@Param("CREATION_DATE") String CREATION_DATE);
	 	
	 	
	 	@Transactional
	 	@Modifying
	 	@Query(value = "update MDT_TRANSACTION_REQUEST_H2H set AUTHORIZE_STATUS= 'A',TRANSACTION_REF_NUMBER = '',"
	 			+ "INPFILENAME = '',ACKFLAG= '',INPFILENAMEUPLOADTONPCI = '',INP_ACK_REASONCODE = '',"
	 			+ "CREATION_DATE = TO_DATE (SYSDATE, 'DD-MM-YY'),LAST_MODIFIED_DATE= TO_DATE (SYSDATE, 'DD-MM-YY') "
	 			+ "WHERE BANK_NAME= :BANK_NAME AND creation_date = to_Date (:creation_date,'DD-MM-YY') and ackflag = 'Rejected'",nativeQuery=true)
	 	public int updateInpfile(@Param("BANK_NAME") String BANK_NAME,@Param("creation_date") String creation_date);
	 	
	 	
	 
	 	@Query(value = "SELECT MI.deb_mobno FROM mdt_init_request_h2h MI JOIN mdt_transaction_request_h2h MT ON "
	 			+ " MI.UMRN = MT.UMRN WHERE MT.bank_name= :bank_name  AND MT.creation_date=TO_DATE(SYSDATE,'DD-MM-YY')"
	 			+ "AND MT.last_modified_date= TO_DATE(SYSDATE,'DD-MM-YY') AND MT.authorize_status='Y' AND  MI.deb_mobno IS NOT NULL",nativeQuery=true)
	 	public List<String>  getinpsmsdata(@Param("bank_name") String bank_name);
	 	
		@Transactional
	 	@Modifying
	 	@Query(value = "update mdt_transaction_request_h2h set sms_sent= :SMS_STATUS where DEB_MOBNO= :MOBILE_NO",nativeQuery=true)
	 	public int updatesmsstatus(@Param("SMS_STATUS") String SMS_STATUS,@Param("MOBILE_NO") String MOBILE_NO);
		
		@Query(value = "select a.unique_id,a.inpfilename,to_char(a.creation_date,'dd-mm-yyyy'),a.creditor_account_no,umrn,"
				+ "a.debtor_account_no,a.resfilename,a.reasoncode,a.amount,"
				+ "(select b.salfilename from mdt_transaction_sal_h2h b where a.resfilename = b.resfilename and b.salfilename is not null) as"
				+ " SAL_FILE_NAME from mdt_transaction_request_h2h a where a.bank_name= :bank_name  and "
				+ "to_char(a.creation_date,'dd-mm-yy') = :creation_date",nativeQuery=true)
		public List<Object[]>  getstatusdatabycredt(@Param("bank_name") String bank_name,@Param("creation_date") String creation_date );
		
		
		@Query(value = "select a.unique_id,a.inpfilename,to_char(a.creation_date,'dd-mm-yyyy'),a.creditor_account_no,umrn,"
				+ "a.debtor_account_no,a.resfilename,a.reasoncode,a.amount,"
				+ "(select b.salfilename from mdt_transaction_sal_h2h b where a.resfilename = b.resfilename and b.salfilename is not null) as"
				+ " SAL_FILE_NAME from mdt_transaction_request_h2h a where a.bank_name= :bank_name  and "
				+ "a.creditor_account_no = :creditor_acc_no",nativeQuery=true)
		public List<Object[]> getstatusbycreditoracc(@Param("bank_name") String bank_name,@Param("creditor_acc_no") String creditor_acc_no);
		
		
		@Query(value = "select unique_id,inpfilename,to_char(creation_date,'dd-mm-yyyy'),creditor_account_no,umrn,"
				+ "debtor_account_no,resfilename,reasoncode,amount from mdt_transaction_request_h2h where "
				+ "to_char(creation_date,'dd-mm-yy') = :creation_date and bank_name=:bank_name",nativeQuery = true)
		public List<Object[]>  gettodaystransstatus(@Param("creation_date") String creation_date,@Param("bank_name") String bank_name);
	 	
	 
	 
	 

	
	
}
