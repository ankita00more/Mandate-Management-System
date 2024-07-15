package com.example.demo.Service;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.example.demo.Dao.AuthoriseButtonRepository;
import com.example.demo.entity.BankdetailsAch;
import com.example.demo.entity.MdtSeq;
import com.example.demo.entity.xmlSeq;
import com.example.demo.utilities.ZipFiles;
import com.example.demo.Dao.BankDetailsAchRepository;
import com.example.demo.Dao.MandateSequenceRepository;
import com.example.demo.Dao.UpdatemasterRepo;
import com.example.demo.Dao.XmlSeqRepository;
import com.example.demo.entity.mdtBean;

@Service
public class AuthoriseButtonService {

	private final Logger logger = Logger.getLogger(AuthoriseButtonService.class);

	@Autowired
	private MandateSequenceRepository mdtseqrepo;

	@Autowired
	private BankDetailsAchRepository bankdetailAchrepo;

	@Autowired
	private AuthoriseButtonRepository mdtRepo;

	@Autowired
	private UpdatemasterRepo updateRepo;

	@Autowired
	private XmlSeqRepository xmlrepol;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ZipFiles zipfile;

	@Value("${NPCI}")
	private String NPCI;
	@Value("${INW_MDT_SIGNED_TO_NPCI}")
	private String INW_MDT_SIGNED_TO_NPCI;

	public String AccptMdt(mdtBean mdt1, String bankname, String file_nm, Model model, String rsncodes[],
			String accept_chk[], String reject_chk[]) {
		if (accept_chk != null) {
			accept_chk = (accept_chk.length == 0) ? null : accept_chk;
		}
		if (reject_chk != null) {
			reject_chk = (reject_chk.length == 0) ? null : reject_chk;
		}
		String[] resn_code1 = (rsncodes != null) ? rsncodes : new String[0];
		String[] without_blank = new String[resn_code1.length];
		int count = 0;
		for (int i = 0; i < resn_code1.length; i++) {
			if (!resn_code1[i].equals("")) {
				without_blank[count] = resn_code1[i];
				count++;
			}
		}
		boolean updt_flag = (reject_chk != null && accept_chk != null) || (reject_chk == null && accept_chk != null)
				|| (reject_chk != null && accept_chk == null);
		if (updt_flag) {
			if (MDT_update_proc(accept_chk, reject_chk, without_blank, file_nm, mdt1)) {
				model.addAttribute("ResMsg", "File " + file_nm + " Has been authorize successfully");
				logger.info("File " + file_nm + " Has been authorize successfully");
				return "redirect:/processMandate";
			} else {
				logger.info("File " + file_nm + "MANDATE IS NOT AUTHORIZE");
				model.addAttribute("ResMsg", "MANDATE IS NOT AUTHORIZE");
				return "redirect:/processMandate";
			}
		}
		return "error";
	}

