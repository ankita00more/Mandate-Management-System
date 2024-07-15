package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SEQUENCE_COUNTER")
public class Sequence_Counter {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long Id;
	
	@Column(name = "SEQUENCE")
	private int SEQUENCE;  
	
	@Column(name ="BANKNAME")
	private String BANKNAME; 
	
	@Column(name ="MDT_REQUEST")
	private String MDT_REQUEST;  
	
	@Column(name ="CREATION_DATE")
	private String CREATION_DATE;

	public long getId() {
		return Id;
	}

	public int getSEQUENCE() {
		return SEQUENCE;
	}

	public String getBANKNAME() {
		return BANKNAME;
	}

	public String getMDT_REQUEST() {
		return MDT_REQUEST;
	}

	public String getCREATION_DATE() {
		return CREATION_DATE;
	}

	public void setId(long id) {
		Id = id;
	}

	public void setSEQUENCE(int sEQUENCE) {
		SEQUENCE = sEQUENCE;
	}

	public void setBANKNAME(String bANKNAME) {
		BANKNAME = bANKNAME;
	}

	public void setMDT_REQUEST(String mDT_REQUEST) {
		MDT_REQUEST = mDT_REQUEST;
	}

	public void setCREATION_DATE(String cREATION_DATE) {
		CREATION_DATE = cREATION_DATE;
	}

	public Sequence_Counter(long id, int sEQUENCE, String bANKNAME, String mDT_REQUEST, String cREATION_DATE) {
		super();
		Id = id;
		SEQUENCE = sEQUENCE;
		BANKNAME = bANKNAME;
		MDT_REQUEST = mDT_REQUEST;
		CREATION_DATE = cREATION_DATE;
	}

	public Sequence_Counter() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
