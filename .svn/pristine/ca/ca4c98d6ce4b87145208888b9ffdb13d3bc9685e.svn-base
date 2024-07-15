package com.example.demo.Dao;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UpdatemasterRepo {
	@Autowired
	private JdbcTemplate jdbc;

	public int deleteMandateByUmrnNo(String umrn) {
		int i=0;
		try {
			String sql="UPDATE mandate_master_imd_h2h SET status_rsn_code = 'MD16', action = 'CANCEL' WHERE umrn = ?";
					i = jdbc.update(sql, umrn);
					return i;
		} catch (Exception e) {
			System.out.println("Welcome to deleteMandateByUmrnNo error"+e.getMessage());
		e.printStackTrace();
		}
		
		return i;
	}
	
	public int updateMandate(int isReject, int isAccept, String statusRsnCodesql, String status, String umrn) {
	    try {
	        String sql = "UPDATE mandate_master_imd_h2h SET isreject = ?, isaccept = ?, status_rsn_code = ?, status = ? WHERE umrn = ?";
	        return jdbc.update(sql, isReject, isAccept, statusRsnCodesql, status, umrn);
	    } catch (Exception e) {
	        System.out.println("Welcome to updateMandate error" + e.getMessage());
	        e.printStackTrace();
	        return 0;
	    }
	}
	
	public int updateFlagg(String flag, String Resfilename, String InwResFolder, String umrn) {
	    try{
	    	String sql = "UPDATE mandate_master_imd_h2h SET mdt_inw_res_flag = ?, mdt_inw_res_filename = ?, MDT_INW_RES_FOLDRNAME = ? WHERE umrn = ?";
	    return jdbc.update(sql, flag, Resfilename, InwResFolder, umrn);
    } catch (Exception e) {
        System.out.println("Welcome to updateFlagg error" + e.getMessage());
        e.printStackTrace();
        return 0;
    }
}
	
	
	public int updateXML(int seq, String acceptFolder, String bankName, String fileDate) {
	   try { String sql = "UPDATE XML_SEQ SET FILE_SEQ = ?, ACCEPT_FOLDER = ? WHERE BANKNAME = ? AND FILE_DATE = ?";
	    int rowsAffected = jdbc.update(sql, seq, acceptFolder, bankName, fileDate);
	    return rowsAffected;
	   }catch (Exception e){
		    System.out.println("Welcome to updateXML error" + e.getMessage());
	        e.printStackTrace();
	        return 0;
	   }
	}
}