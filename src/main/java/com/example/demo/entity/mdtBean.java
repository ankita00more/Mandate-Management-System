package com.example.demo.entity;

import java.sql.Blob;
import org.springframework.stereotype.Component;

@Component
public class mdtBean {

	 private String UMRN = "";
	 private String MSG_ID = "";
	 private String MDT_CREATION_TIME = "";
	 private String INITING_PARTY_ID = "";
	 private String MEMBER_ID_INSTRCTING = "";
	 private String MEMBER_NAME_INSTRCTING = "";
	 private String MEMBER_ID_INSTRUCTED = "";
	 private String MEMBER_NAME_INSTRUCTED = "";
	 private String MDT_REQ_ID = "";
	 private String CATEGORY = "";
	 private String PROPRIETARY = "";
	 private String RCUR = "";
	 private String FREQUENCY = "";
	 private String FRST_COLLCTN_DT = "";
	 private String COLLCTN_AMT = "";
	 private String MAX_AMT = "";
	 private String CREDITOR_NAME = "";
	 private String CREDITOR_ID = "";
	 private String CREDITOR_MEM_ID = "";
	 private String CREDITOR_MEM_NAME = "";
	 private String CUST_NAME = "";
	 private String CUST_PHONE_NUM = "";
	 private String CUST_MOBILE_NUM = "";
	 private String CUST_EMAIL_ADR = "";
	 private String CUST_OTHER = "";
	 private String DEBITOR_ACC_NUM = "";
	 private String DEBITOR_ACC_TYPE = "";
	 private String DEBITOR_ID = "";
	 private String DEBITOR_PROPRIETARY = "";
	 private String MDT_ZIP_FILE_NAME = "";
	 private String MDT_DETAIL_FRNT_IMG_NAME = "";
	 private String MDT_FRNT_IMG_NAME = "";
	 private String MDT_INP_FILE_NAME = "";
	 private String MDT_DETAIL_FRNT_IMG = "";
	 private String MDT_FRNT_IMG = "";
	 private String STATUS = "";//msg_nm_id
	 private String msg_nm_id = "";//
	 private String STATUS_RSN_CODE = "";
	 private String status_reason_description = "";
	 private String MDT_INW_RES_FLAG = "";
	 private String MDT_INW_RES_FILENAME = "";
	 private String BANK_NAME = "";
	 private String CREATION_TIME = "";
	 private String MODIFIED_TIME = "";
	 private String MDT_REQ_FOR = "";
	 private int TOT_REC_CNT = 0;
	 private String BANK_IIN="";
	 private String BANK_shortcode="";
	 private String MDT_INW_RES_FOLDRNAME= "";//MDT_REQ_FOR_DRP
	 private String MDT_REQ_FOR_DRP= "";//
	 private Integer isAccept;
	 private Integer isreject;
	 private String reson;
	 private String ACCOUNT_NAME="";
	 private String ACCOUNT_STATUS="";
	 private String IMG_NO="";
	 private Blob IMG_SG;
	 private String ACCT_TYPE="";
	 private String MODE_OF_OPERTAION="";
	 
	public String getACCT_TYPE() {
		return ACCT_TYPE;
	}

	public void setACCT_TYPE(String aCCT_TYPE) {
		ACCT_TYPE = aCCT_TYPE;
	}

	public String getIMG_NO() {
		return IMG_NO;
	}
	public Blob getIMG_SG() {
		return IMG_SG;
	}

	public void setIMG_SG(Blob blob) {
		IMG_SG = blob;
	}

	public void setIMG_NO(String iMG_NO) {
		IMG_NO = iMG_NO;
	}
	public String getACCOUNT_NAME() {
		return ACCOUNT_NAME;
	}

	public void setACCOUNT_NAME(String aCCOUNT_NAME) {
		ACCOUNT_NAME = aCCOUNT_NAME;
	}


	public String getACCOUNT_STATUS() {
		return ACCOUNT_STATUS;
	}


	public void setACCOUNT_STATUS(String aCCOUNT_STATUS) {
		ACCOUNT_STATUS = aCCOUNT_STATUS;
	}


	public String getReson() {
		return reson;
	}


	public void setReson(String reson) {
		this.reson = reson;
	}


	public Integer getIsreject() {
		return isreject;
	}


	public void setIsreject(Integer isreject) {
		this.isreject = isreject;
	}


