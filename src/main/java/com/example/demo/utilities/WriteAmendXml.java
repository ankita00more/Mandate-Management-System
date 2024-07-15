package com.example.demo.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.example.demo.Service.AuthGenServiceImpl;
import com.example.demo.entity.MdtInitRequestH2h;

public class WriteAmendXml {

	private final Logger logger = Logger.getLogger(WriteAmendXml.class);
	public boolean createMnadateInputXML(MdtInitRequestH2h customer,String bankname,String Validity_flag,String bankloginid,
			String shortcode,String tiff_path,String jpg_path,String destpath,String seqno,String zippath) throws ParserConfigurationException {
		
			boolean flag = false;
		    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		    Date now1 = new Date();
		    SimpleDateFormat timeFormatter = new SimpleDateFormat("ddMMyyyy");
		    String Currentdate = timeFormatter.format(now1);
		    System.out.println("Data for xml -->"+customer);
		    
		    logger.info("Data for xml -->"+customer);
		    try 
		    {
		    	
				String filepath = String.valueOf(destpath.trim())+customer.getUniqueId()+"/AMEND"+ "/" + "MMS-AMEND-" +shortcode+
						"-"+bankloginid+"-"+Currentdate+"-"+seqno+ "-INP" + ".xml";
			
				System.out.println("File path will be -->"+filepath);
				
				File files = new File(String.valueOf(destpath) + customer.getUniqueId() + "/AMEND");
				
				if (!files.exists()) {
					System.out.println("creating directory:" + destpath + customer.getUniqueId() + "/AMEND");
					boolean bool = false;
					try {
				          files.mkdirs();
				          bool = true;
				    } 
					catch (SecurityException securityException) {}
					
					if (bool)
				          System.out.println("DIR created"); 
				}
				
				File uploadedFile = new File(filepath);
			    Document doc = docBuilder.newDocument();
			    
			    doc.setXmlStandalone(true);
			    Element rootElement = doc.createElement("Document");
			    doc.appendChild(rootElement);
			    
			    Attr att = doc.createAttribute("xmlns");
			    att.setValue("urn:iso:std:iso:20022:tech:xsd:pain.010.001.01");
			    rootElement.setAttributeNode(att);
			    
			    Element mndini = doc.createElement("MndtAmdmntReq");
			    rootElement.appendChild(mndini);
			    
			    Element group = doc.createElement("GrpHdr");
			    mndini.appendChild(group);
			    
			    Element msgid = doc.createElement("MsgId");
			    msgid.appendChild(doc.createTextNode(customer.getUniqueId()));
			    group.appendChild(msgid);
			    
			    Element crdt = doc.createElement("CreDtTm");
			    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
			    SimpleDateFormat localdate = new SimpleDateFormat("yyyy-MM-dd");
			    Date now = new Date();
			    
			    String today = localdate.format(now);
			    System.out.println("today"+today);
			    String strDate1 = sd.format(now);
			    
			    String strDate = (new StringBuffer(strDate1)).insert(10, "T").toString();
			    crdt.appendChild(doc.createTextNode(strDate));
			    group.appendChild(crdt);
			    
			    Element InstgAgt = doc.createElement("InstgAgt");
			    group.appendChild(InstgAgt);
			    
			    Element FinInstnId = doc.createElement("FinInstnId");
			    InstgAgt.appendChild(FinInstnId);
			    
			    Element ClrSysMmbId = doc.createElement("ClrSysMmbId");
			    FinInstnId.appendChild(ClrSysMmbId);
			    
			    Element MmbId = doc.createElement("MmbId");
			    MmbId.appendChild(doc.createTextNode(customer.getCreditor_identification_no()));
			    
			    ClrSysMmbId.appendChild(MmbId);
			    Element Nm33 = doc.createElement("Nm");
			    Nm33.appendChild(doc.createTextNode(customer.getBank_name()));
			    FinInstnId.appendChild(Nm33);
		    	
			    Element InstgAgt1 = doc.createElement("InstdAgt");
			    group.appendChild(InstgAgt1);
			    
			    Element FinInstnId1 = doc.createElement("FinInstnId");
			    InstgAgt1.appendChild(FinInstnId1);
			    
			    Element ClrSysMmbId1 = doc.createElement("ClrSysMmbId");
			    FinInstnId1.appendChild(ClrSysMmbId1);
			    
			    Element MmbId1 = doc.createElement("MmbId");
			    MmbId1.appendChild(doc.createTextNode(customer.getDebtor_identification_no()));
			    ClrSysMmbId1.appendChild(MmbId1);
			    
			    Element Nm12 = doc.createElement("Nm");
			    Nm12.appendChild(doc.createTextNode(customer.getDebtor_bank_name()));
			    FinInstnId1.appendChild(Nm12);
			    
			    Element UndrlygAmdmntDtls = doc.createElement("UndrlygAmdmntDtls");
			    mndini.appendChild(UndrlygAmdmntDtls);
			    
			    Element AmdmntRsn = doc.createElement("AmdmntRsn");
			    UndrlygAmdmntDtls.appendChild(AmdmntRsn);
			    
			  
			    Element Rsn = doc.createElement("Rsn");
				AmdmntRsn.appendChild(Rsn);
				Element Prtry = doc.createElement("Prtry");
				Prtry.appendChild(doc.createTextNode(customer.getReason_code()));
				System.out.println("Reason code will be -->"+customer.getReason_code());
				Rsn.appendChild(Prtry);
			    	
			    Element Mndt = doc.createElement("Mndt");
			    UndrlygAmdmntDtls.appendChild(Mndt);
			    Element MndtId = doc.createElement("MndtId");
			    MndtId.appendChild(doc.createTextNode(customer.getUmrn()));
			    Mndt.appendChild(MndtId);
			    
			    Element MndtReqId = doc.createElement("MndtReqId");
			    MndtReqId.appendChild(doc.createTextNode(customer.getUniqueId()));
			    Mndt.appendChild(MndtReqId);
			    
			    Element Tp = doc.createElement("Tp");
			    Mndt.appendChild(Tp);
			    
			    Element SvcLvl = doc.createElement("SvcLvl");
			    Tp.appendChild(SvcLvl);
			    
			    Element Prtry1 = doc.createElement("Prtry");
			    Prtry1.appendChild(doc.createTextNode(customer.getCategory_code()));
			    SvcLvl.appendChild(Prtry1);
			    
			    Element LclInstrm = doc.createElement("LclInstrm");
			    Tp.appendChild(LclInstrm);
			    
			    Element Prtry11 = doc.createElement("Prtry");
			    Prtry11.appendChild(doc.createTextNode(customer.getTransaction_type()));
			    LclInstrm.appendChild(Prtry11);
			    
			    Element Ocrncs = doc.createElement("Ocrncs");
			    Mndt.appendChild(Ocrncs);
			    
			    Element SeqTp = doc.createElement("SeqTp");
			    SeqTp.appendChild(doc.createTextNode(customer.getOccurence()));
			    Ocrncs.appendChild(SeqTp);
			    
			    Element Frqcy = doc.createElement("Frqcy");
			    Frqcy.appendChild(doc.createTextNode(customer.getFrequency()));
			    Ocrncs.appendChild(Frqcy);
			    
			    if(Validity_flag.equalsIgnoreCase("Y")) 
			    {
			    	Date Mdate = customer.getDate_of_mandate();
			    	System.out.println("Date of Mandate will be -->"+Mdate);
			    	
			    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String textMdate = df.format(Mdate);
					System.out.println("textMdate:"+textMdate);
					Element Drtn = doc.createElement("Drtn");
				    Ocrncs.appendChild(Drtn);
				    
				    Element FrDt=doc.createElement("FrDt");
					Drtn.appendChild(FrDt);
					FrDt.appendChild(doc.createTextNode(textMdate));
					System.out.println("Mandate Date will be -->"+textMdate);
			    	
			    }
			    
			    
			    
			    Date FrstColltnDt1 = customer.getFirst_collection_date();
			    System.out.println("First Collection -->"+FrstColltnDt1);
			    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			    String text = df.format(FrstColltnDt1);
			    Element FrstColltnDt = doc.createElement("FrstColltnDt");
			    FrstColltnDt.appendChild(doc.createTextNode(text));
			    Ocrncs.appendChild(FrstColltnDt);
			    
				String until_cancel = customer.getUntil_cancel();
				System.out.println("Until Cancel -->"+customer.getUntil_cancel());
				
				Date FnlColltnDt1 = customer.getFinal_collection_date();
			    System.out.println("Final Collection date -->"+FnlColltnDt1);
				  
				if (!until_cancel.equalsIgnoreCase("Y")) 
				{
			        String text2 = df.format(FnlColltnDt1);
			        Element FnlColltnDt = doc.createElement("FnlColltnDt");
			        FnlColltnDt.appendChild(doc.createTextNode(text2));
			        Ocrncs.appendChild(FnlColltnDt);
			    }   
					
			    if (customer.getAmount_type() != null && (customer.getAmount_type()).equalsIgnoreCase("Fixed")) 
			    {
			        Element ColltnAmt = doc.createElement("ColltnAmt");
			        Attr att12 = doc.createAttribute("Ccy");
			        att12.setValue("INR");
			        ColltnAmt.setAttributeNode(att12);
			        ColltnAmt.appendChild(doc.createTextNode(customer.getAmount()));
			        System.out.println("Get Amount -->"+customer.getAmount());
			        Mndt.appendChild(ColltnAmt);
			        System.out.println("Collection Amount will be -->"+ColltnAmt);
			     } 
			    
			    
			    else if (customer.getAmount_type()!= null && customer.getAmount_type().equalsIgnoreCase("Maximum")) 
			    {
			    	System.out.println("Amount type will be -->"+customer.getAmount_type());
			        Element MaxAmt = doc.createElement("MaxAmt");
			        Attr attr = doc.createAttribute("Ccy");
			        attr.setValue("INR");
			        MaxAmt.setAttributeNode(attr);
			        MaxAmt.appendChild(doc.createTextNode(customer.getAmount()));
			        Mndt.appendChild(MaxAmt);
			    } 
			    
			    Element Cdtr = doc.createElement("Cdtr");
			    Mndt.appendChild(Cdtr);
			    
			    Element Nm1 = doc.createElement("Nm");
			    Nm1.appendChild(doc.createTextNode(customer.getCreditor_name()));
			    System.out.println("Creditor Name will be -->"+customer.getCreditor_name());
			    System.out.println("Creditor Name -->"+customer.getCreditor_name());
			    Cdtr.appendChild(Nm1);
			    
			    Element CdtrAcct = doc.createElement("CdtrAcct");
			    Mndt.appendChild(CdtrAcct);
			    System.out.println("Creditor Account Number will be -->"+CdtrAcct);
			    Element id2 = doc.createElement("Id");
			    CdtrAcct.appendChild(id2);
			    
			    Element Othr1 = doc.createElement("Othr");
			    id2.appendChild(Othr1);
			    
			    Element id3 = doc.createElement("Id");
			    id3.appendChild(doc.createTextNode(customer.getCreditor_utility_code()));
			    System.out.println("Creditor utility code -->"+customer.getCreditor_utility_code());
			    Othr1.appendChild(id3);
			    
			    Element CdtrAgt = doc.createElement("CdtrAgt");
			    Mndt.appendChild(CdtrAgt);
			    Element FinInstnId12 = doc.createElement("FinInstnId");
			    CdtrAgt.appendChild(FinInstnId12);
			    
			    Element ClrSysMmbId12 = doc.createElement("ClrSysMmbId");
			    FinInstnId12.appendChild(ClrSysMmbId12);
			    Element MmbId12 = doc.createElement("MmbId");
			    MmbId12.appendChild(doc.createTextNode(customer.getCreditor_identification_no()));
			    System.out.println("Creditor Identification Number -->"+customer.getCreditor_identification_no());
			    ClrSysMmbId12.appendChild(MmbId12);
			    
			    Element Nm32 = doc.createElement("Nm");
			    Nm32.appendChild(doc.createTextNode(customer.getBank_name()));
			    System.out.println("Bank Name is -->"+customer.getBank_name());
			    FinInstnId12.appendChild(Nm32);
			    
			    Element Dbtr = doc.createElement("Dbtr");
			    Mndt.appendChild(Dbtr);
			    
			    Element Nm3 = doc.createElement("Nm");
			    Nm3.appendChild(doc.createTextNode(customer.getDebtor_name()));
			    System.out.println("Debtor Name is -->"+customer.getDebtor_name());
			    Dbtr.appendChild(Nm3);
			    
			    Element CtcDtls = doc.createElement("CtctDtls");
			    Dbtr.appendChild(CtcDtls);
			    
			    Element Nm4 = doc.createElement("PhneNb");
			    Nm4.appendChild(doc.createTextNode("+91-001-22001000"));
			    CtcDtls.appendChild(Nm4);
			    
			    if (customer.getDeb_mobno() != null) 
			    {
			        Element Nm5 = doc.createElement("MobNb");
			        Nm5.appendChild(doc.createTextNode("+91-" + customer.getDeb_mobno()));
			        System.out.println("Mobile Number is -->"+customer.getDeb_mobno());
			        CtcDtls.appendChild(Nm5);
			    } 
			    else 
			    {
			        Element Nm5 = doc.createElement("MobNb");
			        Nm5.appendChild(doc.createTextNode("+91-9000000000"));
			        CtcDtls.appendChild(Nm5);
			    } 
			    
			    if (customer.getDeb_emailid() != null && !customer.getDeb_emailid().equals("")) 
			    {
			        Element Nm6 = doc.createElement("EmailAdr");
			        Nm6.appendChild(doc.createTextNode(customer.getDeb_emailid()));
			        CtcDtls.appendChild(Nm6);
			    } 
			    else 
			    {
			        Element Nm6 = doc.createElement("EmailAdr");
			        Nm6.appendChild(doc.createTextNode("abc@abc.com"));
			        CtcDtls.appendChild(Nm6);
			    } 
			    
			    
			    Element DbtrAcct = doc.createElement("DbtrAcct");
			    Mndt.appendChild(DbtrAcct);
			    
			    
			    Element Id = doc.createElement("Id");
			    DbtrAcct.appendChild(Id);
			    
			    Element Othr2 = doc.createElement("Othr");
			    Id.appendChild(Othr2);
			    
			    Element Id2 = doc.createElement("Id");
			    Id2.appendChild(doc.createTextNode(customer.getDebtor_account_no()));
			    System.out.println("Debtor account number will be -->"+customer.getDebtor_account_no());
			    Othr2.appendChild(Id2);
			    
			    Element Tp1 = doc.createElement("Tp");
			    DbtrAcct.appendChild(Tp1);
			    
			    Element Prtry2 = doc.createElement("Prtry");
			    Prtry2.appendChild(doc.createTextNode(customer.getDebtor_account_type()));
			    System.out.println("Debtor account type will be -->"+customer.getDebtor_account_type());
			    Tp1.appendChild(Prtry2);
			    
			    Element DbtrAgt = doc.createElement("DbtrAgt");
			    Mndt.appendChild(DbtrAgt);
			    
			    Element FinInstnId2 = doc.createElement("FinInstnId");
			    DbtrAgt.appendChild(FinInstnId2);
			    
			    Element ClrSysMmbId2 = doc.createElement("ClrSysMmbId");
			    FinInstnId2.appendChild(ClrSysMmbId2);
			    
			    Element MmbId3 = doc.createElement("MmbId");
			    MmbId3.appendChild(doc.createTextNode(customer.getDebtor_identification_no()));
			    ClrSysMmbId2.appendChild(MmbId3);
			    
			    Element Nm7 = doc.createElement("Nm");
			    Nm7.appendChild(doc.createTextNode(customer.getDebtor_bank_name()));
			    System.out.println("Bankname will be -->"+customer.getDebtor_bank_name());
			    FinInstnId2.appendChild(Nm7);
			    
			    Element OrgnlMndt = doc.createElement("OrgnlMndt");
			    UndrlygAmdmntDtls.appendChild(OrgnlMndt);
			    
			    Element OrgnlMndtId = doc.createElement("OrgnlMndtId");
			    OrgnlMndtId.appendChild(doc.createTextNode(customer.getUmrn()));
			    OrgnlMndt.appendChild(OrgnlMndtId);
			    
			    TransformerFactory transformerFactory = TransformerFactory.newInstance();
			    Transformer transformer = transformerFactory.newTransformer();
			    DOMSource source = new DOMSource(doc);
			    
			    StreamResult result = new StreamResult(new File(filepath));
			    
			    transformer.transform(source, result);
			    
			    StreamResult result1 = new StreamResult(System.out);
			    transformer.transform(source, result1);
			    
			    System.out.println("XML FILE SAVED SUCCESSFULLY...");
			  System.out.println("Zipfile path -->"+zippath);
			  if( (new MandateZIP()).creatZIP(customer, jpg_path,tiff_path, filepath, bankname,shortcode,bankloginid,zippath,seqno))
			  {
				  flag = true;
			  }
			   	
		    }
		    catch(Exception e) 
		    {
		    	flag = false;
		    	e.printStackTrace();
		    }
		
		System.out.println("Flag value will be -->"+flag);
		return flag;
		
	}
}
