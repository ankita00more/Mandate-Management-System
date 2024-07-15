package com.example.demo.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table (name = "ACCT_Images") 
public class AcctImages {
	
	@Id
	@Column(name ="ACCT_NO", length = 200)
	private String ACCT_NO;    
	@Column(name ="IMG_NO", length = 200)
	private String IMG_NO;
	
	@Lob
	private Blob IMG_SG;  
	@Column(name ="ACCOUNT_NAME", length = 200)
	private String ACCOUNT_NAME; 
	
	@Column(name ="ACCT_STATUS", length = 200)
	private String ACCT_STATUS;
	
	@Column(name ="ACCT_TYPE", length = 200)
	private String ACCT_TYPE; 
	
	@Column(name ="MOP", length = 200)
	private String MOP;
	public String getACCT_NO() {
		return ACCT_NO;
	}
	public void setACCT_NO(String aCCT_NO) {
		ACCT_NO = aCCT_NO;
	}
	public String getIMG_NO() {
		return IMG_NO;
	}
	public void setIMG_NO(String iMG_NO) {
		IMG_NO = iMG_NO;
	}
	public Blob getIMG_SG() {
		return IMG_SG;
	}
	public void setIMG_SG(Blob iMG_SG) {
		IMG_SG = iMG_SG;
	}
	public String getACCOUNT_NAME() {
		return ACCOUNT_NAME;
	}
	public void setACCOUNT_NAME(String aCCOUNT_NAME) {
		ACCOUNT_NAME = aCCOUNT_NAME;
	}
	public String getACCT_STATUS() {
		return ACCT_STATUS;
	}
	public void setACCT_STATUS(String aCCT_STATUS) {
		ACCT_STATUS = aCCT_STATUS;
	}
	public String getACCT_TYPE() {
		return ACCT_TYPE;
	}
	public void setACCT_TYPE(String aCCT_TYPE) {
		ACCT_TYPE = aCCT_TYPE;
	}
	public String getMOP() {
		return MOP;
	}
	public void setMOP(String mOP) {
		MOP = mOP;
	}
	public AcctImages() {
		super();
	}
	
	
	
}