package com.example.demo.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Dao.BankDetailsNachRepository;
import com.example.demo.Dao.BanksConfigurationRepository;
import com.example.demo.Dao.CatCodeListRepository;
import com.example.demo.Dao.MandateAmendRepository;
import com.example.demo.Dao.MdtInitRequestH2hRepository;
import com.example.demo.Dao.SequenceCounterRepository;
import com.example.demo.entity.BankDetailsNach;
import com.example.demo.entity.CatCodeList;
import com.example.demo.entity.MdtInitRequestH2h;
import com.example.demo.entity.User;
import com.example.demo.entity.mdt_amend_request;
import com.example.demo.utilities.DateMaster;
import com.example.demo.utilities.PathsMaster;
import com.example.demo.utilities.WriteAmendXml;
import com.example.demo.utilities.sendMandateSms;

@Service
public class AmendServiceImpl implements  AmendService {
	
	@Autowired
	private CatCodeListRepository codeList;
	
	@Autowired
	MdtInitRequestH2hRepository Dao;
	
	@Autowired
	BankDetailsNachRepository banksdao;
	
	BankDetailsNach details;
	
	@Autowired
	SequenceCounterRepository seqrepo;
	
	@Value("${app.MANDTINPFILES}") 
	String inpfilename;
	
	@Value("${app.INP_MDT_SIGNED_TO_NPCI}")
	String zipfilename;
	
	@Autowired
	BanksConfigurationRepository configRepo;
	
	@Autowired
	MandateAmendRepository amenddao;
	
	private final Logger logger = Logger.getLogger(AmendServiceImpl.class);
	
	private static final String AES_KEY = "secretKey12345678";
	
	
	public void search_data(MdtInitRequestH2h customer,String unique_id,Model model) 
	{
		//fetch data for the below unique_id
				customer = Dao.findByUniqueId(unique_id);
				logger.info("Search Amend -->"+customer);
				
				if(customer != null) 
				{
					List<CatCodeList> catCodeList = codeList();
					if(catCodeList.size() != 0) 
					{
						model.addAttribute("cat_code", catCodeList);
					}
					else
					{
						logger.info("No data present in catcode list");
					}
					
					model.addAttribute("customer", customer);
					model.addAttribute("selected_rsn", customer.getReason_code());
					
					model.addAttribute("selected_creditor", customer.getCreditor_account_no_type());
					model.addAttribute("sele_creditor", customer.getTransaction_type());

					
					model.addAttribute("selected_catcode", customer.getCategory_code());
					
				}
				else 
				{
					model.addAttribute("error","Cannot fetch data please try searching again");
					logger.info("No data avaliable for the unique_id");
				}
				
				
	}
	
