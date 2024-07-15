package com.example.demo.utilities;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.example.demo.entity.MdtInitRequestH2h;
import com.example.demo.entity.User;


public class WriteXMLFile 
{
	boolean flag = false;
	
	HttpSession session;
	
	private final Logger logger = Logger.getLogger(WriteXMLFile.class);
	public boolean CreateMandateXML(HttpSession session,MdtInitRequestH2h customer,String tiff_path,String jpg_path,String bankloginid,
			String shortcode,String destpath,String zipfilepath,String seq_no) 
	{
		logger.info("Creating XML File");
		logger.info("Values inside the array are -->"+customer);
		try 
		{
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			User attribute = (User) session.getAttribute("user");
			String bankname = attribute.getBankname();
			String Validity_Flag = attribute.getValidity_flag();
			logger.info("Value of validity flag will be -->"+Validity_Flag);
			logger.info(attribute.getBankname());
			
			Date now1 = new Date(); 
	   		SimpleDateFormat timeFormatter = new SimpleDateFormat("ddMMyyyy");
	   		String Currentdate = timeFormatter.format(now1); 
	   		
	   		try 
	   		{
				
	   			String filePath =  destpath.trim()+customer.getUniqueId()+"/CREATE/"+ "MMS-CREATE-"+shortcode+"-"+bankloginid+"-"+
	   			Currentdate+"-"+seq_no+"-INP"+".xml";
	   			logger.info("XML FILE PATH -->"+filePath);
	   			
	   			Path directory = Paths.get(destpath.trim() +customer.getUniqueId()+ "/CREATE/");
				if (!Files.exists(directory)) {
		                Files.createDirectories(directory);
		        }
	   			
	   			
	   			Document doc = docBuilder.newDocument();
	   			doc.setXmlStandalone(true);
	   			Element rootElement = doc.createElement("Document");
	   			doc.appendChild(rootElement);
	   			Attr att = doc.createAttribute("xmlns");
	   			att.setValue("urn:iso:std:iso:20022:tech:xsd:pain.009.001.01");
	   			rootElement.setAttributeNode(att);
	   			
	   			Element mndini = doc.createElement("MndtInitnReq");
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
			    logger.info("today"+today);
			    
			    String strDate1 = sd.format(now);
			    String strDate = new StringBuffer(strDate1).insert(10, "T").toString();
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
				
				Element Mndt = doc.createElement("Mndt");
				mndini.appendChild(Mndt);
				
				Element MndtReqId = doc.createElement("MndtReqId");
				MndtReqId.appendChild(doc.createTextNode(customer.getUniqueId()));
				Mndt.appendChild(MndtReqId);
				
				Element Tp = doc.createElement("Tp");
				Mndt.appendChild(Tp);
				
				Element SvcLvl = doc.createElement("SvcLvl");
				Tp.appendChild(SvcLvl);
				
				Element Prtry = doc.createElement("Prtry");
				Prtry.appendChild(doc.createTextNode(customer.getCategory_code()));
				SvcLvl.appendChild(Prtry);
				
				Element LclInstrm = doc.createElement("LclInstrm");
				Tp.appendChild(LclInstrm);
				
				Element Prtry1 = doc.createElement("Prtry");
				Prtry1.appendChild(doc.createTextNode(customer.getTransaction_type()));
				LclInstrm.appendChild(Prtry1);
				
				Element Ocrncs = doc.createElement("Ocrncs");
				Mndt.appendChild(Ocrncs);
				
				Element SeqTp = doc.createElement("SeqTp");
				SeqTp.appendChild(doc.createTextNode(customer.getOccurence()));
				Ocrncs.appendChild(SeqTp);
				
				Element Frqcy = doc.createElement("Frqcy");
				Frqcy.appendChild(doc.createTextNode(customer.getFrequency()));
				Ocrncs.appendChild(Frqcy);
				
				if(Validity_Flag.equalsIgnoreCase("Y")) {
					
					Date Mdate = customer.getDate_of_mandate(); 
					logger.info("Mdate:"+Mdate);
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String textMdate = df.format(Mdate);
					
					Element Drtn = doc.createElement("Drtn");
				    Ocrncs.appendChild(Drtn);
				    
				    Element FrDt=doc.createElement("FrDt");
					Drtn.appendChild(FrDt);
					FrDt.appendChild(doc.createTextNode(textMdate));
					
				}
				
				Date FrstColltnDt1 = customer.getFirst_collection_date(); 
				logger.info("FrstColltnDt1:"+FrstColltnDt1);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String text = df.format(FrstColltnDt1);
				
				Element FrstColltnDt = doc.createElement("FrstColltnDt");
				FrstColltnDt.appendChild(doc.createTextNode(text));
				Ocrncs.appendChild(FrstColltnDt);
				
				Date FnlColltnDt1 = customer.getFinal_collection_date(); 
				String until_cancel = customer.getUntil_cancel();
				
				if(!until_cancel.equalsIgnoreCase("Y")) 
				{
					String text2 = df.format(FnlColltnDt1);				
					Element FnlColltnDt = doc.createElement("FnlColltnDt");
					FnlColltnDt.appendChild(doc.createTextNode(text2));
					Ocrncs.appendChild(FnlColltnDt);
				}
				
				
				if(customer.getAmount_type() != null && customer.getAmount_type().equalsIgnoreCase("Fixed"))
				{
					Element ColltnAmt = doc.createElement("ColltnAmt");
					Attr att12 = doc.createAttribute("Ccy");
					att12.setValue("INR");
					ColltnAmt.setAttributeNode(att12);
					ColltnAmt.appendChild(doc.createTextNode(customer.getAmount()));
					Mndt.appendChild(ColltnAmt);
				}
				else if(customer.getAmount_type() != null && customer.getAmount_type().equalsIgnoreCase("Maximum"))
				{				
				
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
				Cdtr.appendChild(Nm1);
				
				Element CdtrAcct = doc.createElement("CdtrAcct");
				Mndt.appendChild(CdtrAcct);
				
				Element id2 = doc.createElement("Id");
				CdtrAcct.appendChild(id2);
				
				Element Othr1 = doc.createElement("Othr");
				id2.appendChild(Othr1);
				
				Element id3 = doc.createElement("Id");
				id3.appendChild(doc.createTextNode(customer.getCreditor_utility_code()));
				Othr1.appendChild(id3);
				
				Element CdtrAgt = doc.createElement("CdtrAgt");
				Mndt.appendChild(CdtrAgt);
				
				Element FinInstnId12 = doc.createElement("FinInstnId");
				CdtrAgt.appendChild(FinInstnId12);
				
				Element ClrSysMmbId12 = doc.createElement("ClrSysMmbId");
				FinInstnId12.appendChild(ClrSysMmbId12);
				
				Element MmbId12 = doc.createElement("MmbId");
				MmbId12.appendChild(doc.createTextNode(customer.getCreditor_identification_no()));
				ClrSysMmbId12.appendChild(MmbId12);
				
				Element Nm32 = doc.createElement("Nm");
				Nm32.appendChild(doc.createTextNode(customer.getBank_name()));
				FinInstnId12.appendChild(Nm32);
				
				Element Dbtr = doc.createElement("Dbtr");
				Mndt.appendChild(Dbtr);
				
				Element Nm3 = doc.createElement("Nm");
				Nm3.appendChild(doc.createTextNode(customer.getDebtor_name()));
				Dbtr.appendChild(Nm3);
				
				Element CtcDtls = doc.createElement("CtctDtls");
				Dbtr.appendChild(CtcDtls);
				
				Element Nm4 = doc.createElement("PhneNb");
				Nm4.appendChild(doc.createTextNode("+91-001-22001000"));
				CtcDtls.appendChild(Nm4);
				
				if(customer.getDeb_mobno()!=null && !(customer.getDeb_mobno().equals("")))
				{
					Element Nm5 = doc.createElement("MobNb");
					Nm5.appendChild(doc.createTextNode("+91-"+customer.getDeb_mobno()));
					CtcDtls.appendChild(Nm5);
				}
				else
				{
					Element Nm5 = doc.createElement("MobNb");
					Nm5.appendChild(doc.createTextNode("+91-9000000000"));
					CtcDtls.appendChild(Nm5);
				}
				if (customer.getDeb_emailid()!=null && !(customer.getDeb_emailid().equals("")))
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
				Othr2.appendChild(Id2);
				
				Element Tp1 = doc.createElement("Tp");
				DbtrAcct.appendChild(Tp1);
				
				Element Prtry2 = doc.createElement("Prtry");
				Prtry2.appendChild(doc.createTextNode(customer.getDebtor_account_type()));
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
				FinInstnId2.appendChild(Nm7);
				
				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(filePath));
				transformer.transform(source, result);
				
				// Output to console for testing
				 StreamResult result1 = new StreamResult(System.out);
				 
				 transformer.transform(source, result1);
				 logger.info("XML file saved!");
				 
				 //Creating ZIP File of XML and images
				if( (new MandateZIP()).creatZIP(customer, jpg_path, tiff_path, filePath,bankname,shortcode,bankloginid,zipfilepath,seq_no))
				{
					 //Set flag to true if successful
					 flag = true;
				}
				else
				{
					flag = false;
				}
				 
				
		
	   			
	   			
	   		}
	   		catch(Exception e) 
	   		{
	   			flag = false;
	   			e.printStackTrace();
	   		}
			
			
		}
		catch(Exception e) 
		{
			flag = false;
			e.printStackTrace();
		}
		logger.info("Flag Val for create mandate -->"+flag);
		return flag;
		
	}

}
