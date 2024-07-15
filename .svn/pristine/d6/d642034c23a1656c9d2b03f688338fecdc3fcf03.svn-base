package com.example.demo.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.FilenameFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.example.demo.Dao.MandateMasterImdh2hRepository;
import com.example.demo.Dao.UpdatemasterRepo;
import com.example.demo.Dao.BankDetailsAchRepository;

@Service
@Component
public class DeleteMdtService {

	private final Logger logger = Logger.getLogger(DeleteMdtService.class);
	@Autowired
	UpdatemasterRepo mdtRepod;

	@Autowired
	MandateMasterImdh2hRepository mdtRepo;

	@Autowired
	BankDetailsAchRepository bankach;

	@Autowired
	BankDetailsAchRepository bankAchRepo;

	@Value("${NPCI}")
	private String NPCI;
	@Value("${MDTINWresToNPCI}")
	private String MDTINWresToNPCI;

	public List<Object[]> getUmrn(String bankName, String umrn) {

		List<Object[]> resultSet = mdtRepo.findByBankNameAndUmrn(bankName, umrn);
		if (resultSet == null) {
			logger.info("No Data found for cancelation");
		}
		return resultSet;
	}
	public String deleteumrn(String umrn) {
		String status = "";
		try {
			int deleteMandateByUmrnNo = mdtRepod.deleteMandateByUmrnNo(umrn);

			if (deleteMandateByUmrnNo > 0) {
				status = "SUCCESS";
				logger.info("Record Cancelled Succesfully");
			} else {
				logger.info("UMRN UPDATE IN mandate_master_imd_h2h FOR DELETE Else: "+status);
			}
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		}
		return status;
	}

	public static FilenameFilter filenameFilter = new FilenameFilter() {
		Date myDate = new Date();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(myDate).replace("-", "");

		@Override
		public boolean accept(File arg0, String fileName) {
			return fileName.contains(date) && !fileName.contains(".zip");
		}
	};

