package com.example.demo.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

import java.util.Date;
import com.example.demo.entity.MdtInitRequestH2h;



public class MandateZIP 
{

	private final Logger logger = Logger.getLogger(MandateZIP.class);
	
	public boolean creatZIP(MdtInitRequestH2h customer,String jpeg_file_path1, String tiff_file_path1,String xml_file_path, String bankuser,
			String shortcode,String bankloginid,String destpath,String seq_no) throws IOException {
		logger.info("Creating zip file");
		String filePath = "";
		Boolean flag = true;
		try 
		{
			logger.info("Inside try block....");
			
			//GET CURRENT DATE
			Date now = new Date();
			SimpleDateFormat timeFormatter = new SimpleDateFormat("ddMMyyyy");
			String Currentdate = timeFormatter.format(now);
			
			logger.info("Zip file directory path"+destpath);
			Path directory = Paths.get(destpath);
			if (!Files.exists(directory)) 
			{
				 Files.createDirectories(directory);
			}
			
			//Check if the file is of CREATE or AMEND
			if (jpeg_file_path1.contains("CREATE") && tiff_file_path1.contains("CREATE")) 
			{
				filePath = (destpath.trim()) + "/" + "MMS-CREATE-" + shortcode + "-" + bankloginid + "-" + Currentdate + "-" +seq_no + "-INP" + ".zip";
				
			} 
			else if (jpeg_file_path1.contains("AMEND") && tiff_file_path1.contains("AMEND")) 
			{
				filePath = (destpath.trim()) + "/" + "MMS-AMEND-" + shortcode + "-" + bankloginid + "-" + Currentdate + "-" + seq_no + "-INP" + ".zip";
				
			}
			
			logger.info("File path zip -->"+filePath);
			
			String[] srcFiles = { xml_file_path, jpeg_file_path1,tiff_file_path1};
			{
				try 
				{

					// create byte buffer
					byte[] buffer = new byte[1024];
					FileOutputStream fos = new FileOutputStream(filePath);
					ZipOutputStream zos = new ZipOutputStream(fos);
					for (int i = 0; i < srcFiles.length; i++) {
						File srcFile = new File(srcFiles[i]);
					   logger.info("Source filename -->"+srcFiles[i]);
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
					// close the ZipOutputStream
					zos.close();
					flag = true;
				} 
				catch (IOException ioe) 
				{
					flag = false;
					ioe.printStackTrace();
					logger.error("Exception occured while creating zip:"+ioe);
				}
			}
		}
		catch (Exception e) 
		{
			flag = false;
			e.printStackTrace();
			logger.error("Exception occured while creating zip:"+e);
		}
		return flag; 
	}

}
