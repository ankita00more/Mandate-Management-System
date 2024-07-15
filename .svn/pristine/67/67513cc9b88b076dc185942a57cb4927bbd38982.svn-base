package com.example.demo.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.env.Environment;
import com.example.demo.Dao.BankDetailsNachRepository;
import com.example.demo.Dao.BanksConfigurationRepository;
import com.example.demo.Dao.CatCodeListRepository;
import com.example.demo.Dao.MdtInitRequestH2hRepository;
import com.example.demo.Dao.MdtValidityRepository;
import com.example.demo.entity.BankDetailsNach;
import com.example.demo.entity.CatCodeList;
import com.example.demo.entity.MdtInitRequestH2h;
import com.example.demo.entity.User;
import com.example.demo.utilities.DateMaster;
import com.example.demo.utilities.PathsMaster;
import com.example.demo.utilities.WriteXMLFile;
import com.example.demo.utilities.sendMandateSms;

@Service
public class MandateServiceImpl implements MandateService{

	@Autowired
	BankDetailsNachRepository banksdao;
	
	BankDetailsNach bank;
	
	@Autowired
	MdtInitRequestH2hRepository customerDao;
	
	@Autowired
	private CatCodeListRepository codeList;
	
	@Autowired
	BanksConfigurationRepository configRepo;
	
	String tiff_path = "";
	String jpg_path = "";
	
	@Autowired
	MdtValidityRepository validityrepo;
	
	@Value("${app.MANDTINPFILES}") 
	String filename;
	
	@Value("${app.INP_MDT_SIGNED_TO_NPCI}")
	String zipfilename;
	
	 @Autowired
	 private Environment env;

