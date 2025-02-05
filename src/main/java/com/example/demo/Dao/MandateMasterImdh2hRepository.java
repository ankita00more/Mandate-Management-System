package com.example.demo.Dao;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.mandate_master_imd_h2h;

@Repository
public interface MandateMasterImdh2hRepository extends JpaRepository<mandate_master_imd_h2h, String> {

	mandate_master_imd_h2h findByUMRN(String UMRN);

	@Query(value = "SELECT * FROM MANDATE_MASTER_IMD_H2H WHERE MDT_FRNT_IMG_NAME = :imageName", nativeQuery = true)
	mandate_master_imd_h2h findByMDT_FRNT_IMG_NAME(@Param("imageName") String imageName);

	 @Query(value = "SELECT DISTINCT mdt_zip_file_name AS mdt_zip_file_name, " +
	 "action,TRUNC(TO_DATE(CREATION_TIME, 'DD-MM-YYYY HH24:MI:SS')) AS CREATION_DATE " +
	 "FROM mandate_master_imd_h2h " +
	 "WHERE bank_name = ?1 AND mdt_inw_res_flag = 'N' AND status = 'I' AND action <> 'CANCEL' AND TRUNC(TO_DATE(CREATION_TIME, 'DD-MM-YYYY HH24:MI:SS')) >= "
	 + "TO_DATE(TO_CHAR(SYSDATE, 'YYYY') || '-04-01', 'YYYY-MM-DD')", nativeQuery = true)
	public List<Object[]> findDistinctByBankNameAndFlags(@Param("BANK_NAME") String BANK_NAME);

	@Query(value = "SELECT UMRN, debitor_acc_num, MDT_REQ_ID, MDT_CREATION_TIME, MEMBER_ID_INSTRCTING, "
			+ "MEMBER_ID_INSTRUCTED, MSG_ID, 'pain.012.001.01' msg_nm_id, "
			+ "DECODE(status, 'A', 'true', 'R', 'false', 'false') AS status, "
			+ "STATUS_RSN_CODE AS status_reason_code, MDT_ZIP_FILE_NAME, MDT_INW_RES_FLAG, "
			+ "MDT_FRNT_IMG_NAME, SUBSTR(MDT_FRNT_IMG_NAME, -17, 6) AS seq_no, "
			+ "MDT_DETAIL_FRNT_IMG_NAME, (SELECT COUNT(umrn) FROM mandate_master_imd_h2h "
			+ "WHERE MDT_ZIP_FILE_NAME = :filename AND MDT_INW_RES_FLAG = 'N' AND BANK_NAME = :bankname) AS tot_cnt, "
			+ "collctn_amt, ISACCEPT, ISREJECT, STATUS_RSN_CODE AS other_status_reason_code "
			+ "FROM mandate_master_imd_h2h "
			+ "WHERE MDT_ZIP_FILE_NAME = :filename AND MDT_INW_RES_FLAG = 'N' AND BANK_NAME = :bankname AND  action<>'CANCEL' "
			+ "ORDER BY umrn", nativeQuery = true)
	public List<Object[]> findMandatesByFilename(@Param("filename") String filename,
			@Param("bankname") String bankname);

	@Query(value = "SELECT UMRN,  FROM mandate_master_imd_h2h WHERE MDT_ZIP_FILE_NAME = :filename AND BANK_NAME = :bankname ORDER BY UMRN", nativeQuery = true)
	ArrayList<mandate_master_imd_h2h> findByMdtZipFileNameAndBankName(@Param("filename") String filename,
			@Param("bankname") String bankname);

	@Query(value = "SELECT umrn,msg_id, proprietary, collctn_amt, max_amt, category, "
			+ "frequency, frst_collctn_dt, fnl_collctn_dt, cust_name, cust_mobile_num, "
			+ "creditor_name, cust_email_adr, debitor_acc_num "
			+ "FROM mandate_master_imd_h2h WHERE umrn = :umrn", nativeQuery = true)
	List<Object[]> viewDetailByUMRN(@Param("umrn") String umrn);

	@Query(value = "SELECT BANKFULLNAME FROM BANKSCONFIGURATION WHERE BANKNAME = :bankName", nativeQuery = true)
	String getBankfullNam(String bankName);

