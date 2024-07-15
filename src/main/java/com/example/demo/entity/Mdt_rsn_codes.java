package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "mdt_rsn_codes")
public class Mdt_rsn_codes {
	@Id
	@Column(name = "STATUS_RSN_CODE")
	private String STATUS_RSN_CODE;    
	@Column(name = "RSN_DESC")
	private String RSN_DESC;   
	@Column(name = "RSN_CAT")
	private String RSN_CAT; 
	@Column(name = "ACT_FLAG")
	private String ACT_FLAG;
	
	
	//Constructor with args
	public Mdt_rsn_codes(String sTATUS_RSN_CODE, String rSN_DESC, String rSN_CAT, String aCT_FLAG) {
		super();
		this.STATUS_RSN_CODE = sTATUS_RSN_CODE;
		this.RSN_DESC = rSN_DESC;
		this.RSN_CAT = rSN_CAT;
		this.ACT_FLAG = aCT_FLAG;
	}
	
	
	
	//Getter setters
	public String getSTATUS_RSN_CODE() {
		return STATUS_RSN_CODE;
	}
	public void setSTATUS_RSN_CODE(String sTATUS_RSN_CODE) {
		STATUS_RSN_CODE = sTATUS_RSN_CODE;
	}
	public String getRSN_DESC() {
		return RSN_DESC;
	}
	public void setRSN_DESC(String rSN_DESC) {
		RSN_DESC = rSN_DESC;
	}
	public String getRSN_CAT() {
		return RSN_CAT;
	}
	public void setRSN_CAT(String rSN_CAT) {
		RSN_CAT = rSN_CAT;
	}
	public String getACT_FLAG() {
		return ACT_FLAG;
	}
	public void setACT_FLAG(String aCT_FLAG) {
		ACT_FLAG = aCT_FLAG;
	}



	public Mdt_rsn_codes() {
		super();
		// TODO Auto-generated constructor stub
	}

}