	public boolean MDT_update_proc(String[] acc_chk, String[] rej_chk, String[] resn_code, String file_nm, mdtBean mdt)
			throws NullPointerException {
		boolean sts = false;
		boolean status_acc = false;
		boolean status_rej = false;
		String tranType = mdt.getMDT_REQ_FOR();
		String Seq_no = "000000";
		String bank_name = mdt.getBANK_NAME();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		String date = dateFormat.format(cal.getTime());
		String count_perDay = mdtseqrepo.checkMaxseqPerDayandBank(bank_name, date);
		int IntCountperday = 0;
		if (count_perDay != null) {
			IntCountperday = Integer.parseInt(count_perDay);
		}
		if (IntCountperday == 0) {
			Seq_no = "000001";
		} else {
			IntCountperday = IntCountperday + 1;
			Seq_no = String.format("%06d", IntCountperday);
		}
		MdtSeq mdtEnt = new MdtSeq();
		mdtEnt.setSeq(Seq_no);
		mdtEnt.setBankname(bank_name);
		mdtEnt.setCreation_date(date);
		mdtseqrepo.save(mdtEnt);

		String FromNPCI = INW_MDT_SIGNED_TO_NPCI;
		String Foldr_path = NPCI + bank_name.trim() + FromNPCI + "//" + tranType;

		Optional<BankdetailsAch> bankdetails = bankdetailAchrepo.findById(bank_name);
		if (bankdetails != null) {
			mdt.setBANK_IIN(bankdetails.get().getIin());
			mdt.setBANK_shortcode(bankdetails.get().getShortcode());
			mdt.setBANK_uid(bankdetails.get().getBankloginid());
		}
		SimpleDateFormat dateFormats = new SimpleDateFormat("ddMMyyyy");
		String Todaydate = dateFormats.format(cal.getTime());
		logger.info("Updation authorization status");
		if (acc_chk == null || acc_chk.equals(null)) {
			status_acc = true;
		} else {
			for (int i = 0; i < acc_chk.length; i++) {
				String umnr = acc_chk[i].toString().trim();
				mdt.setSTATUS_RSN_CODE("ac01");
				mdt.setUMRN(umnr);
				mdt.setMDT_INW_RES_FOLDRNAME(Foldr_path);
				mdt.setSTATUS("A");
				status_acc = mdtRepo.processMdtData(mdt, entityManager);
			}
		}
		if (rej_chk == null || rej_chk.equals(null)) {
			status_rej = true;
		} else {
			for (int i = 0; i < rej_chk.length; i++) {
				String umnr = rej_chk[i].toString().trim();
				mdt.setSTATUS_RSN_CODE(resn_code[i]);
				mdt.setUMRN(umnr);
				mdt.setMDT_INW_RES_FOLDRNAME(Foldr_path);
				mdt.setSTATUS("R");
				status_rej = mdtRepo.processMdtData(mdt, entityManager);
			}
		}
		if (status_acc == true && status_rej == true) {
			sts = mdt_Create_Accept_folder(mdt, Foldr_path, "MMS-" + tranType + "-" + mdt.getBANK_shortcode() + "-"
					+ mdt.getBANK_uid() + "-" + Todaydate + "-" + Seq_no + "-ACCEPT", date);
		}
		return sts;
	}

