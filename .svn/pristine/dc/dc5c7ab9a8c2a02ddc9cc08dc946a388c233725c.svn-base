package com.example.demo.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.Attr;

import com.example.demo.Dao.BankDetailsNachRepository;
import com.example.demo.Service.AmendServiceImpl;
import com.example.demo.entity.BankDetailsNach;
import com.example.demo.entity.MdtInitRequestH2h;



public class XML_Cancel {
	@Autowired
	BankDetailsNachRepository repo;
	
	BankDetailsNach cust = new BankDetailsNach();
	
	private final Logger logger = Logger.getLogger(XML_Cancel.class);
	
	public boolean cancelMandateInputXML(MdtInitRequestH2h customer, String bankuser,String Shortcode,String Loginid,String destpath,
			String zip_path,String varchar_seq_num ) {
		
		boolean flag = false;
		logger.info("Creating XML File...");
		
		try 
		{
			
			
			Date now1 = new Date();
			SimpleDateFormat timeFormatter = new SimpleDateFormat("ddMMyyyy");
			String Currentdate = timeFormatter.format(now1);
			
			try 
			{
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				
				logger.info("Reason -->"+customer.getReason_code());
				logger.info("Destination path -->"+destpath);
				
				customer.setSeq_no(Integer.parseInt(varchar_seq_num));

				String filepath = destpath.trim()+ customer.getUniqueId()+"/CANCEL"+"/"+ "MMS-CANCEL-"+Shortcode+"-"+Loginid
				+"-"+Currentdate+"-"+varchar_seq_num+"-INP"+".xml";
				
				File files = new File(destpath+customer.getUniqueId()+"/CANCEL");
				
				 if (!files.exists()) 
				 {
		             logger.info("creating directory:"+destpath+customer.getUniqueId()+"/CANCEL");
		             boolean result = false;

		             try
		             {
		             	files.mkdirs();
		                 result = true;
		             } 
		             catch(SecurityException se)
		             {
		                logger.error("<-- Exception occured while creating file -->");
		             }        
		             if(result) {    
		                 logger.info("DIR created");  
		             }
		         }
				 
				 File uploadedFile = new File(filepath);
				 
				 Document doc =  docBuilder.newDocument();
				 
				 doc.setXmlStandalone(true);
				 Element rootElement = doc.createElement("Document");
				 doc.appendChild(rootElement);
				 Attr att = doc.createAttribute("xmlns");
				 att.setValue("urn:iso:std:iso:20022:tech:xsd:pain.011.001.01");
				 rootElement.setAttributeNode(att);
				 
				 Element mndcxl = doc.createElement("MndtCxlReq");
				 rootElement.appendChild(mndcxl);
				 
				 Element group = doc.createElement("GrpHdr");
				 mndcxl.appendChild(group);
				 
				 Element msgid = doc.createElement("MsgId");
				 msgid.appendChild(doc.createTextNode(customer.getUniqueId()));
				 group.appendChild(msgid);
				 
				 Element crdt = doc.createElement("CreDtTm");
				 SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
				 Date now = new Date();
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
				 
				 Element Mndt = doc.createElement("UndrlygCxlDtls");
				 mndcxl.appendChild(Mndt);
						
				 Element CxlRsn = doc.createElement("CxlRsn");
				 Mndt.appendChild(CxlRsn);
				 
				 if(customer.getReason_code() != null) {
					 Element Rsn = doc.createElement("Rsn");
					 CxlRsn.appendChild(Rsn);
					 Element Prtry = doc.createElement("Prtry");
					 Prtry.appendChild(doc.createTextNode(customer.getReason_code()));
					 Rsn.appendChild(Prtry);
					 
				 }
				 Element OrgnlMndt = doc.createElement("OrgnlMndt");
				 Mndt.appendChild(OrgnlMndt);
						
				 Element OrgnlMndtId = doc.createElement("OrgnlMndtId");
				 OrgnlMndtId.appendChild(doc.createTextNode(customer.getUmrn()));
				 OrgnlMndt.appendChild(OrgnlMndtId);
				 
				 //Write the content in xml file
				 TransformerFactory transformerFactory = TransformerFactory.newInstance();
				 Transformer transformer = transformerFactory.newTransformer();
				 DOMSource source = new DOMSource(doc);
				 StreamResult result = new StreamResult(new File(filepath));
									 
				 transformer.transform(source, result);
				 logger.error("XML file saved");
				 
				if( new CancelMandateZIP().creatZIP(customer, filepath,bankuser,zip_path,Shortcode,Loginid,varchar_seq_num))
				{
					flag = true;
				}
				 
				
			}
			catch(Exception e) 
			{
				flag = false;
				logger.error("Exception e"+e);
			}
			
			
			
		}
		catch(Exception e) 
		{
			flag = false;
			logger.error("Exception occured while cancel"+e);
		}
		
		
		return flag;
		
	}

}