	public Integer getIsAccept() {
		return isAccept;
	}


	public void setIsAccept(Integer isAccept) {
		this.isAccept = isAccept;
	}





	public String getStatus_reason_description() {
			return status_reason_description;
		}


		public void setStatus_reason_description(String status_reason_description) {
			this.status_reason_description = status_reason_description;
		}
	 
	 public String getMDT_REQ_FOR_DRP() {
			return MDT_REQ_FOR_DRP;
		}


		public void setMDT_REQ_FOR_DRP(String mDTREQFORDRP) {
			MDT_REQ_FOR_DRP = mDTREQFORDRP;
		}
	 public String getMsg_nm_id() {
			return msg_nm_id;
		}


		public void setMsg_nm_id(String msgNmId) {
			msg_nm_id = msgNmId;
		}
	 public String getMDT_INW_RES_FOLDRNAME() {
		return MDT_INW_RES_FOLDRNAME;
	}


	public void setMDT_INW_RES_FOLDRNAME(String mDTINWRESFOLDRNAME) {
		MDT_INW_RES_FOLDRNAME = mDTINWRESFOLDRNAME;
	}
	private String File_seq="";
	 private String ResMsg="";
	 
	 public String getBANK_IIN() {
		return BANK_IIN;
	}


	public String getResMsg() {
		return ResMsg;
	}


	public void setResMsg(String resMsg) {
		ResMsg = resMsg;
	}


	public String getFile_seq() {
		return File_seq;
	}


	public void setFile_seq(String fileSeq) {
		File_seq = fileSeq;
	}


	public void setBANK_IIN(String bANKIIN) {
		BANK_IIN = bANKIIN;
	}


	public String getBANK_shortcode() {
		return BANK_shortcode;
	}


	public void setBANK_shortcode(String bANKShortcode) {
		BANK_shortcode = bANKShortcode;
	}


	public String getBANK_uid() {
		return BANK_uid;
	}


	public void setBANK_uid(String bANKUid) {
		BANK_uid = bANKUid;
	}
	private String BANK_uid="";
	 public int getTOT_REC_CNT() {
		return TOT_REC_CNT;
	}


	public void setTOT_REC_CNT(int tOTRECCNT) {
		TOT_REC_CNT = tOTRECCNT;
	}

	
	public String getMDT_REQ_FOR() {
		return MDT_REQ_FOR;
	}


	/*public String setMDT_REQ_FOR(String mDTREQFOR) {
		return MDT_REQ_FOR = mDTREQFOR;
	}*/

	public void setMDT_REQ_FOR(String mDTREQFOR) {
		MDT_REQ_FOR = mDTREQFOR;
	}

