package com.example.demo.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.entity.mandate_master_imd_h2h;
import com.example.demo.entity.mdtBean;

@Repository
public interface AuthoriseButtonRepository extends JpaRepository<mandate_master_imd_h2h, String> {

	@Transactional
	@Modifying
	@Query(value = "update mandate_master_imd_h2h set mdt_inw_res_flag=:flag, mdt_inw_res_filename=:Resfilename, MDT_INW_RES_FOLDRNAME=:InwResFolder where umrn=:umrn", nativeQuery = true)
	int UpdateFlag(@Param("flag") String flag, @Param("Resfilename") String Resfilename,
			@Param("InwResFolder") String InwResFolder, @Param("umrn") String umrn);

	@Query(value = "SELECT UMRN, debitor_acc_num, MDT_REQ_ID, MDT_CREATION_TIME, MEMBER_ID_INSTRCTING, "
			+ "MEMBER_ID_INSTRUCTED, MSG_ID, 'pain.012.001.01' msg_nm_id, "
			+ "DECODE(status, 'A', 'true', 'R', 'false', 'false') AS status, "
			+ "STATUS_RSN_CODE AS status_reason_code, MDT_ZIP_FILE_NAME, MDT_INW_RES_FLAG, "
			+ "MDT_FRNT_IMG_NAME, SUBSTR(MDT_FRNT_IMG_NAME, -17, 6) AS seq_no, "
			+ "MDT_DETAIL_FRNT_IMG_NAME, (SELECT COUNT(umrn) FROM mandate_master_imd_h2h "
			+ "WHERE MDT_ZIP_FILE_NAME = :filename AND MDT_INW_RES_FLAG = 'N' AND BANK_NAME = :bankname) AS tot_cnt, "
			+ "collctn_amt, ISACCEPT, ISREJECT, STATUS_RSN_CODE AS other_status_reason_code "
			+ "FROM mandate_master_imd_h2h "
			+ "WHERE MDT_ZIP_FILE_NAME = :filename AND MDT_INW_RES_FLAG = 'N' AND BANK_NAME = :bankname "
			+ "ORDER BY umrn", nativeQuery = true)
	public List<Object[]> findMandatesByFilenames(@Param("filename") String filename,
			@Param("bankname") String bankname);

	@Query(value = "SELECT UMRN, debitor_acc_num, MDT_REQ_ID, MDT_CREATION_TIME, MEMBER_ID_INSTRCTING, "
			+ "MEMBER_ID_INSTRUCTED, MSG_ID, decode(action,'CREATE','pain.009.001.01','AMEND','pain.010.001.01','CANCEL','pain.011.001.01') msg_nm_id,"
			+ "DECODE(status, 'A', 'true', 'R', 'false', 'false') AS status, "
			+ "STATUS_RSN_CODE AS status_reason_code, MDT_ZIP_FILE_NAME, MDT_INW_RES_FLAG, "
			+ "MDT_FRNT_IMG_NAME, SUBSTR(MDT_FRNT_IMG_NAME, -17, 6) AS seq_no, "
			+ "MDT_DETAIL_FRNT_IMG_NAME, (SELECT COUNT(umrn) FROM mandate_master_imd_h2h "
			+ "WHERE MDT_ZIP_FILE_NAME = :filename AND MDT_INW_RES_FLAG = 'N' AND BANK_NAME = :bankname) AS tot_cnt, "
			+ "collctn_amt, ISACCEPT, ISREJECT, STATUS_RSN_CODE AS other_status_reason_code "
			+ "FROM mandate_master_imd_h2h "
			+ "WHERE MDT_ZIP_FILE_NAME = :filename AND MDT_INW_RES_FLAG = 'N' AND BANK_NAME = :bankname "
			+ "ORDER BY umrn", nativeQuery = true)

	public List<Object[]> findMandatesPain(@Param("filename") String filename,

			@Param("bankname") String bankname);

	public default boolean processMdtData(mdtBean mdt, EntityManager entityManager) {
		boolean sts = false;
		StoredProcedureQuery storedProcedure = null;
		try {
			storedProcedure = entityManager.createStoredProcedureQuery("MDT_MGMNT_H2H.MDT_MSTR_PROCESS_H2H");

			storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(9, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(10, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(11, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(12, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(13, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(14, String.class, ParameterMode.OUT);
			storedProcedure.registerStoredProcedureParameter(15, String.class, ParameterMode.OUT);
			storedProcedure.registerStoredProcedureParameter(16, String.class, ParameterMode.OUT);
			storedProcedure.setParameter(1, mdt.getUMRN());
			storedProcedure.setParameter(2, mdt.getMDT_INW_RES_FOLDRNAME());
			storedProcedure.setParameter(3, mdt.getMDT_CREATION_TIME());
			storedProcedure.setParameter(4, mdt.getINITING_PARTY_ID());
			storedProcedure.setParameter(5, mdt.getSTATUS());
			storedProcedure.setParameter(6, mdt.getSTATUS_RSN_CODE());
			storedProcedure.setParameter(7, mdt.getMDT_ZIP_FILE_NAME());
			storedProcedure.setParameter(8, mdt.getMDT_INW_RES_FLAG());
			storedProcedure.setParameter(9, mdt.getMDT_INW_RES_FILENAME());
			storedProcedure.setParameter(10, mdt.getBANK_NAME());
			storedProcedure.setParameter(11, mdt.getCREATION_TIME());
			storedProcedure.setParameter(12, mdt.getMODIFIED_TIME());
			storedProcedure.setParameter(13, mdt.getMDT_REQ_FOR());

			storedProcedure.execute();

			String res1 = (String) storedProcedure.getOutputParameterValue(14);
			String res2 = (String) storedProcedure.getOutputParameterValue(15);
			if (!"0".equals(res1)) {
				sts = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

		}
		return sts;
	}

	public default String findCustomResult(String umrn, String fileName, String zipFileName, String fileType,
			String bankName, EntityManager entityManager) {

		StoredProcedureQuery storedProcedure = null;
		String res = null;
		try {
			storedProcedure = entityManager.createStoredProcedureQuery("MDT_MGMNT_H2H.GET_MDT_DOC_H2H");
			storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.OUT);
			storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
			storedProcedure.setParameter(2, umrn);
			storedProcedure.setParameter(3, fileName);
			storedProcedure.setParameter(4, zipFileName);
			storedProcedure.setParameter(5, fileType);
			storedProcedure.setParameter(6, bankName);
			storedProcedure.execute();
			res = (String) storedProcedure.getOutputParameterValue(1);
			if (!"0".equals(res)) {
				return res;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
