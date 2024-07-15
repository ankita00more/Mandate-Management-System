package com.example.demo.Dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.MdtAuthBean;

@Repository
public class DestinationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<MdtAuthBean> getMdtAuthDetails(String fromDate, String toDate, String accNo, String umrn, String bankName, String radioValue) throws SQLException {
    	
        String datequery = "select /*+ parallel(A,AUTO)*/ distinct creation_time,a.MSG_ID,a.UMRN,"
				+ "(select cat_desc from mandate_category where cat_code= a.category)  as category,"
				+ "a.status,"
				+ "(select RSN_DESC from MDT_RSN_CODES where STATUS_RSN_CODE=a.STATUS_RSN_CODE) as RSN_DESC,"
				+ "a.DEBITOR_ACC_NUM,a.CREDITOR_NAME,a.FREQUENCY,a.collctn_amt,a.MAX_AMT,"
				+ "a.FRST_COLLCTN_DT,a.FNL_COLLCTN_DT,a.CUST_NAME,a.MDT_ZIP_FILE_NAME,a.MODIFIED_TIME,b.acct_status,b.acct_type,a.MDT_DETAIL_FRNT_IMG_NAME "
				+ "from mandate_master_imd_h2h a left join acct_images b "
				+ "on a.DEBITOR_ACC_NUM=b.acct_no "
				+ "where a.bank_name=? and	"
				+ "to_date(a.creation_time,'dd-mm-yyyy hh24:mi:ss') between to_date(?,'dd-mm-yyyy hh24:mi:ss') AND to_date(?,'dd-mm-yyyy hh24:mi:ss')";
        System.out.println("datequery"+datequery);
        String AccUMRNquery = "select /*+ parallel(A,AUTO)*/ DISTINCT a.UMRN,a.msg_id,"
				+ "(select cat_desc from mandate_category where cat_code= a.category)  as category,"
				+ "a.status,"
				+ "(select RSN_DESC from MDT_RSN_CODES where STATUS_RSN_CODE=a.STATUS_RSN_CODE) as RSN_DESC,"
				+ "a.DEBITOR_ACC_NUM,a.CREDITOR_NAME,a.FREQUENCY,a.collctn_amt,a.MAX_AMT,a.FRST_COLLCTN_DT,a.FNL_COLLCTN_DT,a.CUST_NAME,"
				+ "a.MDT_ZIP_FILE_NAME,a.MODIFIED_TIME,b.acct_status,b.acct_type ,a.MDT_DETAIL_FRNT_IMG_NAME,a.creation_time "
				+ "from mandate_master_imd_h2h a left join acct_images b "
				+ "on a.DEBITOR_ACC_NUM=b.acct_no where a.bank_name=? ";
				
        String query = null;
        Object[] params = null;	
        System.out.println("RadioValue>> "+radioValue);
        if (radioValue.equalsIgnoreCase("date_radio")) {
        	System.out.println("In if...");        	
            query = datequery;
            params = new Object[]{bankName, fromDate + "00:00:00", toDate + "23:59:00"};
            System.out.println("query  for acc  --"+query);
            System.out.println("params"+params);
        } else if (radioValue.equalsIgnoreCase("acc_no_radio")) {
        	System.out.println("In 1st else if...");
            query = AccUMRNquery + " AND a.DEBITOR_ACC_NUM = ?";
            System.out.println("query  for acc  --"+query);
            System.out.println("accNo"+accNo);
            System.out.println("bankName"+bankName);
            params = new Object[]{bankName, accNo};
            System.out.println("params"+params);
        } else if (radioValue.equalsIgnoreCase("umrn_radio")) {
        	System.out.println("In 2nd else if... ");
            query = AccUMRNquery + " AND a.UMRN = ?";
            System.out.println("query  for UMRN  --"+query);
            System.out.println("URMN :"+umrn);
            params = new Object[]{bankName, umrn};
            System.out.println("params: " + Arrays.toString(params));
        }

        long startTime = System.nanoTime();
        List<MdtAuthBean> result =  jdbcTemplate.query(query, (rs, rowNum) -> {
        	MdtAuthBean mdtAuthBean = new MdtAuthBean();
            mdtAuthBean.setUmrn(rs.getString("UMRN"));
            mdtAuthBean.setStatus(rs.getString("status").equalsIgnoreCase("A") ? "Accept" :
             rs.getString("status").equalsIgnoreCase("R") ? "Reject" : "Pending");
            mdtAuthBean.setCreate_time(rs.getString("creation_time"));
            mdtAuthBean.setDEBITOR_ACC_NUM(rs.getString("DEBITOR_ACC_NUM"));
            mdtAuthBean.setCREDITOR_NAME(rs.getString("CREDITOR_NAME"));
            mdtAuthBean.setFREQUENCY(rs.getString("FREQUENCY"));
            mdtAuthBean.setMAX_AMT(rs.getString("MAX_AMT"));
            mdtAuthBean.setFRST_COLLCTN_DT(rs.getString("FRST_COLLCTN_DT"));
            mdtAuthBean.setFNL_COLLCTN_DT(rs.getString("FNL_COLLCTN_DT"));
            mdtAuthBean.setCUST_NAME(rs.getString("CUST_NAME"));
            mdtAuthBean.setMDT_ZIP_FILE_NAME(rs.getString("MDT_ZIP_FILE_NAME"));
            mdtAuthBean.setMODIFIED_TIME(rs.getString("MODIFIED_TIME"));
            mdtAuthBean.setAcct_img_status(rs.getString("acct_status"));
            mdtAuthBean.setAcct_type(rs.getString("acct_type"));
            mdtAuthBean.setMsgId(rs.getString("MSG_ID"));
            mdtAuthBean.setMdtCat(rs.getString("category"));
            mdtAuthBean.setFixedAmt(rs.getString("collctn_amt"));
            mdtAuthBean.setJpgFileName(rs.getString("MDT_DETAIL_FRNT_IMG_NAME"));
            mdtAuthBean.setRejectReason(rs.getString("RSN_DESC"));
            mdtAuthBean.setJpgImage(getJpgImageAsBase64(mdtAuthBean.getUmrn()));
         
            return mdtAuthBean;
  	        },params );
        long endTime = System.nanoTime();
        System.out.println("Query execution time: " + (endTime - startTime) + " nanoseconds");
        System.out.println("Query execution time: " + (endTime - startTime) / 1_000_000 + " milliseconds");

        return result;
  	    }  
       
    @SuppressWarnings("deprecation")
    private String getJpgImageAsBase64(String umrn) throws SQLException {
        String query = "SELECT MDT_DETAIL_FRNT_IMG FROM mandate_master_imd_h2h WHERE umrn=?";
        
        // Execute query and retrieve image as byte[]
        byte[] imageBytes = jdbcTemplate.queryForObject(query, new Object[]{umrn}, (rs, rowNum) -> {
            // Extract Blob from ResultSet
            Blob blob = rs.getBlob("MDT_DETAIL_FRNT_IMG");
            if (blob != null) {
                // Convert Blob to byte[]
                return blob.getBytes(1, (int) blob.length());
            }
            return null;
        });
        
        if (imageBytes != null) {
            // Convert byte[] to Base64-encoded String
            return Base64.getEncoder().encodeToString(imageBytes);
        }
        
        return null; // Handle case where no image is found
    }
		
}