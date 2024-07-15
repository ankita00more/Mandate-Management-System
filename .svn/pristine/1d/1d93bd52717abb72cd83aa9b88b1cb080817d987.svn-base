package com.example.demo.entity;

import java.sql.Date;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "MDT_INIT_REQUEST_H2H")
public class MdtInitRequestH2h {

	private static long serialVersionUID = 1L;
	@Id
	@Column(name = "UNIQUE_ID",length = 50) 
	private String uniqueId;
	
	@Column(name = "TRANSACTION_TYPE",length = 35)
	private String transaction_type;
	
	@Column(name = "OCCURENCE",length =  4)
	private String occurence;
	
	@Column(name = "FREQUENCY",length = 4)
	private String frequency;
	
	@Column(name = "FIRST_COLLECTION_DATE")
	private Date first_collection_date;
	
	@Column(name = "FINAL_COLLECTION_DATE")
	private Date final_collection_date;
	
	@Column(name = "AMOUNT_TYPE",length = 13)
	private String amount_type;
	
	@Column(name = "AMOUNT",length = 13)
	private String amount;
	
	@Column(name = "CREDITOR_NAME",length = 40)
	private String creditor_name;
	
	@Column(name = "CREDITOR_UTILITY_CODE",length = 18)
	private String creditor_utility_code;
	
	@Column(name = "CREDITOR_IDENTIFICATION",length = 5)
	private String creditor_identification;
	
	@Column(name = "CREDITOR_IDENTIFICATION_NO",length = 11)
	private String creditor_identification_no;
	
	@Column(name = "DEBTOR_NAME",length = 40)
	private String debtor_name;
	
	@Column(name = "DEBTOR_ACCOUNT_NO",length = 34)
	private String debtor_account_no;
	
	@Column(name = "DEBTOR_ACCOUNT_TYPE",length = 35)
	private String debtor_account_type;
	
	@Column(name = "DEBTOR_BANK_NAME",length = 140)
	private String debtor_bank_name;
	
	@Column(name = "DEBTOR_IDENTIFICATION",length = 5)
	private String debtor_identification;
	
	@Column(name = "UMRN",length = 20)
	private String umrn;
	
	@Column(name = "STATUS",length = 25)
	private String status;
	
	@Column(name = "BENIFICIARY_STATUS",length = 5)
	private String benificiary_status;
	
	@Column(name = "REJECT_REASON",length = 500)
	private String reject_reason;
	
	@Column(name = "DEBTOR_IDENTIFICATION_NO",length = 11)
	private String debtor_identification_no;
	
	@Column(name = "seq_no",length = 38)
	private int seq_no;
	
	@Column(name = "CREATION_DATE")
	private Date creation_date;
	
	@Column(name = "LAST_MODIFIED_DATE")
	private Date last_modified_date;
	
	@Column(name = "MDT_REQUEST",length = 20)
	private String mdt_request;
	
	@Column(name = "BANK_NAME",length = 6)
	private String bank_name;
	
	@Column(name = "JPG_IMAGE")
	@Lob
	private byte[] jpg_bytes;
	
	@Column(name = "TIFF_IMAGE")
	@Lob
	private byte[] tif_bytes;
	
	@Column(name = "REASON_CODE",length = 20)
	private String reason_code;
	
	@Column(name = "CATEGORY_CODE",length = 20)
	private String category_code;
	
	@Column(name = "CREDITOR_ACCOUNT_NO",length = 50)
	private String creditor_account_no;
	
	@Column(name = "TRN_DATE",length = 50)
	private Date trn_date;
	
	@Column(name = "CREDITOR_ACCOUNT_NO_TYPE",length = 50)
	private String creditor_account_no_type;
	
	@Column(name = "UNTIL_CANCEL",length = 1)
	private String until_cancel;
	
	@Column(name = "DEB_EMAILID",length = 100)
	private String deb_emailid;
	
	@Column(name = "DEB_MOBNO",length = 14)
	private String deb_mobno;
	
	@Column(name = "IS_SMS_SENT",length = 5)
	private String is_sms_sent;
	
	@Column(name = "TRANSACTION_ID",length = 40)
	private String transaction_id;
	
	@Column(name = "OTPCOUNT",length = 255)
	private String otpcount;
	
	@Column(name = "NPCI_REFMSGID",length = 255)
	private String npci_refmsgid;
	
	@Column(name = "SCHM_NM",length = 255)
	private String schm_nm;
	
