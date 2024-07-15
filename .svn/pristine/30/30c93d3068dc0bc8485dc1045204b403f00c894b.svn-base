package com.example.demo.Service;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.Dao.BankDetailsNachRepository;
import com.example.demo.Dao.BanksConfigurationRepository;
import com.example.demo.Dao.InputDebitSequenceRepository;
import com.example.demo.Dao.MdtransactionRepository;
import com.example.demo.controller.AuthGenerateTransaction;
import com.example.demo.entity.BankDetailsNach;
import com.example.demo.entity.User;
import com.example.demo.entity.inputdebitsequence_h2h;

import com.example.demo.utilities.DateMaster;
import com.example.demo.utilities.InputGeneration;
import com.example.demo.utilities.PathsMaster;


@Service
public class AuthGenServiceImpl {
	
	@Autowired
	MdtransactionRepository mdtdao;
	
	@Autowired
	BankDetailsNachRepository banksdao;
	
	@Autowired
	private InputDebitSequenceRepository seqdao;
	
	@Autowired
	BanksConfigurationRepository configRepo;
	
	inputdebitsequence_h2h inpseq = new inputdebitsequence_h2h();
	
	@Value("${app.INPToNPCI}")
	String INPToNPCI;
	
	@Value("${app.INPToNPCITEMP}")
	String INPToNPCITEMP;
	
	private final Logger logger = Logger.getLogger(AuthGenServiceImpl.class);
	
	
	BankDetailsNach details;
	
	public boolean downloadFile(HttpSession session,String filepath,String seldate,Model model) 
	{
		  boolean flag;
		  User attribute = (User) session.getAttribute("user");
		  String bank_name = attribute.getBankname();
		  
		  PathsMaster pm = new PathsMaster(configRepo);
		  String srcdir =   pm.getInpToNPCI(bank_name,filepath);
		  logger.debug("Directory path would be for inp file -->"+srcdir);
		  String[] temp = seldate.split("-");
		  
		  String filedate = temp[2]+temp[1]+temp[0];
		  logger.debug("Filedate will be -->"+filedate);
		  
			
		   
		  
		  	String[] args1 = new String[] { srcdir };
		  	String AllFileNames = "";
			String fileName = "";
			int fileFount = 0;
			
			List<String> nextDir = new ArrayList<String>();
			nextDir.add(args1[0]);
			while (nextDir.size() > 0) 
			{
				File pathName = new File(nextDir.get(0));
				String[] fileNames = pathName.list();
				logger.debug("fileNames"+fileNames);
				logger.debug("size --> " + fileNames.length);
				for (int i = 0; i < fileNames.length; i++) 
				{
					logger.debug("Each filename"+fileNames[i]);
					logger.debug("Each file path -->"+pathName.getPath());
					File f = new File(pathName.getPath(), fileNames[i]);

					if (f.isDirectory()) 
					{
						nextDir.add(f.getPath());
					} else 
					{
						fileName = fileNames[i];

						String[] acarray = fileName.split("-");
						String DateFromFile = acarray[4].trim();
						logger.debug("Created file Date -->"+DateFromFile);
						if (DateFromFile.equalsIgnoreCase(filedate)) 
						{

							AllFileNames = AllFileNames.concat(fileName)
									.concat(",");
							
							fileFount = 1;

						}

					}
				}
				nextDir.remove(0);
			}
			
			if(fileFount==0)
			{
				logger.debug("File not found ...");
				model.addAttribute("error","No File Found for Download for Date: "+seldate);
				model.addAttribute("seldate", seldate);
				flag = false;
				
			}
			else
			{
				logger.debug("All downloaded filename -->"+AllFileNames);
				model.addAttribute("AllFileNames", AllFileNames);
				model.addAttribute("srcdir", srcdir);
				model.addAttribute("seldate", seldate);
				flag = true;
				
			}
			return flag;
		
	}
	