	private boolean mdt_Create_Accept_folder(mdtBean mdt, String Folder_path, String folder_name, String date) {

		boolean sts = false;
		File sourceFile = null;
		String destinationPath = "";
		String accept_folder="ACCEPT_MDT_SIGNED_TO_NPCI";
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
			String Todaydate = dateFormat.format(cal.getTime());
			String bankname = mdt.getBANK_NAME();
			String zip_foldr = folder_name.replace(".zip", "");
			File Accept_file = new File(Folder_path + "/" + zip_foldr);//root+inwmdt+accept
			if (!Accept_file.isDirectory()) {
				Accept_file.mkdirs();
				logger.info("Created" + Accept_file);
			}

			List<Object[]> listof = mdtRepo.findMandatesPain(mdt.getMDT_ZIP_FILE_NAME(), bankname);

			String uid = "";
			uid = mdt.getBANK_uid();
			String msgid = "";

			String creatn_tim = "";
			String instcting_id = "";

			String instcted_id = "";
			String instcting_nm = "";
			String instcted_nm = "";

			String msd_id = "";
			String MsgNmid = "";
			String accepted_sts = "";
			String rej_rsn_code = "";
			String Seq_no = "";
			String trn_typ = "";
			mdt.setMDT_REQ_FOR("ACCEPT");
			trn_typ = mdt.getMDT_REQ_FOR();
			String shortcode = "";
			shortcode = mdt.getBANK_shortcode();

			for (Object[] list : listof) {

				xmlSeq xm = xmlrepol.findByIdandDate(Todaydate, bankname);
				xmlSeq xmlentity = new xmlSeq();

				if (xm == null) {
					xmlentity.setBANKNAME(bankname);
					xmlentity.setACCEPT_FOLDER(folder_name);
					xmlentity.setFILE_DATE(Todaydate);
					xmlentity.setFILE_SEQ("000000");

					xmlrepol.save(xmlentity);

				} else {
					String xm_seq = xm.getFILE_SEQ();
					xmlentity.setFILE_SEQ(xm_seq);

				}

				mdtBean mdtbean = new mdtBean();
				mdtbean.setUMRN((String) list[0]);
				mdtbean.setDEBITOR_ACC_NUM((String) list[1]);
				mdtbean.setMDT_REQ_ID((String) list[2]);
				mdtbean.setMDT_CREATION_TIME((String) list[3]);
				mdtbean.setMEMBER_ID_INSTRCTING((String) list[4]);
				mdtbean.setMEMBER_ID_INSTRUCTED((String) list[5]);
				mdtbean.setMSG_ID((String) list[6]);
				mdtbean.setMsg_nm_id((String) list[7]);
				mdtbean.setSTATUS((String) list[8]);
				mdtbean.setSTATUS_RSN_CODE((String) list[9]);
				mdtbean.setMDT_ZIP_FILE_NAME((String) list[10]);
				mdtbean.setMDT_INW_RES_FLAG((String) list[11]);
				mdtbean.setMDT_DETAIL_FRNT_IMG_NAME((String) list[12]);
				mdtbean.setMDT_DETAIL_FRNT_IMG((String) list[14]);
				String umrn = mdtbean.getUMRN();
				creatn_tim = mdtbean.getMDT_CREATION_TIME();
				instcting_id = mdtbean.getMEMBER_ID_INSTRCTING();
				instcted_id = mdtbean.getMEMBER_ID_INSTRUCTED();
				instcting_nm = mdtbean.getMEMBER_NAME_INSTRCTING();
				instcted_nm = mdtbean.getMEMBER_NAME_INSTRUCTED();
				msd_id = mdtbean.getMSG_ID();
				msgid = mdtbean.getMSG_ID();
				MsgNmid = mdtbean.getMsg_nm_id();
				accepted_sts = mdtbean.getSTATUS();
				rej_rsn_code = mdtbean.getSTATUS_RSN_CODE();
				String a[] = mdtbean.getMDT_DETAIL_FRNT_IMG_NAME().split("-");
				int split_value = a.length;
				String file_date = "";
				if (split_value == 6) {
					file_date = a[4];
				}
				String Seq_no1 = xmlentity.getFILE_SEQ();
				int xml_seq = Integer.parseInt(Seq_no1) + 1;
				Seq_no1 = String.valueOf(xml_seq);
				int len = 6 - Seq_no1.length();
				for (int j = 0; j < len; j++) {
					Seq_no1 = "0" + Seq_no1;
				}

				String Accept_file_nm = "MMS-" + trn_typ + "-" + shortcode + "-" + uid + "-" + date + "-" + Seq_no1
						+ "-INP";

				Namespace docnmspc = Namespace.getNamespace("urn:iso:std:iso:20022:tech:xsd:pain.012.001.01");
				Element dc = new Element("Document", docnmspc);
				org.jdom2.Document doc = new org.jdom2.Document(dc);
				Element MndtAccptncRpt = new Element("MndtAccptncRpt", docnmspc);
				Element GrpHdr = new Element("GrpHdr", docnmspc);
				MndtAccptncRpt.addContent(GrpHdr);
				GrpHdr.addContent(new Element("MsgId", docnmspc).setText(msgid));
				GrpHdr.addContent(new Element("CreDtTm", docnmspc).setText(creatn_tim));
				Element InstgAgt = new Element("InstgAgt", docnmspc);
				Element FinInstnId = new Element("FinInstnId", docnmspc);
				Element ClrSysMmbId = new Element("ClrSysMmbId", docnmspc);
				Element MmbId = new Element("MmbId", docnmspc);
				ClrSysMmbId.addContent(0, MmbId.setText(instcted_id));
				FinInstnId.addContent(0, ClrSysMmbId);
				InstgAgt.addContent(FinInstnId);
				GrpHdr.addContent(InstgAgt);// InstdAgt
				Element InstdAgt = new Element("InstdAgt", docnmspc);
				Element FinInstnId1 = new Element("FinInstnId", docnmspc);
				Element ClrSysMmbId1 = new Element("ClrSysMmbId", docnmspc);
				Element MmbId1 = new Element("MmbId", docnmspc);
				ClrSysMmbId1.addContent(0, MmbId1.setText(instcting_id));
				FinInstnId1.addContent(0, ClrSysMmbId1);
				InstdAgt.addContent(FinInstnId1);
				GrpHdr.addContent(InstdAgt);
				Element UndrlygAccptncDtls = new Element("UndrlygAccptncDtls", docnmspc);
				Element OrgnlMsgInf = new Element("OrgnlMsgInf", docnmspc);
				Element MsgId = new Element("MsgId", docnmspc);
				Element MsgNmId = new Element("MsgNmId", docnmspc);
				Element CreDtTm = new Element("CreDtTm", docnmspc);
				OrgnlMsgInf.addContent(0, MsgId.setText(msd_id));
				OrgnlMsgInf.addContent(1, MsgNmId.setText(MsgNmid));
				OrgnlMsgInf.addContent(2, CreDtTm.setText(creatn_tim));
				Element AccptncRslt = new Element("AccptncRslt", docnmspc);
				Element Accptd = new Element("Accptd", docnmspc);
				Element RjctRsn = new Element("RjctRsn", docnmspc);
				Element Prtry = new Element("Prtry", docnmspc);
				RjctRsn.addContent(Prtry.setText(rej_rsn_code));
				AccptncRslt.addContent(0, Accptd.setText(accepted_sts));
				AccptncRslt.addContent(1, RjctRsn);
				Element OrgnlMndt = new Element("OrgnlMndt", docnmspc);
				Element OrgnlMndtId = new Element("OrgnlMndtId", docnmspc);
				OrgnlMndt.addContent(OrgnlMndtId.setText(umrn));
				UndrlygAccptncDtls.addContent(0, OrgnlMsgInf);
				UndrlygAccptncDtls.addContent(1, AccptncRslt);
				UndrlygAccptncDtls.addContent(2, OrgnlMndt);
				MndtAccptncRpt.addContent(UndrlygAccptncDtls);
				doc.getRootElement().addContent(MndtAccptncRpt);
				XMLOutputter xmlOutput = new XMLOutputter();
				String xml_accept_filenm = Accept_file.getAbsolutePath() + "/" + Accept_file_nm + ".xml";
				xmlOutput.setFormat(Format.getPrettyFormat());
				xmlOutput.output(doc, new FileWriter(xml_accept_filenm));
				mdt.setMDT_INW_RES_FILENAME(Accept_file_nm + ".xml");
				mdt.setMDT_ZIP_FILE_NAME(zip_foldr);
				String mm = mdt.getMDT_REQ_FOR();
				mdt.setMDT_REQ_FOR(mm);
				mdt.setUMRN(umrn);

				if (mdtRepo.processMdtData(mdt, entityManager)) {
					sts = true;
				} else {
					sts = false;
				}

				if (Accept_file_nm != null) {
					String xmlfilename = Accept_file_nm + ".xml";
					logger.info("xmlfilename: " + xmlfilename);

					int c1 = updateRepo.updateFlagg("Y", xmlfilename, zip_foldr, umrn);
					int stmt = updateRepo.updateXML(xml_seq, folder_name, bankname, Todaydate);
					if (stmt > 0 && c1 > 0) {
						sts = true;
					} else {
						sts = false;
					}
				}
				sourceFile = new File(Folder_path + "/" + zip_foldr);
//				destinationPath = Folder_path + "//" + folder_name + ".zip";
				destinationPath = NPCI +"//" +bankname+"//" +accept_folder+"//" + zip_foldr + ".zip";
			}

			zipfile.zipDirectory(sourceFile, destinationPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sts;
	}
}
