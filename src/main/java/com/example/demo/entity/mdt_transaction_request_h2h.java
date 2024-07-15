package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MDT_TRANSACTION_REQUEST_H2H")
public class mdt_transaction_request_h2h {
	
	@Id
	@Column(name ="UNIQUE_ID")
	private String uniqueId; 
	
	@Column(name ="TRANSACTION_TYPE")
	private String TRANSACTION_TYPE; 
	
	@Column(name ="OCCURENCE")
	private String OCCURENCE; 
	
	@Column(name ="FREQUENCY")
	private String FREQUENCY;  
	
	@Column(name ="FIRST_COLLECTION_DATE")
	private Date FIRST_COLLECTION_DATE; 
	
	@Column(name ="FINAL_COLLECTION_DATE")
	private Date FINAL_COLLECTION_DATE; 
	
	@Column(name ="AMOUNT_TYPE")
	private String AMOUNT_TYPE; 
	
	@Column(name ="AMOUNT")
	private String AMOUNT;
	
	@Column(name ="CREDITOR_NAME")
	private String CREDITOR_NAME; 
	
	@Column(name ="CREDITOR_IDENTIFICATION")
	private String CREDITOR_IDENTIFICATION;
	
	@Column(name ="DEBTOR_NAME")
	private String DEBTOR_NAME;
	
	@Column(name ="DEBTOR_ACCOUNT_NO")
	private String DEBTOR_ACCOUNT_NO;
	
	@Column(name ="DEBTOR_ACCOUNT_TYPE")
	private String DEBTOR_ACCOUNT_TYPE; 
	
	@Column(name ="DEBTOR_BANK_NAME")
	private String DEBTOR_BANK_NAME;  
	
	@Column(name ="DEBTOR_IDENTIFICATION")
	private String DEBTOR_IDENTIFICATION;
	
	@Column(name ="DEBTOR_IDENTIFICATION_NO")
	private String DEBTOR_IDENTIFICATION_NO;
	
	@Column(name ="CREATION_DATE")
	private Date CREATION_DATE;  
	
	@Column(name ="LAST_MODIFIED_DATE")
	private Date LAST_MODIFIED_DATE; 
	
	@Column(name = "AUTHORIZE_STATUS")
	private String AUTHORIZE_STATUS; 
	
	@Column(name ="INPFILENAME")
	private String INPFILENAME;
	
	@Column(name = "BANK_NAME")
	private String bankName; 
	
	@Column(name ="RESFILENAME")
	private String RESFILENAME;
	
	@Column(name ="REASONCODE")
	private String REASONCODE; 
	
	@Column(name ="TRANSACTION_REF_NUMBER")
	private String TRANSACTION_REF_NUMBER;
	
	@Column(name ="RESFLAG")
	private String RESFLAG;
	
	@Column(name ="UMRN")
	private String UMRN;   
	
	@Column(name ="CREDITOR_ACCOUNT_NO")
	private String CREDITOR_ACCOUNT_NO;
	
	@Column(name ="SAL_TOT_AMT")
	private String SAL_TOT_AMT;     
	
	@Column(name ="INPFILENAMEUPLOADTONPCI")
	private String INPFILENAMEUPLOADTONPCI; 
	
	@Column(name ="UNTIL_CANCEL")
	private String UNTIL_CANCEL;
	
	@Column(name ="SETTLEMENT_DATE")
	private String SETTLEMENT_DATE;
	
	@Column(name ="ACKFLAG")
	private String ACKFLAG; 
	
	@Column(name ="INP_ACK_REASONCODE")
	private String INP_ACK_REASONCODE;