	@Column(name = "EMST",length = 255)
	private String emst;
	
	@Column(name = "EMCREDT",length = 255)
	private String emcredt;
	
	@Column(name = "REASON_CODE_COUNT",length = 255)
	private String reason_code_count;
	
	@Column(name = "DATE_OF_MANDATE",length = 255)
	private Date date_of_mandate;
	
	@Column(name = "AMEND_DT",length = 255)
	private String amend_dt;
	
	@Column(name = "MDT_XML_FILE_NAME",length = 200)
	private String mdt_xml_file_name;
	
	@Column(name = "MDT_JPEG_IMG_NAME",length = 200)
	private String mdt_jpeg_img_name;
	
	@Column(name = "MDT_TIFF_IMG_NAME",length = 200)
	private String mdt_tiff_img_name;
	
	@Column(name = "MDT_ZIP_FOLDER_NAME",length = 200)
	private String mdt_zip_folder_name;
	
	 @Transient
	 private String reasonDesc;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public String getOccurence() {
		return occurence;
	}

	public String getFrequency() {
		return frequency;
	}

	public Date getFirst_collection_date() {
		return first_collection_date;
	}

	public Date getFinal_collection_date() {
		return final_collection_date;
	}

	public String getAmount_type() {
		return amount_type;
	}

	public String getAmount() {
		return amount;
	}

	public String getCreditor_name() {
		return creditor_name;
	}

	public String getCreditor_utility_code() {
		return creditor_utility_code;
	}

	public String getCreditor_identification() {
		return creditor_identification;
	}

	public String getCreditor_identification_no() {
		return creditor_identification_no;
	}

	public String getDebtor_name() {
		return debtor_name;
	}

	public String getDebtor_account_no() {
		return debtor_account_no;
	}

	public String getDebtor_account_type() {
		return debtor_account_type;
	}

	public String getDebtor_bank_name() {
		return debtor_bank_name;
	}

	public String getDebtor_identification() {
		return debtor_identification;
	}

	public String getUmrn() {
		return umrn;
	}

	public String getStatus() {
		return status;
	}

	public String getBenificiary_status() {
		return benificiary_status;
	}

	public String getReject_reason() {
		return reject_reason;
	}

	public String getDebtor_identification_no() {
		return debtor_identification_no;
	}