	 private static final Logger logger = Logger.getLogger(MandateServiceImpl.class);
	
	 
	 public List<CatCodeList> codeList() 
	 {
			// TODO Auto-generated method stub
			List<CatCodeList> catCodeList = codeList.findAll();
			return catCodeList;
	}
	public void createMandate(HttpSession session,MdtInitRequestH2h customer, MultipartFile jpg_image, MultipartFile tif_image,Model model)
	{
		User attribute = (User) session.getAttribute("user");
		String bankname = attribute.getBankname();
		logger.info("Bankname would be  -->"+bankname);
		String sms_flag = attribute.getSms_flag();
		String smsbankusername = attribute.getSmsbankusername();
		String smsbankuserid = attribute.getSmsbankuserid();
		String smsbankpassword = attribute.getSmsbankpassword();
		String utility_code = "";
		String deb_acc_no = "";
		Date date_of_mandate;
		String deb_bankname = "";
		String amount = "";
		String rsn_cnt = "";
		String reject_rsn1 = "";
		String  rjt1_desc = "";
	    String rjt2  = "";
	    String rjt2_desc = "";
	    int reason_cnt = 0;
	    String reasoncode = "";
	    int representationcnt = 0;
	    String formattedDom = null;
		
	    logger.info("Until Cancel:"+customer.getUntil_cancel());
		PathsMaster pm = new PathsMaster(configRepo);
		//XML and both image path
		String destPath = pm.getMndtInpFiles(bankname, filename);
		logger.info("Destination path would be -->"+destPath);
		//ZIP file path
		String zip_path = pm.getInpMdtSignedToNPCI(bankname,zipfilename);
		logger.info("Zip file path would be -->"+zip_path);
		//changes -->29/12/2023
		
		if (bankname != null) 
		{
			bank = banksdao.findByBankname(bankname);
			utility_code = bank.getCreditor_utility_code();
			logger.info("Utility code -->"+utility_code);
			deb_acc_no = customer.getDebtor_account_no();
			logger.info("Deb -->"+utility_code);
			date_of_mandate = customer.getDate_of_mandate();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			formattedDom = dateFormat.format(date_of_mandate);
			logger.info("Date of mandate -->"+formattedDom);
			deb_bankname = customer.getDebtor_bank_name();
			logger.info("Deb bankname -->"+deb_bankname);
			amount = customer.getAmount();
			logger.info("Amount will be -->"+amount);
		}
		else 
		{
			logger.info("Cannot fetch data for the bank");
		}
		
		
		
		//<----------------------------------- Check if the mandate was rejected ------------------------------->
		List<Object[]> distinctData = validityrepo.getrejrsn(deb_acc_no,formattedDom, utility_code,deb_bankname,amount);
		int listsize = distinctData.size();
		logger.info("List size will be -->"+listsize);
		
		//<---------------------- Retrieve values from list of objects ------------------------------------------>
		if(listsize != 0) 
		{
			for (Object[] row : distinctData) 
			 {
				 reject_rsn1 = (String) row[2];
				 logger.info("Reject Reason 1 -->"+reject_rsn1);
				 rjt1_desc = (String) row[3];
				 logger.info("Reject Reason 1 Desc -->"+rjt1_desc);
				 rjt2 = (String) row[4];
				 logger.info("Reject Reason 2  -->"+rjt2);
				 rjt2_desc = (String) row[5];
				 logger.info("Reject Reason 2 Desc  -->"+rjt2_desc);
				 rsn_cnt =  ((String) row[1]);
				 logger.info("Reason count will be -->"+rsn_cnt);
				 
			 }
			
		    reason_cnt = Integer.parseInt(rsn_cnt);
			//Get reject reason from property file
		    reasoncode = env.getProperty(reject_rsn1);
		    representationcnt = Integer.parseInt(reasoncode);
			
		}
		//<--------------------------------------------------Check------------------------------------------------------------->
		if( listsize != 0 && reason_cnt >= representationcnt) 
		{
				//if the reject reason count is equal to 1 then reject reason1 description will be printed
				if(reason_cnt == 1) {
					logger.info("Mandate cannot be created due to reason code -->"+rjt1_desc);
					model.addAttribute("unique_id","Mandate cannot be created due to Reason Code :"+rjt1_desc);
				}
				else {
					logger.info("Mandate cannot be created due to reason code -->"+rjt2_desc);
					model.addAttribute("unique_id","Mandate cannot be created due to reason code -->"+rjt2_desc);
				}
			
			
		}
		//<--------------------------------------------------- Save Mandate Data -------------------------------------------->
		else {
			try {
				
				int seq = 0;
				String varchar_seq_num = "";
				/* int seqnum = 0; */
				String shortcode = "";
				String bankloginid = "";
				String unique_id = "";
				
				//Adding sequence number to the database
				seq = customerDao.getSeqNo(bankname);
				logger.info("Sequence Number: "+seq);
				if(bankname != null) 
				{
					seq++;
					varchar_seq_num = String.valueOf(seq);
					int len = varchar_seq_num.length();
					for (int j = 0; j < 6 - len; j++) 
					{
						
						varchar_seq_num = "0".concat(varchar_seq_num);
						
					}
					logger.info("Sequence Number -->"+varchar_seq_num);
					
				}
				else 
				{
					model.addAttribute("Unique_id","Something went wrong please contact system administrator");
					logger.info("Unable to fetch sequence number");
				}
				
				
				
				if(bankname != null) 
				{
					//Get bank details to store in the database
					bank = banksdao.findByBankname(bankname);
					customer.setBank_name(bankname);
					customer.setCreditor_utility_code(bank.getCreditor_utility_code());
					logger.info("Creditor utility -->"+customer.getCreditor_utility_code());
					customer.setCreditor_identification(bank.getCreditor_identification());
					logger.info("Creditor Identification -->"+customer.getCreditor_identification());
					customer.setCreditor_identification_no(bank.getCreditor_identification_no());
					logger.info("Creditor Identification No -->"+customer.getCreditor_identification_no());
					customer.setUniqueId(customer.getDebtor_account_no() + DateMaster.getCurrentTimeStamp());
					customer.setSeq_no(Integer.parseInt(varchar_seq_num));
					customer.setCreation_date(DateMaster.getDate4());
					logger.info("Creation Date -->"+DateMaster.getDate4());
					customer.setLast_modified_date(DateMaster.getDate4());
					customer.setMdt_request("CREATE");
					
					bankloginid = bank.getBankloginid();
					shortcode = bank.getShortcode();
					
				}
				else 
				{
					model.addAttribute("Unique_id","Something went wrong please contact system administrator");
					logger.info("Cannot fetch data for the bank");
				}
				
				//Converting Images to bytes
				byte[] jpg_bytes = jpg_image.getBytes();
				byte[] tif_bytes = tif_image.getBytes();
				
				//Store the images in bytes 
				customer.setJpg_bytes(jpg_bytes);
				customer.setTif_bytes(tif_bytes);
				
				//Unique id 
				unique_id = customer.getUniqueId();
				model.addAttribute("Unique_id"+unique_id);
				logger.info("Unique_id -->"+unique_id);
				
				//Inserting Image file at the path
				if(!jpg_image.isEmpty()) 
				{
			 		 jpg_path = processFile(jpg_image, "jpg_image",unique_id, bankname,varchar_seq_num,destPath,customer);
			 		logger.info("JPG PATH -------->"+jpg_path);
			 	}
			 	if(!tif_image.isEmpty()) 
			 	{
			 		 tiff_path = processFile(tif_image, "tiff_image",unique_id, bankname,varchar_seq_num,destPath,customer);
			 		logger.info("TIFF PATH -------->"+tiff_path);
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
			 	
			 	if(customer.getDeb_emailid().equals(""))
			 	{
			 		customer.setDeb_emailid("A@A.COM");
			 	}
				//Start creating XML file once the images are saved 
				if(new WriteXMLFile().CreateMandateXML(session,customer, jpg_path, tiff_path,bankloginid,shortcode,destPath,zip_path,varchar_seq_num))
				{	logger.info("Starting to create xml...");
					logger.info(" uploading Images to their path");
					
				 	if (jpg_path != null && tiff_path != null  ) 
				 	{
				 		
				 		logger.info("Data to be saved 1-->"+customer);
				 		
				 		//if data saved successfully
				 		if(customerDao.save(customer) != null)
				 		{
				 			logger.info("Data inserted and xml file saved successfully");
				 			
				 			if(sms_flag.equalsIgnoreCase("Y")) 
				 			{
				 				logger.info("Sending sms...");
				 				if(new sendMandateSms().sendSMS(customer,"CREATE",smsbankusername, smsbankuserid, smsbankpassword)) 
				 				{
				 					customer = customerDao.findByUniqueId(unique_id);
				 					customer.setIs_sms_sent("Y");
				 					if(customerDao.save(customer) != null)
				 					{
				 						logger.info("Message send successfully");
				 					}
				 					else
				 					{
				 						logger.info("Unable to sms details in db");
				 					}
				 						
				 				
				 				}
				 				else 
				 				{
				 					customer = customerDao.findByUniqueId(unique_id);
				 					customer.setIs_sms_sent("NS");
				 					if(customerDao.save(customer) != null)
				 						logger.info("Message not sent");
				 					
				 				}
				 				model.addAttribute("Unique_id","Mandate Has Been Generated Successfully having Uniqueid: "+unique_id);
				 			}
				 			else 
				 			{
				 			
				 			
				 				model.addAttribute("Unique_id","Mandate Has Been Generated Successfully having Uniqueid: "+unique_id);
				 			}
				 					
				 		}
				 		else 
				 		{
				 			logger.info("Error creating XML File");
				 			model.addAttribute("Unique_id", "Exception occured. Please contact System Administrator.");
				 		}
				 	}
				 	else
				 	{
				 		logger.info("Cannot move further as image filepath is empty");
				 		model.addAttribute("Unique_id", "Exception occured. Please contact System Administrator.");
				 	}
					
					
				}
				else 
				{
					model.addAttribute("Unique_id","Exception occured. Please contact System Administrator.");
				}
				
				
				
			}
			catch(Exception e) 
			{
				logger.error("Exception occured while mandate creation :"+e);
				e.printStackTrace();
			}
			
		}
		
 }
	
	//<------------------------------------------ Creating path and uploading images to the path --------------------------------->
	public String processFile(MultipartFile file, String fieldName,String UID,String bank_name,String varchar_seq_num,
			String destPath,MdtInitRequestH2h customer) throws IOException {
		
		 String filePath = "";
		 String jpeg_img_name = "";
		 String tiff_img_name = "";
		 
		 bank = banksdao.findByBankname(bank_name);
		 
		 if(bank != null) 
		 {
			 logger.info("Path for tiff and jpg images -->"+destPath);
			 
			 if (!file.isEmpty()) {
				 
				 String fileExtension = getFileExtension(file.getOriginalFilename());
				 logger.info("file extension"+fileExtension);
				 
				 String CurrentDate = new SimpleDateFormat("ddMMyyyy").format(new Date());
				 
				if(fieldName.equalsIgnoreCase("jpg_image")) 
				{
					
					filePath = String.valueOf(destPath) + UID + "/CREATE" + "/" + "MMS-CREATE-" + 
							bank.getShortcode() + "-" + bank.getBankloginid() + "-" + CurrentDate + "-" + varchar_seq_num + "_detailfront" +
							"." + fileExtension.trim();
				 jpeg_img_name = "MMS-CREATE-" + bank.getShortcode() + "-" + bank.getBankloginid() + "-" + CurrentDate + "-" + varchar_seq_num + 
						 "_detailfront" +"." + fileExtension.trim();
				 logger.info("JPEG Image -->"+jpeg_img_name);
					/* customer.setMdt_jpeg_img_name(jpeg_img_name); */
					
				}
				else 
				{
		
					filePath = String.valueOf(destPath) + UID + "/CREATE" + "/" + "MMS-CREATE-" + bank.getShortcode()+
							"-" + bank.getBankloginid()+ "-" + CurrentDate + "-" + varchar_seq_num + "_front" + "." + fileExtension.trim();
					tiff_img_name = "MMS-CREATE-" + bank.getShortcode()+"-" + bank.getBankloginid()+ "-" + CurrentDate + "-" + varchar_seq_num + 
							 "_front" + "." + fileExtension.trim();
					 logger.info("JPEG Image -->"+tiff_img_name);
						/* customer.setMdt_tiff_img_name(tiff_img_name); */
				 }
				 
				 Path directory = Paths.get(destPath +UID+ "/CREATE/");
				 if (!Files.exists(directory)) {
		                Files.createDirectories(directory);
		          }
				 
				 byte[] bytes = file.getBytes();
	            Path path = Paths.get(filePath);
	            Files.write(path, bytes);
	            logger.info("File uploaded successfully to: " + filePath);
			 }
			return filePath;
			 
		 }
		 else 
		 {
			 logger.info("Unable to fetch bankdetails to name the image");
			 return null;
		 }
		 
		
	}
	
	//<------------------------------------------ Get Extension of the image file ----------------------------------------->
	private String getFileExtension(String fileName) 
	{
        // Extract file extension from the provided file name
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	}

	
}