	public mdt_transaction_request_h2h(String uniqueId, String tRANSACTION_TYPE, String oCCURENCE, String fREQUENCY,
			Date fIRST_COLLECTION_DATE, Date fINAL_COLLECTION_DATE, String aMOUNT_TYPE, String aMOUNT,
			String cREDITOR_NAME, String cREDITOR_IDENTIFICATION, String dEBTOR_NAME, String dEBTOR_ACCOUNT_NO,
			String dEBTOR_ACCOUNT_TYPE, String dEBTOR_BANK_NAME, String dEBTOR_IDENTIFICATION,
			String dEBTOR_IDENTIFICATION_NO, Date cREATION_DATE, Date lAST_MODIFIED_DATE, String aUTHORIZE_STATUS,
			String iNPFILENAME, String bankName, String rESFILENAME, String rEASONCODE, String tRANSACTION_REF_NUMBER,
			String rESFLAG, String uMRN, String cREDITOR_ACCOUNT_NO, String sAL_TOT_AMT, String iNPFILENAMEUPLOADTONPCI,
			String uNTIL_CANCEL, String sETTLEMENT_DATE, String aCKFLAG, String iNP_ACK_REASONCODE) {
		super();
		this.uniqueId = uniqueId;
		TRANSACTION_TYPE = tRANSACTION_TYPE;
		OCCURENCE = oCCURENCE;
		FREQUENCY = fREQUENCY;
		FIRST_COLLECTION_DATE = fIRST_COLLECTION_DATE;
		FINAL_COLLECTION_DATE = fINAL_COLLECTION_DATE;
		AMOUNT_TYPE = aMOUNT_TYPE;
		AMOUNT = aMOUNT;
		CREDITOR_NAME = cREDITOR_NAME;
		CREDITOR_IDENTIFICATION = cREDITOR_IDENTIFICATION;
		DEBTOR_NAME = dEBTOR_NAME;
		DEBTOR_ACCOUNT_NO = dEBTOR_ACCOUNT_NO;
		DEBTOR_ACCOUNT_TYPE = dEBTOR_ACCOUNT_TYPE;
		DEBTOR_BANK_NAME = dEBTOR_BANK_NAME;
		DEBTOR_IDENTIFICATION = dEBTOR_IDENTIFICATION;
		DEBTOR_IDENTIFICATION_NO = dEBTOR_IDENTIFICATION_NO;
		CREATION_DATE = cREATION_DATE;
		LAST_MODIFIED_DATE = lAST_MODIFIED_DATE;
		AUTHORIZE_STATUS = aUTHORIZE_STATUS;
		INPFILENAME = iNPFILENAME;
		this.bankName = bankName;
		RESFILENAME = rESFILENAME;
		REASONCODE = rEASONCODE;
		TRANSACTION_REF_NUMBER = tRANSACTION_REF_NUMBER;
		RESFLAG = rESFLAG;
		UMRN = uMRN;
		CREDITOR_ACCOUNT_NO = cREDITOR_ACCOUNT_NO;
		SAL_TOT_AMT = sAL_TOT_AMT;
		INPFILENAMEUPLOADTONPCI = iNPFILENAMEUPLOADTONPCI;
		UNTIL_CANCEL = uNTIL_CANCEL;
		SETTLEMENT_DATE = sETTLEMENT_DATE;
		ACKFLAG = aCKFLAG;
		INP_ACK_REASONCODE = iNP_ACK_REASONCODE;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public String getTRANSACTION_TYPE() {
		return TRANSACTION_TYPE;
	}

	public String getOCCURENCE() {
		return OCCURENCE;
	}

	public String getFREQUENCY() {
		return FREQUENCY;
	}

	public Date getFIRST_COLLECTION_DATE() {
		return FIRST_COLLECTION_DATE;
	}

	public Date getFINAL_COLLECTION_DATE() {
		return FINAL_COLLECTION_DATE;
	}

	public String getAMOUNT_TYPE() {
		return AMOUNT_TYPE;
	}

	public String getAMOUNT() {
		return AMOUNT;
	}

	public String getCREDITOR_NAME() {
		return CREDITOR_NAME;
	}

	public String getCREDITOR_IDENTIFICATION() {
		return CREDITOR_IDENTIFICATION;
	}

	public String getDEBTOR_NAME() {
		return DEBTOR_NAME;
	}

	public String getDEBTOR_ACCOUNT_NO() {
		return DEBTOR_ACCOUNT_NO;
	}

	public String getDEBTOR_ACCOUNT_TYPE() {
		return DEBTOR_ACCOUNT_TYPE;
	}

	public String getDEBTOR_BANK_NAME() {
		return DEBTOR_BANK_NAME;
	}

	public String getDEBTOR_IDENTIFICATION() {
		return DEBTOR_IDENTIFICATION;
	}

	public String getDEBTOR_IDENTIFICATION_NO() {
		return DEBTOR_IDENTIFICATION_NO;
	}

	public Date getCREATION_DATE() {
		return CREATION_DATE;
	}

	public Date getLAST_MODIFIED_DATE() {
		return LAST_MODIFIED_DATE;
	}

	public String getAUTHORIZE_STATUS() {
		return AUTHORIZE_STATUS;
	}

	public String getINPFILENAME() {
		return INPFILENAME;
	}

	public String getBankName() {
		return bankName;
	}

	public String getRESFILENAME() {
		return RESFILENAME;
	}

	public String getREASONCODE() {
		return REASONCODE;
	}

	public String getTRANSACTION_REF_NUMBER() {
		return TRANSACTION_REF_NUMBER;
	}

	public String getRESFLAG() {
		return RESFLAG;
	}

	public String getUMRN() {
		return UMRN;
	}

	public String getCREDITOR_ACCOUNT_NO() {
		return CREDITOR_ACCOUNT_NO;
	}

	public String getSAL_TOT_AMT() {
		return SAL_TOT_AMT;
	}

	public String getINPFILENAMEUPLOADTONPCI() {
		return INPFILENAMEUPLOADTONPCI;
	}

	public String getUNTIL_CANCEL() {
		return UNTIL_CANCEL;
	}

	public String getSETTLEMENT_DATE() {
		return SETTLEMENT_DATE;
	}

	public String getACKFLAG() {
		return ACKFLAG;
	}

	public String getINP_ACK_REASONCODE() {
		return INP_ACK_REASONCODE;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public void setTRANSACTION_TYPE(String tRANSACTION_TYPE) {
		TRANSACTION_TYPE = tRANSACTION_TYPE;
	}

	public void setOCCURENCE(String oCCURENCE) {
		OCCURENCE = oCCURENCE;
	}

	public void setFREQUENCY(String fREQUENCY) {
		FREQUENCY = fREQUENCY;
	}

	public void setFIRST_COLLECTION_DATE(Date fIRST_COLLECTION_DATE) {
		FIRST_COLLECTION_DATE = fIRST_COLLECTION_DATE;
	}

	public void setFINAL_COLLECTION_DATE(Date fINAL_COLLECTION_DATE) {
		FINAL_COLLECTION_DATE = fINAL_COLLECTION_DATE;
	}

	public void setAMOUNT_TYPE(String aMOUNT_TYPE) {
		AMOUNT_TYPE = aMOUNT_TYPE;
	}

	public void setAMOUNT(String aMOUNT) {
		AMOUNT = aMOUNT;
	}

	public void setCREDITOR_NAME(String cREDITOR_NAME) {
		CREDITOR_NAME = cREDITOR_NAME;
	}

	public void setCREDITOR_IDENTIFICATION(String cREDITOR_IDENTIFICATION) {
		CREDITOR_IDENTIFICATION = cREDITOR_IDENTIFICATION;
	}

	public void setDEBTOR_NAME(String dEBTOR_NAME) {
		DEBTOR_NAME = dEBTOR_NAME;
	}

	public void setDEBTOR_ACCOUNT_NO(String dEBTOR_ACCOUNT_NO) {
		DEBTOR_ACCOUNT_NO = dEBTOR_ACCOUNT_NO;
	}

	public void setDEBTOR_ACCOUNT_TYPE(String dEBTOR_ACCOUNT_TYPE) {
		DEBTOR_ACCOUNT_TYPE = dEBTOR_ACCOUNT_TYPE;
	}

	public void setDEBTOR_BANK_NAME(String dEBTOR_BANK_NAME) {
		DEBTOR_BANK_NAME = dEBTOR_BANK_NAME;
	}

	public void setDEBTOR_IDENTIFICATION(String dEBTOR_IDENTIFICATION) {
		DEBTOR_IDENTIFICATION = dEBTOR_IDENTIFICATION;
	}

	public void setDEBTOR_IDENTIFICATION_NO(String dEBTOR_IDENTIFICATION_NO) {
		DEBTOR_IDENTIFICATION_NO = dEBTOR_IDENTIFICATION_NO;
	}

	public void setCREATION_DATE(Date cREATION_DATE) {
		CREATION_DATE = cREATION_DATE;
	}

	public void setLAST_MODIFIED_DATE(Date lAST_MODIFIED_DATE) {
		LAST_MODIFIED_DATE = lAST_MODIFIED_DATE;
	}

	public void setAUTHORIZE_STATUS(String aUTHORIZE_STATUS) {
		AUTHORIZE_STATUS = aUTHORIZE_STATUS;
	}

	public void setINPFILENAME(String iNPFILENAME) {
		INPFILENAME = iNPFILENAME;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setRESFILENAME(String rESFILENAME) {
		RESFILENAME = rESFILENAME;
	}

	public void setREASONCODE(String rEASONCODE) {
		REASONCODE = rEASONCODE;
	}

	public void setTRANSACTION_REF_NUMBER(String tRANSACTION_REF_NUMBER) {
		TRANSACTION_REF_NUMBER = tRANSACTION_REF_NUMBER;
	}

	public void setRESFLAG(String rESFLAG) {
		RESFLAG = rESFLAG;
	}

	public void setUMRN(String uMRN) {
		UMRN = uMRN;
	}

	public void setCREDITOR_ACCOUNT_NO(String cREDITOR_ACCOUNT_NO) {
		CREDITOR_ACCOUNT_NO = cREDITOR_ACCOUNT_NO;
	}

	public void setSAL_TOT_AMT(String sAL_TOT_AMT) {
		SAL_TOT_AMT = sAL_TOT_AMT;
	}

	public void setINPFILENAMEUPLOADTONPCI(String iNPFILENAMEUPLOADTONPCI) {
		INPFILENAMEUPLOADTONPCI = iNPFILENAMEUPLOADTONPCI;
	}

	public void setUNTIL_CANCEL(String uNTIL_CANCEL) {
		UNTIL_CANCEL = uNTIL_CANCEL;
	}

	public void setSETTLEMENT_DATE(String sETTLEMENT_DATE) {
		SETTLEMENT_DATE = sETTLEMENT_DATE;
	}

	public void setACKFLAG(String aCKFLAG) {
		ACKFLAG = aCKFLAG;
	}

	public void setINP_ACK_REASONCODE(String iNP_ACK_REASONCODE) {
		INP_ACK_REASONCODE = iNP_ACK_REASONCODE;
	}

	public mdt_transaction_request_h2h() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	
	
	

	
	
	
	
	

}
