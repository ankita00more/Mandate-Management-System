package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "XML_SEQ")
public class xmlSeq {

	@Id
	@Column(length = 20)
	private String FILE_DATE;
	
	@Column(length = 200)
	private String ACCEPT_FOLDER;
	
	@Column(length = 200)
	private String FILE_SEQ;
	
	@Column(length = 20)
	private String BANKNAME;

	public String getFILE_DATE() {
		return FILE_DATE;
	}

	public void setFILE_DATE(String fILE_DATE) {
		FILE_DATE = fILE_DATE;
	}

	public String getACCEPT_FOLDER() {
		return ACCEPT_FOLDER;
	}

	public void setACCEPT_FOLDER(String aCCEPT_FOLDER) {
		ACCEPT_FOLDER = aCCEPT_FOLDER;
	}

	public String getFILE_SEQ() {
		return FILE_SEQ;
	}

	public void setFILE_SEQ(String fILE_SEQ) {
		FILE_SEQ = fILE_SEQ;
	}

	public String getBANKNAME() {
		return BANKNAME;
	}

	public void setBANKNAME(String bANKNAME) {
		BANKNAME = bANKNAME;
	}

	public xmlSeq() {
		super();
	}
	
	
}
