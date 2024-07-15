package com.example.demo.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.util.Comparator;


public class Customerlist implements Serializable,Comparator<Date> {
	

	private static long serialVersionUID = 1L;
	private String CREDITOR_NAME;
	private String bankloginid;
	private String shortcode;
	private Date creationdate;
	private Date lastmodifieddate;
	private String BANKNAME;
	private Blob jpg_img;
	private Blob tiff_img;
	private String DEBTOR_BANK_NAME ;
	private String DEBTOR_IDENTIFICATION ;
	private String DEBTOR_IDENTIFICATION_NO ;
	private	String Status;
	private	String Benificiary_Status;
	private	String Reject_Reason;
	private String CREDITOR_UTILITY_CODE;
	private String CREDITOR_IDENTIFICATION;
	private String CREDITOR_IDENTIFICATION_NO;
	private String UNIQUE_ID;
	private String REASON_CODE;
	private String INPFILENAME;
	private String category_Code;
	private String CREDITOR_ACCOUNT_NO;
	private String TRANSACTION_TYPE ;
	private String OCCURENCE ;
	private String FREQUENCY ;
	private Date FIRST_COLLECTION_DATE ;
	private Date FINAL_COLLECTION_DATE ;
	//CHANGES --> 27/09/2023
	private Date AMEND_DT;
	
	private String AMOUNT_TYPE ;
	private String AMOUNT ;
	private String DEBTOR_NAME ;
	private String DEBTOR_ACCOUNT_NO ;
	private String DEBTOR_ACCOUNT_TYPE ;
	public boolean FLAG;
	private String mdt_rsn_codes;
	private String CREDITOR_ACCOUNT_NO_TYPE;
	private String UMRN ;
	private Date LAST_TRANSACTION_DATE ;
	private String AUTHORIZE_STATUS;
	private String UNTIL_CANCEL;
	private String DEBTOR_MOBILE_NO;
	private String DEBTOR_EMAIL_ID;
	private Date Mandate_Date;
	
	
	