	public void updateMaxAmount(String[] transactionDataArray,Model model,HttpSession session) {
		logger.info("Inside update amount");
		if(transactionDataArray != null && !transactionDataArray.equals("")&&transactionDataArray.length!= 0) {
			try 
			{
				User attribute = (User) session.getAttribute("user");
				String bank_name = attribute.getUsername();
				PathsMaster pm = new PathsMaster(configRepo);
				String despath = pm.getInpToNPCI(bank_name,INPToNPCI);
				
				logger.info("Destpath would be -->"+despath);

				int val = 0;
				int rows = 0;
				String AMT = "";
				String UID = "";
				
				for(String data : transactionDataArray) 
				{
					String[] parts = data.split("-"); 
					AMT = parts[0].trim();
					UID = parts[1].trim();
					String creation_date = DateMaster.getDateonly();
					rows = mdtdao.updatedata(AMT, UID, creation_date);
					rows++;
				}
				logger.info("Total rows updated :"+rows);
				
				if(rows > 0)
				{
					model.addAttribute("msg","Data updated successfully");
				}
				else
				{
					model.addAttribute("msg","Something went wrong while updation please contact system administrator");
				}
				
				model.addAttribute("generateInputsize",val);
				
				
						
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}		
	}
	
	
	public boolean InpFileGen(String bank_name,String username,HttpSession session) 
	{
		
		boolean inpgenflag = false;
		InputGeneration inp = new InputGeneration(mdtdao);
		String bankloginid = "";
		String shortcode = "";
		String userno = "";
		String sponifsc = "";
		String today = "";
		User attribute = (User) session.getAttribute("user");
		String sms_flag = attribute.getSms_flag();
		String smsusername = attribute.getSmsbankusername();
		String smsid = attribute.getSmsbankuserid();
		String smspass = attribute.getSmsbankpassword();
		
		//Path for generated inp file
		PathsMaster pm = new PathsMaster(configRepo);
		logger.info("Path from properties file -->"+INPToNPCI);
		String despath = pm.getInpToNPCI(bank_name,INPToNPCI);
		String temppath = pm.getInpToNPCITEMP(bank_name, INPToNPCITEMP);
		logger.info("Destination of generated inp file -->"+despath);
		logger.info("temp path of generated inp file -->"+temppath);
		int cnt = inpseq1(bank_name);
		cnt = cnt + 1;
		logger.info("Count -->"+cnt);
		  try 
		  {
			  
			  int rows = seqdao.updatedata(cnt,bank_name);
			  
			  if(rows > 0) 
			  {
					 logger.info("Data updated successfully..."); 
					 
			  } 
			  else 
			  {
					 logger.info("Cannot update data"); 
			  }

		  }
		  catch(Exception e) 
		  {
			  logger.error("Exception occured while fetching sequence");
		  }
		  
		 // String fileseq = (new StringBuilder(String.valueOf(cnt))).toString();
		  String fileseq = cnt +"";
		  if (fileseq.length() != 6 && fileseq.length() < 6) 
		  {
			  int temp = 6 -fileseq.length();
			  for (int i = 0; i < temp; i++) 
				  fileseq = "0".concat(fileseq); 
		  }
		  
		 
	
		
		details = banksdao.findByBankname(bank_name);
		
		if(details != null)
		{
		    bankloginid = details.getBankloginid();
			shortcode = details.getShortcode();
			userno = details.getUserno();
			sponifsc = details.getIfsc_code();
			today = DateMaster.getDateonly();
			
			//NPCI FILENAME WILL BE
			String npcifilename = "ACH-DR-"+shortcode+"-"+bankloginid+"-"+today+"-"+fileseq+"-INP.txt";
			logger.info("NPCIFILENAME WILL BE -->"+npcifilename);
			logger.info("File Sequence -->"+fileseq);
			logger.info("shortcode:"+shortcode+"bankloginid:"+bankloginid+"Userno:"+userno+"ifsc code:"+sponifsc);
			
			if(inp.writes(bank_name, npcifilename,fileseq,username, bankloginid,sponifsc, userno,despath,sms_flag,smsusername,smsid,smspass,temppath))
			{
				inpgenflag = true;
				logger.info("Inp file creation successful");
			}
			else
			{
				inpgenflag = false;
				logger.info("Failed to create Inp file");
			}
		}
		
		return inpgenflag;	
	}
	
	public int inpseq1(String bankname) {
		String sdate = DateMaster.getDateonly();
		int count = 0;
		count = seqdao.getseqcount(bankname, sdate);
		if(count == 0) {
			String date = DateMaster.getDateonly();
			inpseq.setBANKNAME(bankname);
			inpseq.setSDATE(date);
			inpseq.setSEQNO("0");
			seqdao.save(inpseq);
			
		}
		else {
			count = seqdao.getcount(bankname, sdate);
		}
		logger.info("Sequence count for this bank is -->"+count);
		
		return count;
		
	}
	
	
	public String filedownloadproc() {
		
		
		return INPToNPCI;
		
	}

}