	public int getSeq_no() {
		return seq_no;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public Date getLast_modified_date() {
		return last_modified_date;
	}

	public String getMdt_request() {
		return mdt_request;
	}

	public String getBank_name() {
		return bank_name;
	}

	public byte[] getJpg_bytes() {
		return jpg_bytes;
	}

	public byte[] getTif_bytes() {
		return tif_bytes;
	}

	public String getReason_code() {
		return reason_code;
	}

	public String getCategory_code() {
		return category_code;
	}

	public String getCreditor_account_no() {
		return creditor_account_no;
	}

	public Date getTrn_date() {
		return trn_date;
	}

	public String getCreditor_account_no_type() {
		return creditor_account_no_type;
	}

	public String getUntil_cancel() {
		return until_cancel;
	}

	public String getDeb_emailid() {
		return deb_emailid;
	}

	public String getDeb_mobno() {
		return deb_mobno;
	}

	public String getIs_sms_sent() {
		return is_sms_sent;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public String getOtpcount() {
		return otpcount;
	}

	public String getNpci_refmsgid() {
		return npci_refmsgid;
	}

	public String getSchm_nm() {
		return schm_nm;
	}

	public String getEmst() {
		return emst;
	}

	public String getEmcredt() {
		return emcredt;
	}

	public String getReason_code_count() {
		return reason_code_count;
	}

	public Date getDate_of_mandate() {
		return date_of_mandate;
	}

	public String getAmend_dt() {
		return amend_dt;
	}

	public String getMdt_xml_file_name() {
		return mdt_xml_file_name;
	}

	public String getMdt_jpeg_img_name() {
		return mdt_jpeg_img_name;
	}

	public String getMdt_tiff_img_name() {
		return mdt_tiff_img_name;
	}

	public String getMdt_zip_folder_name() {
		return mdt_zip_folder_name;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		MdtInitRequestH2h.serialVersionUID = serialVersionUID;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public void setOccurence(String occurence) {
		this.occurence = occurence;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public void setFirst_collection_date(Date first_collection_date) {
		this.first_collection_date = first_collection_date;
	}

	public void setFinal_collection_date(Date final_collection_date) {
		this.final_collection_date = final_collection_date;
	}

	public void setAmount_type(String amount_type) {
		this.amount_type = amount_type;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setCreditor_name(String creditor_name) {
		this.creditor_name = creditor_name;
	}

	public void setCreditor_utility_code(String creditor_utility_code) {
		this.creditor_utility_code = creditor_utility_code;
	}

	public void setCreditor_identification(String creditor_identification) {
		this.creditor_identification = creditor_identification;
	}

	public void setCreditor_identification_no(String creditor_identification_no) {
		this.creditor_identification_no = creditor_identification_no;
	}

	public void setDebtor_name(String debtor_name) {
		this.debtor_name = debtor_name;
	}

	public void setDebtor_account_no(String debtor_account_no) {
		this.debtor_account_no = debtor_account_no;
	}

	public void setDebtor_account_type(String debtor_account_type) {
		this.debtor_account_type = debtor_account_type;
	}

	public void setDebtor_bank_name(String debtor_bank_name) {
		this.debtor_bank_name = debtor_bank_name;
	}

	public void setDebtor_identification(String debtor_identification) {
		this.debtor_identification = debtor_identification;
	}

	public void setUmrn(String umrn) {
		this.umrn = umrn;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setBenificiary_status(String benificiary_status) {
		this.benificiary_status = benificiary_status;
	}

	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}

	public void setDebtor_identification_no(String debtor_identification_no) {
		this.debtor_identification_no = debtor_identification_no;
	}

	public void setSeq_no(int seq_no) {
		this.seq_no = seq_no;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public void setLast_modified_date(Date last_modified_date) {
		this.last_modified_date = last_modified_date;
	}

	public void setMdt_request(String mdt_request) {
		this.mdt_request = mdt_request;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public void setJpg_bytes(byte[] jpg_bytes) {
		this.jpg_bytes = jpg_bytes;
	}

	public void setTif_bytes(byte[] tif_bytes) {
		this.tif_bytes = tif_bytes;
	}

	public void setReason_code(String reason_code) {
		this.reason_code = reason_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public void setCreditor_account_no(String creditor_account_no) {
		this.creditor_account_no = creditor_account_no;
	}

	public void setTrn_date(Date trn_date) {
		this.trn_date = trn_date;
	}

	public void setCreditor_account_no_type(String creditor_account_no_type) {
		this.creditor_account_no_type = creditor_account_no_type;
	}

	public void setUntil_cancel(String until_cancel) {
		this.until_cancel = until_cancel;
	}

	public void setDeb_emailid(String deb_emailid) {
		this.deb_emailid = deb_emailid;
	}

	public void setDeb_mobno(String deb_mobno) {
		this.deb_mobno = deb_mobno;
	}

	public void setIs_sms_sent(String is_sms_sent) {
		this.is_sms_sent = is_sms_sent;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public void setOtpcount(String otpcount) {
		this.otpcount = otpcount;
	}

	public void setNpci_refmsgid(String npci_refmsgid) {
		this.npci_refmsgid = npci_refmsgid;
	}

	public void setSchm_nm(String schm_nm) {
		this.schm_nm = schm_nm;
	}

	public void setEmst(String emst) {
		this.emst = emst;
	}

	public void setEmcredt(String emcredt) {
		this.emcredt = emcredt;
	}

	public void setReason_code_count(String reason_code_count) {
		this.reason_code_count = reason_code_count;
	}

	public void setDate_of_mandate(Date date_of_mandate) {
		this.date_of_mandate = date_of_mandate;
	}

	public void setAmend_dt(String amend_dt) {
		this.amend_dt = amend_dt;
	}

	public void setMdt_xml_file_name(String mdt_xml_file_name) {
		this.mdt_xml_file_name = mdt_xml_file_name;
	}

	public void setMdt_jpeg_img_name(String mdt_jpeg_img_name) {
		this.mdt_jpeg_img_name = mdt_jpeg_img_name;
	}

	public void setMdt_tiff_img_name(String mdt_tiff_img_name) {
		this.mdt_tiff_img_name = mdt_tiff_img_name;
	}

	public void setMdt_zip_folder_name(String mdt_zip_folder_name) {
		this.mdt_zip_folder_name = mdt_zip_folder_name;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	public MdtInitRequestH2h(String uniqueId, String transaction_type, String occurence, String frequency,
			Date first_collection_date, Date final_collection_date, String amount_type, String amount,
			String creditor_name, String creditor_utility_code, String creditor_identification,
			String creditor_identification_no, String debtor_name, String debtor_account_no, String debtor_account_type,
			String debtor_bank_name, String debtor_identification, String umrn, String status,
			String benificiary_status, String reject_reason, String debtor_identification_no, int seq_no,
			Date creation_date, Date last_modified_date, String mdt_request, String bank_name, byte[] jpg_bytes,
			byte[] tif_bytes, String reason_code, String category_code, String creditor_account_no, Date trn_date,
			String creditor_account_no_type, String until_cancel, String deb_emailid, String deb_mobno,
			String is_sms_sent, String transaction_id, String otpcount, String npci_refmsgid, String schm_nm,
			String emst, String emcredt, String reason_code_count, Date date_of_mandate, String amend_dt,
			String mdt_xml_file_name, String mdt_jpeg_img_name, String mdt_tiff_img_name, String mdt_zip_folder_name,
			String reasonDesc) {
		super();
		this.uniqueId = uniqueId;
		this.transaction_type = transaction_type;
		this.occurence = occurence;
		this.frequency = frequency;
		this.first_collection_date = first_collection_date;
		this.final_collection_date = final_collection_date;
		this.amount_type = amount_type;
		this.amount = amount;
		this.creditor_name = creditor_name;
		this.creditor_utility_code = creditor_utility_code;
		this.creditor_identification = creditor_identification;
		this.creditor_identification_no = creditor_identification_no;
		this.debtor_name = debtor_name;
		this.debtor_account_no = debtor_account_no;
		this.debtor_account_type = debtor_account_type;
		this.debtor_bank_name = debtor_bank_name;
		this.debtor_identification = debtor_identification;
		this.umrn = umrn;
		this.status = status;
		this.benificiary_status = benificiary_status;
		this.reject_reason = reject_reason;
		this.debtor_identification_no = debtor_identification_no;
		this.seq_no = seq_no;
		this.creation_date = creation_date;
		this.last_modified_date = last_modified_date;
		this.mdt_request = mdt_request;
		this.bank_name = bank_name;
		this.jpg_bytes = jpg_bytes;
		this.tif_bytes = tif_bytes;
		this.reason_code = reason_code;
		this.category_code = category_code;
		this.creditor_account_no = creditor_account_no;
		this.trn_date = trn_date;
		this.creditor_account_no_type = creditor_account_no_type;
		this.until_cancel = until_cancel;
		this.deb_emailid = deb_emailid;
		this.deb_mobno = deb_mobno;
		this.is_sms_sent = is_sms_sent;
		this.transaction_id = transaction_id;
		this.otpcount = otpcount;
		this.npci_refmsgid = npci_refmsgid;
		this.schm_nm = schm_nm;
		this.emst = emst;
		this.emcredt = emcredt;
		this.reason_code_count = reason_code_count;
		this.date_of_mandate = date_of_mandate;
		this.amend_dt = amend_dt;
		this.mdt_xml_file_name = mdt_xml_file_name;
		this.mdt_jpeg_img_name = mdt_jpeg_img_name;
		this.mdt_tiff_img_name = mdt_tiff_img_name;
		this.mdt_zip_folder_name = mdt_zip_folder_name;
		this.reasonDesc = reasonDesc;
	}

	public MdtInitRequestH2h() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MdtInitRequestH2h [uniqueId=" + uniqueId + ", transaction_type=" + transaction_type + ", occurence="
				+ occurence + ", frequency=" + frequency + ", first_collection_date=" + first_collection_date
				+ ", final_collection_date=" + final_collection_date + ", amount_type=" + amount_type + ", amount="
				+ amount + ", creditor_name=" + creditor_name + ", creditor_utility_code=" + creditor_utility_code
				+ ", creditor_identification=" + creditor_identification + ", creditor_identification_no="
				+ creditor_identification_no + ", debtor_name=" + debtor_name + ", debtor_account_no="
				+ debtor_account_no + ", debtor_account_type=" + debtor_account_type + ", debtor_bank_name="
				+ debtor_bank_name + ", debtor_identification=" + debtor_identification + ", umrn=" + umrn + ", status="
				+ status + ", benificiary_status=" + benificiary_status + ", reject_reason=" + reject_reason
				+ ", debtor_identification_no=" + debtor_identification_no + ", seq_no=" + seq_no + ", creation_date="
				+ creation_date + ", last_modified_date=" + last_modified_date + ", mdt_request=" + mdt_request
				+ ", bank_name=" + bank_name + ", reason_code=" + reason_code + ", category_code=" + category_code
				+ ", creditor_account_no=" + creditor_account_no + ", trn_date=" + trn_date
				+ ", creditor_account_no_type=" + creditor_account_no_type + ", until_cancel=" + until_cancel
				+ ", deb_emailid=" + deb_emailid + ", deb_mobno=" + deb_mobno + ", is_sms_sent=" + is_sms_sent
				+ ", transaction_id=" + transaction_id + ", otpcount=" + otpcount + ", npci_refmsgid=" + npci_refmsgid
				+ ", schm_nm=" + schm_nm + ", emst=" + emst + ", emcredt=" + emcredt + ", reason_code_count="
				+ reason_code_count + ", date_of_mandate=" + date_of_mandate + ", amend_dt=" + amend_dt
				+ ", mdt_xml_file_name=" + mdt_xml_file_name + ", mdt_jpeg_img_name=" + mdt_jpeg_img_name
				+ ", mdt_tiff_img_name=" + mdt_tiff_img_name + ", mdt_zip_folder_name=" + mdt_zip_folder_name
				+ ", reasonDesc=" + reasonDesc + ", getUniqueId()=" + getUniqueId() + ", getTransaction_type()="
				+ getTransaction_type() + ", getOccurence()=" + getOccurence() + ", getFrequency()=" + getFrequency()
				+ ", getFirst_collection_date()=" + getFirst_collection_date() + ", getFinal_collection_date()="
				+ getFinal_collection_date() + ", getAmount_type()=" + getAmount_type() + ", getAmount()=" + getAmount()
				+ ", getCreditor_name()=" + getCreditor_name() + ", getCreditor_utility_code()="
				+ getCreditor_utility_code() + ", getCreditor_identification()=" + getCreditor_identification()
				+ ", getCreditor_identification_no()=" + getCreditor_identification_no() + ", getDebtor_name()="
				+ getDebtor_name() + ", getDebtor_account_no()=" + getDebtor_account_no()
				+ ", getDebtor_account_type()=" + getDebtor_account_type() + ", getDebtor_bank_name()="
				+ getDebtor_bank_name() + ", getDebtor_identification()=" + getDebtor_identification() + ", getUmrn()="
				+ getUmrn() + ", getStatus()=" + getStatus() + ", getBenificiary_status()=" + getBenificiary_status()
				+ ", getReject_reason()=" + getReject_reason() + ", getDebtor_identification_no()="
				+ getDebtor_identification_no() + ", getSeq_no()=" + getSeq_no() + ", getCreation_date()="
				+ getCreation_date() + ", getLast_modified_date()=" + getLast_modified_date() + ", getMdt_request()="
				+ getMdt_request() + ", getBank_name()=" + getBank_name() + ", getJpg_bytes()="
				+ Arrays.toString(getJpg_bytes()) + ", getTif_bytes()=" + Arrays.toString(getTif_bytes())
				+ ", getReason_code()=" + getReason_code() + ", getCategory_code()=" + getCategory_code()
				+ ", getCreditor_account_no()=" + getCreditor_account_no() + ", getTrn_date()=" + getTrn_date()
				+ ", getCreditor_account_no_type()=" + getCreditor_account_no_type() + ", getUntil_cancel()="
				+ getUntil_cancel() + ", getDeb_emailid()=" + getDeb_emailid() + ", getDeb_mobno()=" + getDeb_mobno()
				+ ", getIs_sms_sent()=" + getIs_sms_sent() + ", getTransaction_id()=" + getTransaction_id()
				+ ", getOtpcount()=" + getOtpcount() + ", getNpci_refmsgid()=" + getNpci_refmsgid() + ", getSchm_nm()="
				+ getSchm_nm() + ", getEmst()=" + getEmst() + ", getEmcredt()=" + getEmcredt()
				+ ", getReason_code_count()=" + getReason_code_count() + ", getDate_of_mandate()="
				+ getDate_of_mandate() + ", getAmend_dt()=" + getAmend_dt() + ", getMdt_xml_file_name()="
				+ getMdt_xml_file_name() + ", getMdt_jpeg_img_name()=" + getMdt_jpeg_img_name()
				+ ", getMdt_tiff_img_name()=" + getMdt_tiff_img_name() + ", getMdt_zip_folder_name()="
				+ getMdt_zip_folder_name() + ", getReasonDesc()=" + getReasonDesc() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	 

}
