package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mandate_sequence")
public class MdtSeq {

	@Column(name = "SEQ")
	private String seq;
	
	@Column(name = "BANKNAME")
	private String bankname;

	@Id
	@Column(name = "CREATION_DATE")
	private String creation_date;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

	public MdtSeq(String seq, String bankname, String creation_date) {
		super();
		this.seq = seq;
		this.bankname = bankname;
		this.creation_date = creation_date;
	}

	public MdtSeq() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
