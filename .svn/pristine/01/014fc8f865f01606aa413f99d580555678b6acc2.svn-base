package com.example.demo.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Dao.BankDetailsNachRepository;
import com.example.demo.entity.BankDetailsNach;
import com.example.demo.entity.MdtInitRequestH2h;

public class CancelMandateZIP {
	
	@Autowired
	BankDetailsNachRepository repo ;
	
	BankDetailsNach cust = new BankDetailsNach();
	
	private final Logger logger = Logger.getLogger(CancelMandateZIP.class);
	
	public boolean creatZIP(MdtInitRequestH2h customer, String xml_file_path,String bank_name,String destpath,String shortcode,String loginid,
			String seqnum)throws IOException{
		Boolean flag = false;
		try 
		{
			
			logger.info("Zipfilepath would be -->"+destpath);
			Date now = new Date();
			SimpleDateFormat timeFormatter = new SimpleDateFormat("ddMMyyyy");
			String Currentdate = timeFormatter.format(now);
			
			Path directory = Paths.get(destpath);
			if (!Files.exists(directory)) 
			{
				 Files.createDirectories(directory);
			}
			
			String filePath = destpath + "/" + "MMS-CANCEL-" + shortcode+ "-" + loginid+ "-" + 
			Currentdate + "-" + seqnum + "-INP" + ".zip";
			File uploadedFile = new File(filePath);
			String[] srcFiles = { xml_file_path };
			
			{
				try 
				{
					//Create Byte Buffer
					byte[] buffer = new byte[1024];
					FileOutputStream fos = new FileOutputStream(filePath);
					ZipOutputStream zos = new ZipOutputStream(fos);
					
					
					for (int i = 0; i < srcFiles.length; i++) {

						File srcFile = new File(srcFiles[i]);
						FileInputStream fis = new FileInputStream(srcFile);
						zos.putNextEntry(new ZipEntry(srcFile.getName()));
						int length;
						while ((length = fis.read(buffer)) > 0) {
							zos.write(buffer, 0, length);
						}
						zos.closeEntry();
						// close the InputStream
						fis.close();
					}
					
					zos.close();
					
					flag = true;
				}
				catch(Exception e) 
				{
					flag = false;
					e.printStackTrace();
					logger.error("Exception occured while creating zip file"+e);
				}
			}
			
		}
		catch(Exception e) 
		{
			flag = false;
			e.printStackTrace();
			logger.error("Exception occured while creating zip file"+e);
		}
		return flag;
	}

}