	//decrypting the unique_id
	public String decryptParam(String encryptedParam) 
	{
		logger.info("Encrypted param would be -->"+encryptedParam);
		 try 
		 {
	            SecretKeySpec secretKey = new SecretKeySpec(AES_KEY.getBytes(), "AES");
	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	            cipher.init(Cipher.DECRYPT_MODE, secretKey);
	            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedParam));
	            return new String(decryptedBytes);
	     } 
		 catch (Exception e) 
		 {
	            e.printStackTrace();
	            return "";
	     }
    }

	
	public void getData(HttpSession session,String value,String data,Model model) 
	{
		
		
		 User attribute = (User) session.getAttribute("user");
		 String bank_name = attribute.getUsername();
		
		List<String> msgId = new ArrayList<String>();

		msgId = getUniqueIddata(value, data, bank_name);
		logger.info("Unique id -->"+msgId);
		if (msgId.isEmpty()) 
		{
			logger.info("No data found for the unique_id");
			model.addAttribute("messageId", "No Mandate Found");
			model.addAttribute("data", msgId);

		} 
		else 
		{
			model.addAttribute("messageId",String.valueOf(msgId.size()) + " Mandate found with below Unique ID  for Amend \n\n");
			model.addAttribute("data", msgId);

		}
		
	}
	
	//Amend Mandate Method
	
	public void AmendMandate(HttpSession session,String unique_id,Model model,MultipartFile jpg_image,MultipartFile tiff_image,
			 MdtInitRequestH2h customer,String umrn) throws IOException, ParserConfigurationException, URISyntaxException 
	{
		try
		{

			
			String jpg_path  = "";
			String tiff_path = "";
			int seq = 0;
			String varchar_seq_num = "";
			logger.info("Data after amend : "+customer);
		 	User attribute = (User) session.getAttribute("user");
			String bank_name = attribute.getUsername();
			String validity_flag = attribute.getValidity_flag();
			String sms_flag = attribute.getSms_flag();
			String smsbankusername = attribute.getSmsbankusername();
			String smsbankuserid = attribute.getSmsbankuserid();
			String smsbankpassword = attribute.getSmsbankpassword();
			PathsMaster pm = new  PathsMaster(configRepo);
			String destPath = pm.getMndtInpFiles(bank_name, inpfilename);
			logger.info("Destination path for amend xml -->"+destPath);
			
			String zip_path = pm.getInpMdtSignedToNPCI(bank_name,zipfilename);
			logger.info("Zip file path would be -->"+zip_path);
			
			
			if(customer != null) 
			{
				details = banksdao.findByBankname(bank_name);
				
				if(details != null)
				{
					//Get details that would be null
					customer.setUniqueId(unique_id);
					customer.setCreditor_utility_code(details.getCreditor_utility_code());
					customer.setCreditor_identification(details.getCreditor_identification());
					customer.setCreditor_identification_no(details.getCreditor_identification_no());
					customer.setBank_name(bank_name);
					customer.setMdt_request("AMEND");
					customer.setUmrn(umrn);
					//Converting Images to bytes
					byte[] jpg_bytes = jpg_image.getBytes();
					byte[] tif_bytes = tiff_image.getBytes();
					
					//Store the images in bytes 
					customer.setJpg_bytes(jpg_bytes);
					customer.setTif_bytes(tif_bytes);
					//Get details that would be null
					
					logger.info("Details will be -->"+details);
					String bankloginid = details.getBankloginid();
					String shortcode = details.getShortcode();
					
					//Adding sequence number to the database
					seq = seqrepo.getseqcount(bank_name,DateMaster.getDate2());
					logger.info("Sequence num-->"+seq);
					seq++;
					int rows = seqrepo.insertseq(seq,bank_name,"AMEND",DateMaster.getDate2());
					if(rows > 0)
					{
						logger.info("Sequence inserted");
					}
					else
					{
						logger.info("Failed to insert sequence");
					}
					logger.info("<--- Seq number -->"+seq);
					
					if(bank_name != null) 
					{
						varchar_seq_num = String.valueOf(seq);
						int len = varchar_seq_num.length();
						for (int j = 0; j < 6 - len; j++) 
						{
							
							varchar_seq_num = "0".concat(varchar_seq_num);
							logger.info("Sequence Number -->"+varchar_seq_num);
						}
						
					}
					
					if(!jpg_image.isEmpty()) 
					{
				 		 jpg_path = processFile(jpg_image, "jpg_image", unique_id, bank_name,varchar_seq_num,customer);
				 		 logger.info("JPEG image path would be -->"+jpg_path);
				 		
				 	}
				 	if(!tiff_image.isEmpty())
				 	{
				 		 tiff_path = processFile(tiff_image, "tiff_image",unique_id, bank_name,varchar_seq_num,customer);
				 		 logger.info("JPEG image path would be -->"+tiff_path);
				 	}
				 	
				 	if(customer.getUntil_cancel()!= null && !customer.getUntil_cancel().equals("")) 
			 		{
			 			customer.setUntil_cancel("Y");
			 		   // Get today's date in dd-mm-yy format
			 			Date todayDate = new Date();
						java.sql.Date date = new java.sql.Date(todayDate.getTime()); 
			 	        customer.setFinal_collection_date(date);
						logger.info("Final collection:"+date);
						
			 	        
			 		}
				 	else
			 		{
			 			customer.setUntil_cancel("N");
			 		}
				 	logger.info("Creating XML");
				 	//Creating XML file
				 	if(new WriteAmendXml().createMnadateInputXML(customer,bank_name,validity_flag,bankloginid,shortcode,tiff_path,jpg_path,destPath,varchar_seq_num,zip_path))
				 	{
				 		logger.info("Xml creation successful...");
				 		if(jpg_path != null && tiff_path != null ) 
				 		{
				 			if(AmendData(unique_id))
				 			{
				 				if(updatedata(customer,unique_id)) 
						 		{
				 					if(sms_flag.equalsIgnoreCase("Y")) 
						 			{
				 						logger.info("Sending sms");
				 						if(new sendMandateSms().sendSMS(customer,"AMEND",smsbankusername, smsbankuserid, smsbankpassword)) 
						 				{
				 							customer = Dao.findByUniqueId(unique_id);
						 					customer.setIs_sms_sent("Y");
						 					if(Dao.save(customer) != null) 
						 					{
						 						logger.info("Message sent successfully");
						 					}
						 					else
						 					{
						 						logger.info("Unable to add details in database");
						 					}
						 				}
				 						else
				 						{
				 							customer = Dao.findByUniqueId(unique_id);
						 					customer.setIs_sms_sent("NS");
						 					if(Dao.save(customer) != null)
						 						logger.info("Message not sent");
				 						}
						 			}
							 		model.addAttribute("Unique_id","Mandate Updated Successfully having Uniqueid:"+unique_id);
							 	}
					 			else
					 			{
					 				model.addAttribute("Unique_id","Exception occured please contact System Administrator");
					 			}
				 				
				 			}
				 			else 
				 			{
				 				logger.info("Unable to insert previous data");
				 			}
				 		}
				 		else
				 		{
				 			logger.info("<---------Image path is null------>");
				 			model.addAttribute("Unique_id","Exception occured please contact System Administrator");
				 		}
				 	}
				 	else
				 	{
				 		logger.info("<------------Error creating xml file--------->");
				 		model.addAttribute("Unique_id","Exception occured please contact System Administrator");
				 	}
				}
				else
				{
					logger.info("Cannot fetch bank details");
					model.addAttribute("Unique_id","Exception occured please contact System Administrator");
				}
				
				
				
			}
			else 
			{
				model.addAttribute("Unique_id","Exception occured please contact System Administrator");
			}
			
			
		
			
		}
		catch(Exception e)
		{
			logger.error("Error: "+e);
			e.printStackTrace();
		}
	}
	
	//Amend Mandate Method
	
	public List<String> getUniqueIddata(String data, String value,String bankName) {
		
		
		List<String> id = null;
		if(data.equalsIgnoreCase("DEBTOR_ACCOUNT_NO")) {
			id = Dao.getuniqueidbydebtor_account_no(value, bankName);
			logger.info("Inside debtor account number query..."+id);
			return id;
			
		}
		else if(data.equalsIgnoreCase("UMRN")) {
			id = Dao.getuniqueidbyumrn(value, bankName);
			logger.info("Inside umrn query..."+id);
			return id;
			
		}
		else  {
			id = Dao.getuniqueid(value, bankName);
			logger.info("Inside unique id query...."+id);
			return id;
		
		}
			
	}
	
	public boolean AmendData(String unique_id) {
		logger.info("Entering to amend data");
		boolean flag = false;
		MdtInitRequestH2h prev_data = new MdtInitRequestH2h();
		prev_data = Dao.findByUniqueId(unique_id);
		logger.info("Previous data -->"+prev_data);
		if(insert_prev_data(prev_data,unique_id)) 
		{
			flag = true;
		}
		return flag;
		
	}
	
	public boolean insert_prev_data(MdtInitRequestH2h prev_data,String unique_id) {
		boolean flag = false;
		
		mdt_amend_request prev_update = new mdt_amend_request();
		
		
		String request = prev_data.getMdt_request();
		logger.info("Request would be -->"+request);
		//If the request is create then it will insert the data
		if(request.equalsIgnoreCase("CREATE")) 
		{
			prev_update.setUniqueId(unique_id);
			prev_update.setTRANSACTION_TYPE_PREV(prev_data.getTransaction_type());
			logger.info("Transaction type of previous data -->"+prev_data.getTransaction_type());
			prev_update.setOCCURENCE_PREV(prev_data.getOccurence());
			prev_update.setFREQUENCY_PREV(prev_data.getFrequency());
			prev_update.setFIRST_COLLECTION_PREV(prev_data.getFirst_collection_date());
			prev_update.setFINAL_COLLECTION_PREV(prev_data.getFinal_collection_date());
			prev_update.setAMOUNT_TYPE_PREV(prev_data.getAmount_type());
			prev_update.setAMOUNT_PREV(prev_data.getAmount());
			prev_update.setDEBTOR_NAME_PREV(prev_data.getDebtor_name());
			prev_update.setDEBTOR_ACCOUNT_NO_PREV(prev_data.getDebtor_account_no());
			prev_update.setDEBTOR_ACCOUNT_TYPE_PREV(prev_data.getDebtor_account_type());
			prev_update.setDEBTOR_BANK_NAME_PREV(prev_data.getDebtor_bank_name());
			prev_update.setDEBTOR_IDENTIFICATION_PREV(prev_data.getDebtor_identification());
			prev_update.setDEBTOR_IDENT_NO_PREV(prev_data.getDebtor_identification_no());
			prev_update.setCREDITOR_NAME_PREV(prev_data.getCreditor_name());
			prev_update.setCREDITOR_UTILITY_CODE_PREV(prev_data.getCreditor_utility_code());
			prev_update.setCREDITOR_IDENTIFICATION_PREV(prev_data.getCreditor_identification());
			prev_update.setCREDITOR_IDENT_NO_PREV(prev_data.getCreditor_identification_no());
			prev_update.setCREATION_DATE_PREV(prev_data.getCreation_date());
			prev_update.setLAST_MODIFIED_PREV(prev_data.getLast_modified_date());
			prev_update.setCATEGORY_CODE_PREV(prev_data.getCategory_code());
			prev_update.setCREDITOR_ACCOUNT_NO_PREV(prev_data.getCreditor_account_no());
			prev_update.setCREDITOR_ACCOUNT_NO_TYPE_PREV(prev_data.getCreditor_account_no_type());
			prev_update.setUNTIL_CANCEL_PREV(prev_data.getUntil_cancel());
			prev_update.setDEB_EMAILID_PREV(prev_data.getDeb_emailid());
			prev_update.setDEB_MOBNO_PREV(prev_data.getDeb_mobno());
			prev_update.setDATE_OF_MANDATE_PREV(prev_data.getDate_of_mandate());
			prev_update.setREASON_CODE_PREV(prev_data.getReason_code());
			prev_update.setJPG_IMAGE_PREV(prev_data.getJpg_bytes());
			prev_update.setTIFF_IMAGE_PREV(prev_data.getTif_bytes());
			
			
			if(amenddao.save(prev_update) != null) 
			{
				flag = true;
			}
			else
			{
				logger.info("Cannot insert data in table");
			}
			
			
			
		}
		//If the request is amend then it will update the table
		else 
		{
			logger.info("Date in database:"+prev_data.getLast_modified_date());
			prev_update = amenddao.findByUniqueId(unique_id);
			logger.info("Data in mdt_amend_request table -->"+prev_update);
			prev_update.setTRANSACTION_TYPE_PREV(prev_data.getTransaction_type());
			logger.info("Transaction type of previous data -->"+prev_data.getTransaction_type());
			prev_update.setOCCURENCE_PREV(prev_data.getOccurence());
			prev_update.setFREQUENCY_PREV(prev_data.getFrequency());
			prev_update.setFIRST_COLLECTION_PREV(prev_data.getFirst_collection_date());
			prev_update.setFINAL_COLLECTION_PREV(prev_data.getFinal_collection_date());
			prev_update.setAMOUNT_TYPE_PREV(prev_data.getAmount_type());
			prev_update.setAMOUNT_PREV(prev_data.getAmount());
			prev_update.setDEBTOR_NAME_PREV(prev_data.getDebtor_name());
			prev_update.setDEBTOR_ACCOUNT_NO_PREV(prev_data.getDebtor_account_no());
			prev_update.setDEBTOR_ACCOUNT_TYPE_PREV(prev_data.getDebtor_account_type());
			prev_update.setDEBTOR_BANK_NAME_PREV(prev_data.getDebtor_bank_name());
			prev_update.setDEBTOR_IDENTIFICATION_PREV(prev_data.getDebtor_identification());
			prev_update.setDEBTOR_IDENT_NO_PREV(prev_data.getDebtor_identification_no());
			prev_update.setCREDITOR_NAME_PREV(prev_data.getCreditor_name());
			prev_update.setCREDITOR_UTILITY_CODE_PREV(prev_data.getCreditor_utility_code());
			prev_update.setCREDITOR_IDENTIFICATION_PREV(prev_data.getCreditor_identification());
			prev_update.setCREDITOR_IDENT_NO_PREV(prev_data.getCreditor_identification_no());
			prev_update.setCREATION_DATE_PREV(prev_data.getCreation_date());
			logger.info("Creation_date -->"+prev_data.getCreation_date());
			prev_update.setLAST_MODIFIED_PREV(prev_data.getLast_modified_date());
			logger.info("Last modified date -->"+prev_data.getLast_modified_date());
			prev_update.setCATEGORY_CODE_PREV(prev_data.getCategory_code());
			prev_update.setCREDITOR_ACCOUNT_NO_PREV(prev_data.getCreditor_account_no());
			prev_update.setCREDITOR_ACCOUNT_NO_TYPE_PREV(prev_data.getCreditor_account_no_type());
			prev_update.setUNTIL_CANCEL_PREV(prev_data.getUntil_cancel());
			prev_update.setDEB_EMAILID_PREV(prev_data.getDeb_emailid());
			prev_update.setDEB_MOBNO_PREV(prev_data.getDeb_mobno());
			prev_update.setDATE_OF_MANDATE_PREV(prev_data.getDate_of_mandate());
			prev_update.setREASON_CODE_PREV(prev_data.getReason_code());
			prev_update.setJPG_IMAGE_PREV(prev_data.getJpg_bytes());
			prev_update.setTIFF_IMAGE_PREV(prev_data.getTif_bytes());
			if(amenddao.save(prev_update) != null) 
			{
				flag = true;
			}
			else {
				logger.info("Cannot insert data in table");
			}
			
		}
			
		logger.info("Insert status -->"+flag);
		return flag;
		
	}
	
	public boolean updatedata(MdtInitRequestH2h customer,String unique_id) 
	{
		boolean flag = false;
		try
		{

			
			boolean flag1 = false;
			boolean flag2 = false;
			
			  java.util.Date now = new java.util.Date();
		      logger.info("Today's -->"+now);
		      Date date1 = new Date(now.getTime());
		      logger.info("Date before con -->"+date1);
		      java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
		      logger.info("sqlDate1" + sqlDate1);
			
			//MdtInitRequestH2h data = Dao.findByUniqueId(unique_id);
			mdt_amend_request data = amenddao.findByUniqueId(unique_id);
			logger.info("New Data would be -->"+data);
			
			if(data != null) 
			{
				
				data.setTRANSACTION_TYPE(customer.getTransaction_type());
				data.setOCCURENCE(customer.getOccurence());
				data.setFREQUENCY(customer.getFrequency());
				data.setFIRST_COLLECTION_DATE(customer.getFirst_collection_date());
				data.setFINAL_COLLECTION_DATE(customer.getFinal_collection_date());
				data.setAMOUNT_TYPE(customer.getAmount_type());
				data.setAMOUNT(customer.getAmount());
				data.setCREDITOR_NAME(customer.getCreditor_name());
				data.setCREDITOR_UTILITY_CODE(customer.getCreditor_utility_code());
				data.setCREDITOR_IDENTIFICATION(customer.getCreditor_identification());
				data.setCREDITOR_IDENTIFICATION_NO(customer.getCreditor_identification_no());
				data.setDEBTOR_NAME(customer.getDebtor_name());
				data.setDEBTOR_ACCOUNT_NO(customer.getDebtor_account_no());
				data.setDEBTOR_BANK_NAME(customer.getDebtor_bank_name());
				data.setDEBTOR_IDENTIFICATION(customer.getDebtor_identification());
				data.setDEBTOR_ACCOUNT_TYPE(customer.getDebtor_account_type());
				data.setUMRN(customer.getUmrn());
				data.setDEBTOR_IDENTIFICATION_NO(customer.getDebtor_identification_no());
				data.setMDT_REQUEST(customer.getMdt_request());
				data.setBANK_NAME(customer.getBank_name());
				data.setREASON_CODE(customer.getReason_code());
				data.setCATEGORY_CODE(customer.getCategory_code());
				data.setCREDITOR_ACCOUNT_NO(customer.getCreditor_account_no());
				data.setCREDITOR_ACCOUNT_NO_TYPE(customer.getCreditor_account_no_type());
				data.setUNTIL_CANCEL(customer.getUntil_cancel());
				data.setDEB_EMAILID(customer.getDeb_emailid());
				data.setDEB_MOBNO(customer.getDeb_mobno());
				data.setDATE_OF_MANDATE(customer.getDate_of_mandate());
				data.setJPG_IMAGE(customer.getJpg_bytes());
				data.setTIFF_IMAGE(customer.getTif_bytes());
				data.setLAST_MODIFIED_DATE(DateMaster.getDate4());
				
				
				logger.info("Data of mandate -->"+customer.getDate_of_mandate());
				if(amenddao.save(data) != null) 
				{
					flag1 = true;
				}
			}
			
			MdtInitRequestH2h initdata = Dao.findByUniqueId(unique_id);
			logger.info("Init data would be -->"+initdata);
			if(initdata != null) 
			{
				initdata.setMdt_request("AMEND");
				initdata.setBenificiary_status("");
				initdata.setReject_reason("");
				initdata.setStatus("");
				initdata.setLast_modified_date(sqlDate1);
				logger.info("Last modified date would be -->"+sqlDate1);
				
				if(Dao.save(initdata) != null) 
				{
					flag2 = true;
				}
			}
			
			if(flag1 == true && flag2 == true) 
			{
				flag = true;
			}
			
			
			
			/*
			 * if(data != null) { logger.info("Data to be updated using amend -->"+data);
			 * data.setCreditor_name(customer.getCreditor_name());
			 * data.setCreditor_account_no(customer.getCreditor_account_no());
			 * data.setFrequency(customer.getFrequency());
			 * data.setOccurence(customer.getOccurence());
			 * data.setAmount(customer.getAmount());
			 * data.setAmount_type(customer.getAmount_type()); data.setMdt_request("AMEND");
			 * data.setReason_code(customer.getReason_code()); data.setStatus("");
			 * data.setBenificiary_status(""); data.setReject_reason("");
			 * data.setFirst_collection_date(customer.getFirst_collection_date());
			 * data.setFinal_collection_date(customer.getFinal_collection_date());
			 * data.setDeb_emailid(customer.getDeb_emailid());
			 * data.setDate_of_mandate(customer.getDate_of_mandate());
			 * data.setDebtor_account_no(customer.getDebtor_account_no()); Dao.save(data);
			 * flag = true; } else { logger.info("Cannot update data...."); flag = false; }
			 */
			
			
			
			
		
			
		}
		catch(Exception e)
		{
			flag = false;
			logger.error("Exception occured while updating data in amend table");
		}
		logger.info("Flag value for update will be -->"+flag);
		return flag;
	}
	
	public String processFile(MultipartFile file, String fieldName,String UID,String bank_name,String seqno,MdtInitRequestH2h customer) throws IOException {
		
		PathsMaster pm = new PathsMaster(configRepo);
		String destPath = pm.getMndtInpFiles(bank_name,inpfilename);
		
		 String filePath = "";
		 String jpeg_name = "";
		 String tiff_name = "";
		 details = banksdao.findByBankname(bank_name);
		 
		 if (!file.isEmpty()) {
			
			 
			 String fileExtension = getFileExtension(file.getOriginalFilename());
			 logger.info("File extension will be -->"+fileExtension);
			 logger.info("file extension"+fileExtension);
			 
			 String CurrentDate = new SimpleDateFormat("ddMMyyyy").format(new Date());
			 
			if(fieldName.equalsIgnoreCase("jpg_image")) {
				
				filePath = String.valueOf(destPath) + UID + "/AMEND" + "/" + "MMS-AMEND-" + 
				details.getShortcode() + "-" + details.getBankloginid() + "-" + CurrentDate + "-" + seqno + "_detailfront" +
						"." + fileExtension.trim();
				jpeg_name = "MMS-AMEND-" + details.getShortcode() + "-" + details.getBankloginid() + "-" + CurrentDate + "-" + seqno + 
						"_detailfront" +"." + fileExtension.trim();
				
				
				
			}
			else 
			{
	
				filePath = String.valueOf(destPath) + UID + "/AMEND" + "/" + "MMS-AMEND-" + details.getShortcode()+
						"-" + details.getBankloginid()+ "-" + CurrentDate + "-" + seqno + "_front" + "." + fileExtension.trim();
				tiff_name = "MMS-AMEND-" + details.getShortcode()+"-" + details.getBankloginid()+ "-" + CurrentDate + "-" + seqno + 
						"_front" + "." + fileExtension.trim();
				
				
				
			}
			 
			 Path directory = Paths.get(destPath +UID+ "/AMEND/");
			 if (!Files.exists(directory)) {
	                Files.createDirectories(directory);
	          }
			 
			 byte[] bytes = file.getBytes();
             Path path = Paths.get(filePath);
             Files.write(path, bytes);
             logger.info("Amend Image File uploaded successfully to: " + filePath);
		 }
		return filePath;
		
	}
	
	private String getFileExtension(String fileName) {
        // Extract file extension from the provided file name
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
  }
	
	
	
	public List<CatCodeList> codeList() {
		// TODO Auto-generated method stub
		List<CatCodeList> catCodeList = codeList.findAll();
		return catCodeList;
	}
	
	public boolean createxml(HttpSession session,MdtInitRequestH2h customer,String bankloginid,String shortcode,String tiff_path,String jpg_path,String seq_no) 
	{
		//GET BANK_NAME
		User attribute = (User) session.getAttribute("user");
		String bank_name = attribute.getBankname();
		String Validity_flag = attribute.getValidity_flag();
		
		PathsMaster pm = new  PathsMaster(configRepo);
		String destPath = pm.getMndtInpFiles(bank_name, inpfilename);
		logger.info("Destination path for amend xml -->"+destPath);
		boolean flag = false;
		
		String zip_path = pm.getInpMdtSignedToNPCI(bank_name,zipfilename);
		logger.info("Zip file path would be -->"+zip_path);
		
		try {
			if(new WriteAmendXml().createMnadateInputXML(customer,bank_name,Validity_flag,bankloginid,shortcode,tiff_path,jpg_path,destPath,seq_no,zip_path)) 
			 {
				logger.info("Xml file created successfully -->"+flag);
				 flag = true;
			 }
			else
			{
				logger.info("Cannot craete");
				flag = false;
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
		
	}
	
	
    

}