	public String getUMRN() {
		return UMRN;
	}
	public void setUMRN(String uMRN) {
		UMRN = uMRN;
	}
	public String getMSG_ID() {
		return MSG_ID;
	}
	public void setMSG_ID(String mSGID) {
		MSG_ID = mSGID;
	}
	public String getMDT_CREATION_TIME() {
		return MDT_CREATION_TIME;
	}
	public void setMDT_CREATION_TIME(String mDTCREATIONTIME) {
		MDT_CREATION_TIME = mDTCREATIONTIME;
	}
	public String getINITING_PARTY_ID() {
		return INITING_PARTY_ID;
	}
	public void setINITING_PARTY_ID(String iNITINGPARTYID) {
		INITING_PARTY_ID = iNITINGPARTYID;
	}
	public String getMEMBER_ID_INSTRCTING() {
		return MEMBER_ID_INSTRCTING;
	}
	public void setMEMBER_ID_INSTRCTING(String mEMBERIDINSTRCTING) {
		MEMBER_ID_INSTRCTING = mEMBERIDINSTRCTING;
	}
	public String getMEMBER_NAME_INSTRCTING() {
		return MEMBER_NAME_INSTRCTING;
	}
	public void setMEMBER_NAME_INSTRCTING(String mEMBERNAMEINSTRCTING) {
		MEMBER_NAME_INSTRCTING = mEMBERNAMEINSTRCTING;
	}
	public String getMEMBER_ID_INSTRUCTED() {
		return MEMBER_ID_INSTRUCTED;
	}
	public void setMEMBER_ID_INSTRUCTED(String mEMBERIDINSTRUCTED) {
		MEMBER_ID_INSTRUCTED = mEMBERIDINSTRUCTED;
	}
	public String getMEMBER_NAME_INSTRUCTED() {
		return MEMBER_NAME_INSTRUCTED;
	}
	public void setMEMBER_NAME_INSTRUCTED(String mEMBERNAMEINSTRUCTED) {
		MEMBER_NAME_INSTRUCTED = mEMBERNAMEINSTRUCTED;
	}
	public String getMDT_REQ_ID() {
		return MDT_REQ_ID;
	}
	public void setMDT_REQ_ID(String mDTREQID) {
		MDT_REQ_ID = mDTREQID;
	}
	public String getCATEGORY() {
		return CATEGORY;
	}
	public void setCATEGORY(String cATEGORY) {
		CATEGORY = cATEGORY;
	}
	public String getPROPRIETARY() {
		return PROPRIETARY;
	}
	public void setPROPRIETARY(String pROPRIETARY) {
		PROPRIETARY = pROPRIETARY;
	}
	public String getRCUR() {
		return RCUR;
	}
	public void setRCUR(String rCUR) {
		RCUR = rCUR;
	}
	public String getFREQUENCY() {
		return FREQUENCY;
	}
	public void setFREQUENCY(String fREQUENCY) {
		FREQUENCY = fREQUENCY;
	}
	public String getFRST_COLLCTN_DT() {
		return FRST_COLLCTN_DT;
	}
	public void setFRST_COLLCTN_DT(String fRSTCOLLCTNDT) {
		FRST_COLLCTN_DT = fRSTCOLLCTNDT;
	}
	public String getCOLLCTN_AMT() {
		return COLLCTN_AMT;
	}
	public void setCOLLCTN_AMT(String cOLLCTNAMT) {
		COLLCTN_AMT = cOLLCTNAMT;
	}
	public String getMAX_AMT() {
		return MAX_AMT;
	}
	public void setMAX_AMT(String mAXAMT) {
		MAX_AMT = mAXAMT;
	}
	public String getCREDITOR_NAME() {
		return CREDITOR_NAME;
	}
	public void setCREDITOR_NAME(String cREDITORNAME) {
		CREDITOR_NAME = cREDITORNAME;
	}
	public String getCREDITOR_ID() {
		return CREDITOR_ID;
	}
	public void setCREDITOR_ID(String cREDITORID) {
		CREDITOR_ID = cREDITORID;
	}
	public String getCREDITOR_MEM_ID() {
		return CREDITOR_MEM_ID;
	}
	public void setCREDITOR_MEM_ID(String cREDITORMEMID) {
		CREDITOR_MEM_ID = cREDITORMEMID;
	}
	public String getCREDITOR_MEM_NAME() {
		return CREDITOR_MEM_NAME;
	}
	public void setCREDITOR_MEM_NAME(String cREDITORMEMNAME) {
		CREDITOR_MEM_NAME = cREDITORMEMNAME;
	}
	public String getCUST_NAME() {
		return CUST_NAME;
	}
	public void setCUST_NAME(String cUSTNAME) {
		CUST_NAME = cUSTNAME;
	}
	public String getCUST_PHONE_NUM() {
		return CUST_PHONE_NUM;
	}
	public void setCUST_PHONE_NUM(String cUSTPHONENUM) {
		CUST_PHONE_NUM = cUSTPHONENUM;
	}
	public String getCUST_MOBILE_NUM() {
		return CUST_MOBILE_NUM;
	}
	public void setCUST_MOBILE_NUM(String cUSTMOBILENUM) {
		CUST_MOBILE_NUM = cUSTMOBILENUM;
	}
	public String getCUST_EMAIL_ADR() {
		return CUST_EMAIL_ADR;
	}
	public void setCUST_EMAIL_ADR(String cUSTEMAILADR) {
		CUST_EMAIL_ADR = cUSTEMAILADR;
	}
	public String getCUST_OTHER() {
		return CUST_OTHER;
	}
	public void setCUST_OTHER(String cUSTOTHER) {
		CUST_OTHER = cUSTOTHER;
	}
	public String getDEBITOR_ACC_NUM() {
		return DEBITOR_ACC_NUM;
	}
	public void setDEBITOR_ACC_NUM(String dEBITORACCNUM) {
		DEBITOR_ACC_NUM = dEBITORACCNUM;
	}
	public String getDEBITOR_ACC_TYPE() {
		return DEBITOR_ACC_TYPE;
	}
	public void setDEBITOR_ACC_TYPE(String dEBITORACCTYPE) {
		DEBITOR_ACC_TYPE = dEBITORACCTYPE;
	}
	public String getDEBITOR_ID() {
		return DEBITOR_ID;
	}
	public void setDEBITOR_ID(String dEBITORID) {
		DEBITOR_ID = dEBITORID;
	}
	public String getDEBITOR_PROPRIETARY() {
		return DEBITOR_PROPRIETARY;
	}
	public void setDEBITOR_PROPRIETARY(String dEBITORPROPRIETARY) {
		DEBITOR_PROPRIETARY = dEBITORPROPRIETARY;
	}
	public String getMDT_ZIP_FILE_NAME() {
		return MDT_ZIP_FILE_NAME;
	}
	public void setMDT_ZIP_FILE_NAME(String mDTZIPFILENAME) {
		MDT_ZIP_FILE_NAME = mDTZIPFILENAME;
	}
	public String getMDT_DETAIL_FRNT_IMG_NAME() {
		return MDT_DETAIL_FRNT_IMG_NAME;
	}
	public void setMDT_DETAIL_FRNT_IMG_NAME(String mDTDETAILFRNTIMGNAME) {
		MDT_DETAIL_FRNT_IMG_NAME = mDTDETAILFRNTIMGNAME;
	}
	public String getMDT_FRNT_IMG_NAME() {
		return MDT_FRNT_IMG_NAME;
	}
	public void setMDT_FRNT_IMG_NAME(String mDTFRNTIMGNAME) {
		MDT_FRNT_IMG_NAME = mDTFRNTIMGNAME;
	}
	public String getMDT_INP_FILE_NAME() {
		return MDT_INP_FILE_NAME;
	}
	public void setMDT_INP_FILE_NAME(String mDTINPFILENAME) {
		MDT_INP_FILE_NAME = mDTINPFILENAME;
	}
	public String getMDT_DETAIL_FRNT_IMG() {
		return MDT_DETAIL_FRNT_IMG;
	}
	public void setMDT_DETAIL_FRNT_IMG(String mDTDETAILFRNTIMG) {
		MDT_DETAIL_FRNT_IMG = mDTDETAILFRNTIMG;
	}
	public String getMDT_FRNT_IMG() {
		return MDT_FRNT_IMG;
	}
	public void setMDT_FRNT_IMG(String mDTFRNTIMG) {
		MDT_FRNT_IMG = mDTFRNTIMG;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getSTATUS_RSN_CODE() {
		return STATUS_RSN_CODE;
	}
	public void setSTATUS_RSN_CODE(String sTATUSRSNCODE) {
		STATUS_RSN_CODE = sTATUSRSNCODE;
	}
	
	public String getMDT_INW_RES_FLAG() {
		return MDT_INW_RES_FLAG;
	}


	public void setMDT_INW_RES_FLAG(String mDTINWRESFLAG) {
		MDT_INW_RES_FLAG = mDTINWRESFLAG;
	}


	public String getMDT_INW_RES_FILENAME() {
		return MDT_INW_RES_FILENAME;
	}


	public void setMDT_INW_RES_FILENAME(String mDTINWRESFILENAME) {
		MDT_INW_RES_FILENAME = mDTINWRESFILENAME;
	}


	public String getBANK_NAME() {
		return BANK_NAME;
	}
	public void setBANK_NAME(String bANKNAME) {
		BANK_NAME = bANKNAME;
	}
	public String getCREATION_TIME() {
		return CREATION_TIME;
	}
	public void setCREATION_TIME(String cREATIONTIME) {
		CREATION_TIME = cREATIONTIME;
	}
	public String getMODIFIED_TIME() {
		return MODIFIED_TIME;
	}
	public void setMODIFIED_TIME(String mODIFIEDTIME) {
		MODIFIED_TIME = mODIFIEDTIME;
	}


	public String getMODE_OF_OPERTAION() {
		return MODE_OF_OPERTAION;
	}
	public void setMODE_OF_OPERTAION(String mODE_OF_OPERTAION) {
		MODE_OF_OPERTAION = mODE_OF_OPERTAION;
	}
	
	
}