	@Query(value = "SELECT mm.umrn AS UMRN, mm.MDT_DETAIL_FRNT_IMG_NAME AS name, mm.MDT_DETAIL_FRNT_IMG AS doc "
			+ "FROM mandate_master_imd_h2h mm " + "WHERE mm.BANK_NAME = :bankName "
			+ "AND mm.MDT_ZIP_FILE_NAME = :zipFileName "
			+ "AND mm.MDT_DETAIL_FRNT_IMG_NAME = :fileName", nativeQuery = true)
	List<Object[]> getJPEGDocuments(@Param("bankName") String bankName, @Param("zipFileName") String zipFileName,
			@Param("fileName") String fileName);

	@Query(value = "SELECT mm.umrn AS UMRN, mm.MDT_FRNT_IMG_NAME AS name, mm.MDT_FRNT_IMG AS doc "
			+ "FROM mandate_master_imd_h2h mm " + "WHERE mm.BANK_NAME = :bankName "
			+ "AND mm.MDT_ZIP_FILE_NAME = :zipFileName " + "AND mm.MDT_FRNT_IMG_NAME = :fileName", nativeQuery = true)
	List<Object[]> getTIFFDocuments(@Param("bankName") String bankName, @Param("zipFileName") String zipFileName,
			@Param("fileName") String fileName);

	@Modifying
	@Query(value = "{call MDT_MGMNT_H2H.MDT_MSTR_PROCESS_H2H(:iUmrn, :iMdtInwResFolderName, :iMdtCreationTime, :iInitingPartyId, :iStatus, :iStatusRsnCode, :iMdtZipFileName, :iMdtInwResFlag, :iMdtInwResFilename, :iBankName, :iCreationTime, :iModifiedTime, :iOpt, :opRes, :opMsg, :opMsgNo)}", nativeQuery = true)
	boolean callMdtMstrProcessH2H(@Param("iUmrn") String iUmrn,
			@Param("iMdtInwResFolderName") String iMdtInwResFolderName,
			@Param("iMdtCreationTime") String iMdtCreationTime, @Param("iInitingPartyId") String iInitingPartyId,
			@Param("iStatus") String iStatus, @Param("iStatusRsnCode") String iStatusRsnCode,
			@Param("iMdtZipFileName") String iMdtZipFileName, @Param("iMdtInwResFlag") String iMdtInwResFlag,
			@Param("iMdtInwResFilename") String iMdtInwResFilename, @Param("iBankName") String iBankName,
			@Param("iCreationTime") String iCreationTime, @Param("iModifiedTime") String iModifiedTime,
			@Param("iOpt") String iOpt, @Param("opRes") String opRes, @Param("opMsg") String opMsg,
			@Param("opMsgNo") String opMsgNo);

	@Query(value = "SELECT umrn, debitor_acc_num FROM mandate_master_imd_h2h "
			+ "WHERE mdt_zip_file_name = ?1 AND isaccept = 1 AND status = 'A'", nativeQuery = true)
	List<mandate_master_imd_h2h> findByZipFileNameAndIsAcceptAndStatus(String zipFileName);

	@Transactional
	@Modifying
	@Query(value = "UPDATE mandate_master_imd_h2h SET status_rsn_code = 'MD16', action = 'CANCEL' WHERE umrn = ?1", nativeQuery = true)
	int cancelMandateByUmrn(String umrn);

	@Query(value = "SELECT umrn,cust_name, creditor_name, debitor_acc_num, frst_collctn_dt, fnl_collctn_dt "
			+ "FROM mandate_master_imd_h2h "
			+ "WHERE action = 'CREATE' AND bank_name = :bankName AND umrn = :umrn", nativeQuery = true)
	List<Object[]> findByBankNameAndUmrn(@Param("bankName") String bankName, @Param("umrn") String umrn);

	@Query(value = "SELECT msg_id, mdt_req_id, member_id_instrcting, member_name_instrcting, "
			+ "member_id_instructed, member_name_instructed, debitor_acc_num "
			+ "FROM mandate_master_imd_h2h WHERE umrn = ?1", nativeQuery = true)
	List<Object[]> findByUmrn(String umrn);

	@Query(value = "SELECT ACTION FROM mandate_master_imd_h2h WHERE MDT_ZIP_FILE_NAME = :filename AND BANK_NAME = :bankname", nativeQuery = true)
	List<String> findByMdtZipFileNameAction(@Param("filename") String filename, @Param("bankname") String bankname);

	@Query(value = "SELECT msg_id, mdt_req_id, member_id_instrcting, member_name_instrcting, "
			+ "member_id_instructed, member_name_instructed, debitor_acc_num "
			+ "FROM mandate_master_imd_h2h WHERE umrn = :umrn", nativeQuery = true)
	ArrayList<String> selectDatabyUmrn(@Param("umrn") String umrn);

}