	public String createXlxForDltMdt(String UMRN, String banknm)
			throws ParserConfigurationException, TransformerException, IOException {
		logger.info("CREATE XLX FOR DELETE MANDATE :");
		String msId = "";
		String mdtId = "";
		String SrcMICR = "";
		String DstMICR = "";
		String sourcingBnkNm = "";
		String destbnkNm = "";
		String other = "";
		String status = "";
		String bankShortCode = "";
		String bnkLogin = "";
		try {
			ArrayList<String> rs = mdtRepo.selectDatabyUmrn(UMRN);
			if (rs != null && !rs.isEmpty()) {
				String msIdfull = rs.get(0);
				String[] rs1234 = msIdfull.split(",");

				if (rs1234.length >= 6) {
					msId = rs1234[0];
					mdtId = rs1234[1];
					SrcMICR = rs1234[2];
					DstMICR = rs1234[3];
					sourcingBnkNm = rs1234[4];
					destbnkNm = rs1234[5];
					other = rs1234[6];

				} else {
					logger.info("\"Error: rs1234 is null or doesn't contain enough elements." + rs1234);
				}
			} else {
				logger.info("Error: rs1234 is null or empty.");
			}
			List<String> getSortnId = bankach.getSortandIdByBANKN(banknm);
			if (getSortnId != null) {
				String x = getSortnId.get(0);
				String[] getSortandId = x.split(",");
				if (getSortandId.length >= 1) {
					bankShortCode = getSortandId[0];
					bnkLogin = getSortandId[1];
				} else {
					logger.info("\"Error: getSortandId is null or doesn't contain enough elements." + getSortandId);
				}
			} else {
				logger.info("Error: getSortandId is null or empty.");
			}

			// Create XML document
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			// Root element
			Element root = doc.createElement("Document");
			doc.appendChild(root);
			Attr att = doc.createAttribute("xmlns");
			att.setValue("urn:iso:std:iso:20022:tech:xsd:pain.009.001.01");
			root.setAttributeNode(att);

			// MndtCxlReq element
			Element rootElement = doc.createElement("MndtCxlReq");
			root.appendChild(rootElement);

			// Populate XML elements with retrieved data
			Element GrpHdr = doc.createElement("GrpHdr");
			rootElement.appendChild(GrpHdr);

			Element MsgId = doc.createElement("MsgId");
			MsgId.appendChild(doc.createTextNode(msId != null ? msId : ""));
			GrpHdr.appendChild(MsgId);

			Element CreDtTm = doc.createElement("CreDtTm");
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
			Date now = new Date();
			String strDate1 = sd.format(now);
			String strDate = new StringBuffer(strDate1).insert(10, "T").toString();
			CreDtTm.appendChild(doc.createTextNode(strDate != null ? strDate : ""));
			GrpHdr.appendChild(CreDtTm);

			Element InitgPty = doc.createElement("InitgPty");
			GrpHdr.appendChild(InitgPty);
			Element Id = doc.createElement("Id");
			GrpHdr.appendChild(Id);
			Element OrgId = doc.createElement("OrgId");
			GrpHdr.appendChild(OrgId);
			Element Othr = doc.createElement("Othr");
			GrpHdr.appendChild(Othr);
			Element CId = doc.createElement("Id");
			CId.appendChild(doc.createTextNode(other != null ? other : ""));
			Othr.appendChild(CId);
			Element InstgAgt = doc.createElement("InstgAgt");
			GrpHdr.appendChild(InstgAgt);
			Element FinInstnId = doc.createElement("FinInstnId");
			InstgAgt.appendChild(FinInstnId);
			Element ClrSysMmbId = doc.createElement("ClrSysMmbId");
			FinInstnId.appendChild(ClrSysMmbId);
			Element MmbId = doc.createElement("MmbId");
			MmbId.appendChild(doc.createTextNode(SrcMICR != null ? SrcMICR : ""));
			ClrSysMmbId.appendChild(MmbId);
			Element Nm = doc.createElement("Nm");
			Nm.appendChild(doc.createTextNode(sourcingBnkNm != null ? sourcingBnkNm : ""));
			FinInstnId.appendChild(Nm);
			Element DestinationInstgAgt = doc.createElement("InstgAgt");
			GrpHdr.appendChild(DestinationInstgAgt);
			Element DestinationFinInstnId = doc.createElement("FinInstnId");
			DestinationInstgAgt.appendChild(DestinationFinInstnId);
			Element DestinationClrSysMmbId = doc.createElement("ClrSysMmbId");
			DestinationFinInstnId.appendChild(DestinationClrSysMmbId);
			Element DestinationMmbId = doc.createElement("MmbId");
			DestinationMmbId.appendChild(doc.createTextNode(DstMICR != null ? DstMICR : ""));
			DestinationClrSysMmbId.appendChild(DestinationMmbId);
			Element DestinationNm = doc.createElement("Nm");
			DestinationNm.appendChild(doc.createTextNode(destbnkNm != null ? destbnkNm : ""));
			DestinationFinInstnId.appendChild(DestinationNm);
			Element UndrlygCxlDtls = doc.createElement("UndrlygCxlDtls");
			rootElement.appendChild(UndrlygCxlDtls);
			Element CxlRsn = doc.createElement("CxlRsn");
			UndrlygCxlDtls.appendChild(CxlRsn);
			Element Rsn = doc.createElement("Rsn");
			CxlRsn.appendChild(Rsn);
			Element Prtry = doc.createElement("Prtry");
			Prtry.appendChild(doc.createTextNode("MD16"));
			Rsn.appendChild(Prtry);

			Element OrgnlMndt = doc.createElement("OrgnlMndt");
			UndrlygCxlDtls.appendChild(OrgnlMndt);

			Element OrgnlMndtId = doc.createElement("OrgnlMndtId");
			OrgnlMndtId.appendChild(doc.createTextNode(mdtId != null ? mdtId : ""));
			OrgnlMndt.appendChild(OrgnlMndtId);

			Date myDate = new Date();
			String date = new SimpleDateFormat("dd-MM-yyyy").format(myDate).replace("-", "");
			String rootpath = NPCI + banknm.trim() + MDTINWresToNPCI + "Cancel" + "/";

			String fileName = "";
			File f = new File(rootpath);
			if (!f.exists()) {
				f.mkdirs();
			}
			String[] file_names = f.list(filenameFilter);
			if (file_names.length > 0) {
				ArrayList<Integer> file_seq = new ArrayList<Integer>();
				for (String file : file_names) {
					String[] filedata = file.split("-");
					int Seq = Integer.parseInt(filedata[5].replace(".xml", ""));
					file_seq.add(Seq);
				}

				int max_seq = Collections.max(file_seq);
				fileName = String.format("%06d", max_seq + 1);
			} else {
				fileName = String.format("%06d", 0);
			}
			String xmlfile = rootpath + "MMS-CANCEL" + "-" + bankShortCode + "-" + bnkLogin + "-" + date + "-"
					+ fileName + ".xml";
			StreamResult result = new StreamResult(new File(xmlfile));
			StreamResult result1 = new StreamResult(System.out);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			transformer.transform(source, result);
			status = "SUCCESS";
			transformer.transform(source, result);
			transformer.transform(source, result1);
			String zipFile = fileName + ".zip";
			String[] srcFiles = { xmlfile };

			try {

				byte[] buffer = new byte[1024];
				FileOutputStream fos = new FileOutputStream(zipFile);
				ZipOutputStream zos = new ZipOutputStream(fos);
				for (int i = 0; i < srcFiles.length; i++) {
					File srcFile = new File(srcFiles[i]);
					FileInputStream fis = new FileInputStream(srcFile);
					zos.putNextEntry(new ZipEntry(srcFile.getName()));
					int length1;
					while ((length1 = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, length1);
					}
					zos.closeEntry();
					fis.close();
				}
				zos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (TransformerException tfe) {
			logger.info(" DELETE MANDATE XLX  2:" + tfe);
			status = "FAIL";
			tfe.printStackTrace();
		}
		return status;
	}
}