	public Date getMandate_Date() {
		return Mandate_Date;
	}
	public void setMandate_Date(Date mandate_Date) {
		Mandate_Date = mandate_Date;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public static void setSerialVersionUID(long serialVersionUID) {
		Customerlist.serialVersionUID = serialVersionUID;
	}
	
	public String getBANKNAME() {
		return BANKNAME;
	}
	public void setBANKNAME(String bANKNAME) {
		BANKNAME = bANKNAME;
	}
	
	public String getDEBTOR_MOBILE_NO() {
		return DEBTOR_MOBILE_NO;
	}
	public void setDEBTOR_MOBILE_NO(String dEBTOR_MOBILE_NO) {
		DEBTOR_MOBILE_NO = dEBTOR_MOBILE_NO;
	}
	public String getDEBTOR_EMAIL_ID() {
		return DEBTOR_EMAIL_ID;
	}
	public void setDEBTOR_EMAIL_ID(String dEBTOR_EMAIL_ID) {
		DEBTOR_EMAIL_ID = dEBTOR_EMAIL_ID;
	}
	public Date getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(Date date1) {
		this.creationdate = date1;
	}
	public Date getLastmodifieddate() {
		return lastmodifieddate;
	}
	public void setLastmodifieddate(Date lastmodifieddate) {
		this.lastmodifieddate = lastmodifieddate;
	}
	public String getMdt_rsn_codes() {
		return mdt_rsn_codes;
	}
	public void setMdt_rsn_codes(String mdt_rsn_codes) {
		this.mdt_rsn_codes = mdt_rsn_codes;
	}
	public String getCREDITOR_ACCOUNT_NO_TYPE() {
		return CREDITOR_ACCOUNT_NO_TYPE;
	}
	public void setCREDITOR_ACCOUNT_NO_TYPE(String cREDITOR_ACCOUNT_NO_TYPE) {
		CREDITOR_ACCOUNT_NO_TYPE = cREDITOR_ACCOUNT_NO_TYPE;
	}
	public String getCREDITOR_ACCOUNT_NO() {
		return CREDITOR_ACCOUNT_NO;
	}
	public void setCREDITOR_ACCOUNT_NO(String cREDITOR_ACCOUNT_NO) {
		CREDITOR_ACCOUNT_NO = cREDITOR_ACCOUNT_NO;
	}
	private boolean flag,checkMdt;
	
	
	public boolean isCheckMdt() {
		return checkMdt;
	}
	public void setCheckMdt(boolean checkMdt) {
		this.checkMdt = checkMdt;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	private String Bank_name;
	
	public String getBankloginid() {
		return bankloginid;
	}
	public void setBankloginid(String bankloginid) {
		this.bankloginid = bankloginid;
	}
	public String getShortcode() {
		return shortcode;
	}
	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}
	private String seq_num;
	public String getSeq_num() {
		return seq_num;
	}
	public void setSeq_num(String seq_num) {
		this.seq_num = seq_num;
	}
	
	public String getCREDITOR_NAME() {
		return CREDITOR_NAME;
	}
	public void setCREDITOR_NAME(String cREDITOR_NAME) {
		CREDITOR_NAME = cREDITOR_NAME;
	}
	public String getCREDITOR_UTILITY_CODE() {
		return CREDITOR_UTILITY_CODE;
	}
	public void setCREDITOR_UTILITY_CODE(String cREDITOR_UTILITY_CODE) {
		CREDITOR_UTILITY_CODE = cREDITOR_UTILITY_CODE;
	}
	public String getCREDITOR_IDENTIFICATION() {
		return CREDITOR_IDENTIFICATION;
	}
	public void setCREDITOR_IDENTIFICATION(String cREDITOR_IDENTIFICATION) {
		CREDITOR_IDENTIFICATION = cREDITOR_IDENTIFICATION;
	}
	public String getCREDITOR_IDENTIFICATION_NO() {
		return CREDITOR_IDENTIFICATION_NO;
	}
	public void setCREDITOR_IDENTIFICATION_NO(String cREDITOR_IDENTIFICATION_NO) {
		CREDITOR_IDENTIFICATION_NO = cREDITOR_IDENTIFICATION_NO;
	}
	
	public String getUNIQUE_ID() {
		return UNIQUE_ID;
	}
	public void setUNIQUE_ID(String unique_id) {
		this.UNIQUE_ID = unique_id;
	}
	
	//CHANGES --> 27/09/2023
	public Date getAMEND_DT() {
		return AMEND_DT;
	}
	public void setAMEND_DT(Date aMEND_DT) {
		AMEND_DT = aMEND_DT;
	}
	//CHANGES --> 27/09/2023
	public void setFIRST_COLLECTION_DATE(Date fIRST_COLLECTION_DATE) {
		FIRST_COLLECTION_DATE = fIRST_COLLECTION_DATE;
	}
	public Date getFIRST_COLLECTION_DATE() {
		return FIRST_COLLECTION_DATE;
	}
	public void setFIRST_COLLECTION_DATE1(Date fIRST_COLLECTION_DATE) {
		FIRST_COLLECTION_DATE = fIRST_COLLECTION_DATE;
	}
	public Date getFIRST_COLLECTION_DATE1() {
		return FIRST_COLLECTION_DATE;
	}
	public Date getFINAL_COLLECTION_DATE() {
		return FINAL_COLLECTION_DATE;
	}
	public void setFINAL_COLLECTION_DATE(Date fINAL_COLLECTION_DATE) {
		FINAL_COLLECTION_DATE = fINAL_COLLECTION_DATE;
	}

	public String getBank_name() {
		return Bank_name;
	}
	public void setBank_name(String bank_name) {
		Bank_name = bank_name;
	}
	public String getTRANSACTION_TYPE() {
		return TRANSACTION_TYPE;
	}
	public void setTRANSACTION_TYPE(String tRANSACTION_TYPE) {
		TRANSACTION_TYPE = tRANSACTION_TYPE;
	}
	public String getOCCURENCE() {
		return OCCURENCE;
	}
	public void setOCCURENCE(String oCCURENCE) {
		OCCURENCE = oCCURENCE;
	}
	public String getFREQUENCY() {
		return FREQUENCY;
	}
	public void setFREQUENCY(String fREQUENCY) {
		FREQUENCY = fREQUENCY;
	}
	public String getAMOUNT_TYPE() {
		return AMOUNT_TYPE;
	}
	public void setAMOUNT_TYPE(String aMOUNT_TYPE) {
		AMOUNT_TYPE = aMOUNT_TYPE;
	}
	public String getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(String aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public String getDEBTOR_NAME() {
		return DEBTOR_NAME;
	}
	public void setDEBTOR_NAME(String dEBTOR_NAME) {
		DEBTOR_NAME = dEBTOR_NAME;
	}
	public String getDEBTOR_ACCOUNT_NO() {
		return DEBTOR_ACCOUNT_NO;
	}
	public void setDEBTOR_ACCOUNT_NO(String dEBTOR_ACCOUNT_NO) {
		DEBTOR_ACCOUNT_NO = dEBTOR_ACCOUNT_NO;
	}
	public String getDEBTOR_ACCOUNT_TYPE() {
		return DEBTOR_ACCOUNT_TYPE;
	}
	public void setDEBTOR_ACCOUNT_TYPE(String dEBTOR_ACCOUNT_TYPE) {
		DEBTOR_ACCOUNT_TYPE = dEBTOR_ACCOUNT_TYPE;
	}
	public String getDEBTOR_BANK_NAME() {
		return DEBTOR_BANK_NAME;
	}
	public void setDEBTOR_BANK_NAME(String dEBTOR_BANK_NAME) {
		DEBTOR_BANK_NAME = dEBTOR_BANK_NAME;
	}
	public String getDEBTOR_IDENTIFICATION() {
		return DEBTOR_IDENTIFICATION;
	}
	public void setDEBTOR_IDENTIFICATION(String dEBTOR_IDENTIFICATION) {
		DEBTOR_IDENTIFICATION = dEBTOR_IDENTIFICATION;
	}
	public String getDEBTOR_IDENTIFICATION_NO() {
		return DEBTOR_IDENTIFICATION_NO;
	}
	public void setDEBTOR_IDENTIFICATION_NO(String dEBTOR_IDENTIFICATION_NO) {
		DEBTOR_IDENTIFICATION_NO = dEBTOR_IDENTIFICATION_NO;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getBenificiary_Status() {
		return Benificiary_Status;
	}
	public void setBenificiary_Status(String benificiary_Status) {
		Benificiary_Status = benificiary_Status;
	}
	public String getReject_Reason() {
		return Reject_Reason;
	}
	public void setReject_Reason(String reject_Reason) {
		Reject_Reason = reject_Reason;
	}
	public Blob getJpg_img() {
		return jpg_img;
	}
	public void setJpg_img(Blob jpg_img) {
		this.jpg_img = jpg_img;
	}
	public Blob getTiff_img() {
		return tiff_img;
	}
	public void setTiff_img(Blob tiff_img) {
		this.tiff_img = tiff_img;
	}
	public String getREASON_CODE() {
		return REASON_CODE;
	}
	public void setREASON_CODE(String rEASON_CODE) {
		REASON_CODE = rEASON_CODE;
	}
	public boolean isFLAG() {
		return FLAG;
	}
	public void setFLAG(boolean FLAG) {
		this.FLAG = FLAG;
	}
	
	
	public Date getLAST_TRANSACTION_DATE() {
		return LAST_TRANSACTION_DATE;
	}
	public void setLAST_TRANSACTION_DATE(Date lAST_TRANSACTION_DATE) {
		LAST_TRANSACTION_DATE = lAST_TRANSACTION_DATE;
	}
	
	public String getUMRN() {
		return UMRN;
	}
	public void setUMRN(String uMRN) {
		UMRN = uMRN;
	}
	public String getAUTHORIZE_STATUS() {
		return AUTHORIZE_STATUS;
	}
	public void setAUTHORIZE_STATUS(String aUTHORIZE_STATUS) {
		AUTHORIZE_STATUS = aUTHORIZE_STATUS;
	}
	
	@Override
	public int compare(Date d1 , Date d2) {
		// TODO Auto-generated method stub\\\
		
		if(d1.compareTo(d2)<0){
			return -1;
		}else if(d1.compareTo(d2)>0){
			return 1;
		}else {
			return 0;
		}
	}
	public String getINPFILENAME() {
		return INPFILENAME;
	}
	public void setINPFILENAME(String iNPFILENAME) {
		INPFILENAME = iNPFILENAME;
	}
	public String getUNTIL_CANCEL() {
		return UNTIL_CANCEL;
	}
	public void setUNTIL_CANCEL(String uNTIL_CANCEL) {
		UNTIL_CANCEL = uNTIL_CANCEL;
	}
	public String getCategory_Code() {
		return category_Code;
	}
	public void setCategory_Code(String category_Code) {
		this.category_Code = category_Code;
	}
	
	
}
