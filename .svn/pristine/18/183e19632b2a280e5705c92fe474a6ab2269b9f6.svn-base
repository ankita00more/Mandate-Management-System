package com.example.demo.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.example.demo.Dao.MdtransactionRepository;
import com.example.demo.entity.mdt_transaction_request_h2h;

public class InputGeneration 
{
	
	  
	@Autowired
	private MdtransactionRepository repo;
	
	mdt_transaction_request_h2h mdt;
	
	private final Logger logger = Logger.getLogger(InputGeneration.class);
	
	public InputGeneration(MdtransactionRepository repo) 
	{
        this.repo = repo;
    }

	public boolean writes(String bankname, String npcifilename,String fileseq, String username,String bankloginid, 
			String sponifsc,String userno,String npciinpfolder,String sms_flag,String smsusername,String smsid,String smspass,String temppath) 
	{
		long start=System.currentTimeMillis();
		
		logger.info("<--- Starting file creation -->"+start);
		boolean inpflag = false;
		String rcd = "";
		String usnm = "";
		String aud = "";
		String ACHtrancode = "56";
		String ACHfileno = fileseq;
		String totamt = "";
		String userref = "";
		String totitems = "";
		String shortcode = "";
		String bankuser = "";
		int writeCnt = 0;
		int counter = 0;
		int Record_counter = 0;
		String NewtransactionNo = "";
		Map<String, String> ind = new Hashtable<String, String>();
		String Amt2 = "";
		String Amount = "";
		DateMaster dm = new DateMaster();
		Model model = null;
		
		try
		{
			 mdt_transaction_request_h2h cust;
			 ArrayList<mdt_transaction_request_h2h> customer =  new ArrayList<>();
			 
			 SimpleDateFormat sd1 = new SimpleDateFormat("ddMMyyHHmmssS");
			 Date now = new Date();
			 String strDate1 = sd1.format(now);
			 logger.info("StrDate size"+strDate1.length());
			 strDate1 = strDate1.substring(0, 14);
			 String strDate = new StringBuffer(strDate1).insert(0, bankname).toString();
			 
			 userref = strDate;
			 
			 logger.info("userref --> "+userref);
			 
			 String today = dm.getDateonly();
			 
			 //Write file first in temp folder
			 File directory = new File(temppath);
			 if (!directory.exists())
			 {
			        directory.mkdir();
			 }
			 
			 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
			 Date Currentdatetime = new Date();
			 String DT1 = dateFormat.format(Currentdatetime);
			 
			 String FileLocation = temppath+ npcifilename;
			 logger.info(FileLocation);
			 
			 customer = repo.getinpfiledata(bankname, DT1);
			 logger.info("List size -->"+customer.size());
			 
			 if(customer.size() < 1) 
			 {
				 inpflag = true;
				 logger.info("Cannot create file as data is not avaliable");
			 }
			 else
			 {
				 int cnt = 0;
				 for(int i=0;i<customer.size();i++) 
				 {
					 System.out.print("Deb name ->"+customer.get(i).getDEBTOR_NAME());
					 ind.put("DEBTOR_NAME"+cnt,customer.get(i).getDEBTOR_NAME());
					 ind.put("AMOUNT"+cnt,customer.get(i).getAMOUNT());
					 ind.put("DEBTOR_IDENTIFICATION_NO"+cnt,customer.get(i).getDEBTOR_IDENTIFICATION_NO());
					 ind.put("DEBTOR_ACCOUNT_NO"+cnt,customer.get(i).getDEBTOR_ACCOUNT_NO());
					 ind.put("CREDITOR_IDENTIFICATION_NO"+cnt,customer.get(i).getCREDITOR_IDENTIFICATION());
					 if(customer.get(i).getUMRN()!= null) 
					 {
						 ind.put("UMRN"+cnt,customer.get(i).getUMRN());
					 }
					 else 
					 {
						 ind.put("UMRN"+cnt,"");
					 }
					 ind.put("CREDITOR_NAME"+cnt,customer.get(i).getCREDITOR_NAME());
					 ind.put("CREATION_DATE"+cnt,customer.get(i).getCREATION_DATE().toString());
					 cnt++;
				 }
				 
				 Record_counter = customer.size();
				 logger.info("Count of records -->"+Record_counter);
				 
				 //Iterating the values 
				  Iterator<mdt_transaction_request_h2h> iterator = customer.iterator();
				  if(iterator.hasNext())
				  {
					  if(Record_counter > 0)
					  {
						  double totAMT = 0.0D;
						  //Calculate total Amount
						  Iterator<mdt_transaction_request_h2h> amtitr = customer.iterator();
						  
						  while (amtitr.hasNext()) 
						  {
							  mdt_transaction_request_h2h cust1 = amtitr.next();
							  String tatalAmnt = cust1.getAMOUNT();
							  double amt = Double.parseDouble(tatalAmnt);
							  logger.info("Amount in Header --> "+amt);
							  totAMT += amt;
						  }
						  
						  logger.info("TOTAL AMOUNT: " + totAMT+ "in file: "+npcifilename);
						  
						  DecimalFormat df = new DecimalFormat("#.00");
						  totamt = df.format(totAMT);
						  logger.info("Decimal formated AMOUNT: " + totamt);
						  
						  BigDecimal a = new BigDecimal(totamt);
						  BigDecimal b = new BigDecimal("100");
						  BigDecimal multiplied = a.multiply(b);
						  
						  String Amtt = String.valueOf(multiplied);
						  logger.info("Amount multiplied by 100:" + Amtt);
						  
						  if (Amtt.contains(".")) 
						  {
							    String[] amt_array = Amtt.split("\\.");
								if (amt_array[1].length() == 1
										&& amt_array[1].trim() == "0") 
								{
									Amt2 = Amtt.replace(".0", "");
								} 
								else 
								{
									Amt2 = Amtt.replace(".00", "");
								}
							  
						  }
						  else
						  {
							  Amt2 = Amtt.replace(".0", "");
						  }
						  
						  if (Amt2 == null || Amt2.length() < 14 || Amt2 == " ") 
						  {
								Amount = ("0000000000000" + Amt2).substring(Amt2
										.length());
						  } 
						  else 
						  {
								Amount = Amt2.substring(Amt2.length() - 14);
						  }
							
					  }
				  }
				  
				  totitems = String.valueOf(customer.size());
				  
				  //USERNAME
				  if (bankloginid.length() != 40 && bankloginid.length() < 40) 
				  {
						int temp = 40 - bankloginid.length();
						for (int i = 0; i < temp; i++) 
						{
							bankloginid = bankloginid.concat(" ");
						}
				  } 
				  else if (bankloginid.length() != 40 && bankloginid.length() > 40) 
				  {
					   bankloginid = bankloginid.substring(0, 39);
						int temp = 40 - bankloginid.length();
						for (int i = 0; i < temp; i++) 
						{
							bankloginid = bankloginid.concat(" ");
						}

				 }
				  
				 //ACHFILENO
				  if (ACHfileno.length() != 9 && ACHfileno.length() < 9) 
				  {
						int temp = 9 - ACHfileno.length();
						for (int i = 0; i < temp; i++)
						{
							ACHfileno = "0".concat(ACHfileno);
						}
				  } 
				  else if (ACHfileno.length() != 9 && ACHfileno.length() > 9) 
				  {
						ACHfileno = ACHfileno.substring(0, 8);
						int temp = 9 - ACHfileno.length();
						for (int i = 0; i < temp; i++) 
						{
							ACHfileno = "0".concat(ACHfileno);
						}
				  }
				  
				  logger.info("ACHFILENO : "+ACHfileno);
				  
				  //USERNO
				  if (userno.length() != 18 && userno.length() < 18) 
				  {
						int temp = 18 - userno.length();
						for (int i = 0; i < temp; i++) {
							userno = userno.concat(" ");
						}
				  }
				  else if (userno.length() != 18 && userno.length() > 18) 
				  {
						userno = userno.substring(0, 17);
						int temp = 18 - userno.length();
						for (int i = 0; i < temp; i++) 
						{
							userno = userno.concat(" ");
						}
				  }
				  
				  logger.info("Userno :"+userno);
				  
				  //USERREF
				  if (userref.length() != 18 && userref.length() < 18) 
				  {
						int temp = 18 - userref.length();
						for (int i = 0; i < temp; i++) 
						{
							userref = userref.concat(" ");
						}
				  } 
				  else if (userref.length() != 18 && userref.length() > 18) 
				  {
						userref = userref.substring(0, 17);
						int temp = 18 - userref.length();
						for (int i = 0; i < temp; i++) 
						{
							userref = userref.concat(" ");
						}

				  }
				  
				  logger.info(" userref ---> " + userref);
				  
				  //SPONSIFSC
				  if (sponifsc.length() != 11 && sponifsc.length() < 11) 
				  {
						int temp = 11 - sponifsc.length();
						for (int i = 0; i < temp; i++) 
						{
							sponifsc = sponifsc.concat(" ");
						}
				  }
				  else if (sponifsc.length() != 11 && sponifsc.length() > 11) 
				  {
						sponifsc = sponifsc.substring(0, 10);
						int temp = 11 - sponifsc.length();
						for (int i = 0; i < temp; i++) 
						{
							sponifsc = sponifsc.concat(" ");
						}
				  }
				  
				 logger.info(" sponifsc ---> " + sponifsc);
				 
				 //TOTAL ITEMS
				 if (totitems.length() != 9 && totitems.length() < 9) 
				 {
						int temp = 9 - totitems.length();
						for (int i = 0; i < temp; i++) 
						{
							totitems = "0".concat(totitems);
						}
				 } 
				 else if (totitems.length() != 9 && totitems.length() > 9) 
				 {
						totitems = totitems.substring(0, 8);
						int temp = 9 - totitems.length();
						for (int i = 0; i < temp; i++) 
						{
							totitems = "0".concat(totitems);
						}

				}
				 
				logger.info("Creating Header for file " + npcifilename + " for bank " + bankname + " at " + DateMaster.getDateonly());
				logger.info("File location:" + FileLocation);
				
				 BufferedWriter out = new BufferedWriter(new FileWriter(FileLocation));
				 
				 out.write(ACHtrancode);
				 out.write("       ");
				 out.write(bankloginid);
				 out.write("              ");
				 out.write(ACHfileno);
				 out.write("         ");
				 out.write("               ");
				 out.write("   ");
				 out.write("             ");
				 out.write(Amount);
				 out.write(today);
				 out.write("          ");
				 out.write("          ");
				 out.write("   ");
				 out.write(userno);
				 out.write(userref);
				 out.write(sponifsc);
				 out.write("                                   ");
				 out.write(totitems);
				 out.write("  ");
				 out.write("                                                         ");
				 out.newLine();
				 
				// write individual record into file
				int indcount = customer.size();
				logger.info("data length: "+indcount);
				
				String ACHtrancodeind = "67";
				String benfaccname = "";
				String indamount = "";
				String Amt5 = "";
				String Amt6 = "";
				String Amount1 = "";
				String destbankiin = "";
				String benfaccno = "";
				String indtranref = "";
				String umrn = "";
				
				for (int i = 0; i < indcount; i++) 
				{
					writeCnt++;
					out.write(ACHtrancodeind);
					out.write("         ");
					out.write("10");
					out.write("   ");
					out.write("               ");
					//Debtor Name
			        benfaccname = ((String)ind.get("DEBTOR_NAME"+i)).toString().trim();
			        if (benfaccname.length() != 40 && benfaccname.length() < 40) 
			        {
						int temp = 40 - benfaccname.length();
						for (int j = 0; j < temp; j++) 
						{
							benfaccname = benfaccname.concat(" ");
						}
					} 
			        else if (benfaccname.length() != 40&& benfaccname.length() > 40) 
			        {
						benfaccname = benfaccname.substring(0, 39);
						int temp = 40 - benfaccname.length();
						for (int j = 0; j < temp; j++) 
						{
							benfaccname = benfaccname.concat(" ");
						}

					}
			        
			        out.write(benfaccname);
					out.write("         ");
					out.write("       ");
					
					//username
			        String indusername = ((String)ind.get("CREDITOR_NAME"+i)).toString().trim();
			        if (bankloginid.length() != 20 && bankloginid.length() < 20) 
			        {
						int temp = 20 - indusername.length();
						for (int j = 0; j < temp; j++) 
						{
							bankloginid = bankloginid.concat(" ");
						}
					} 
			        else if (bankloginid.length() != 20 && bankloginid.length() > 20) 
			        {
			        	bankloginid = bankloginid.substring(0, 19);
						int temp = 20 - bankloginid.length();
						for (int j = 0; j < temp; j++) 
						{
							bankloginid = bankloginid.concat(" ");
						}
					}
			        
			        out.write(bankloginid);
					out.write("             ");
					
					//AMOUNT
					indamount = ((String)ind.get("AMOUNT"+i)).toString().trim();
					double amtdata = Double.parseDouble(indamount);
			        DecimalFormat dft = new DecimalFormat("#.00");
			        String finalamt = dft.format(amtdata);
			        logger.info("Decimal formatted amount --> "+finalamt);
			        String amtfinal = "";
			        BigDecimal a = new BigDecimal(finalamt);
			        BigDecimal b = new BigDecimal("100");
			        BigDecimal multiplied1 = a.multiply(b);
			        Amt6 = String.valueOf(multiplied1);
			        logger.info("Amount value will be ---->"+Amt6);
			        logger.info("Amount multiplied with 100 --> "+Amt6);
			        
			        if (Amt6.contains(".")) 
			        {
			        	String[] amount_array  = Amt6.split("\\.");
			        	if (amount_array[1].length() == 1 && amount_array[1].trim() == "0") 
				        {
			        		amtfinal = Amt6.replace(".0", "");
				        }
			        	else 
				        {
			        		amtfinal = Amt6.replace(".00", "");
				        } 
			        	
			        }
			        else
			        {
			        	amtfinal = Amt6.replace(".0", "");
			        }
			        
			        if (amtfinal == null || amtfinal.length() < 14 || amtfinal == " ") 
			        {
			          Amount1 = ("0000000000000" + amtfinal).substring(amtfinal.length());
			        } 
			        else 
			        {
			          Amount1 = amtfinal.substring(amtfinal.length() - 14);
			        } 
			        
			        out.write(Amount1);
					out.write("          ");
					out.write("          ");
					out.write(" ");
					out.write("  ");
					
					//DEBTOR_IDENTIFICATION_NO
					destbankiin = ((String)ind.get("DEBTOR_IDENTIFICATION_NO"+i)).toString().trim();
					if (destbankiin.length() != 11 && destbankiin.length() < 11) 
					{
						int temp = 11 - destbankiin.length();
						for (int j = 0; j < temp; j++) 
						{
							destbankiin = destbankiin.concat(" ");
						}
					} 
					else if (destbankiin.length() != 11 && destbankiin.length() < 11)
					{
						destbankiin = destbankiin.substring(0, 10);
						int temp = 11 - destbankiin.length();
						for (int j = 0; j < temp; j++) 
						{
							destbankiin = destbankiin.concat(" ");
						}
					}
					
					out.write(destbankiin);
					
					//account number
			        benfaccno = ((String)ind.get("DEBTOR_ACCOUNT_NO"+i)).toString().trim();
			        if (benfaccno.length() != 35 && benfaccno.length() < 35) 
			        {
						int temp = 35 - benfaccno.length();
						for (int j = 0; j < temp; j++) 
						{
							benfaccno = benfaccno.concat(" ");
						}
					} 
			        else if (benfaccno.length() != 35 && benfaccno.length() > 35) 
			        {

						benfaccno = benfaccno.substring(0, 34);
						int temp = 35 - benfaccno.length();
						for (int j = 0; j < temp; j++) 
						{
							benfaccno = benfaccno.concat(" ");
						}
					}
			        
			        out.write(benfaccno);
					out.write(sponifsc);
					out.write(userno);
					
					SimpleDateFormat sd = new SimpleDateFormat("ddMMyyyyHHmmssSSS");
					Date now1 = new Date();
					String strDate11 = sd.format(now1);
					String datenew = strDate11.substring(0, 14);
					
					if (writeCnt > 1) 
					{
						counter = writeCnt - 1;

					}
					
					String counter1 = String.valueOf(counter);
					if (counter1.length() < 5) 
					{

						int temp = 5 - counter1.length();
						for (int j = 0; j < temp; j++) 
						{
							counter1 = "0".concat(counter1);
						}
						NewtransactionNo = datenew + counter1;
					}
					
					String strDate2 = new StringBuffer(NewtransactionNo).insert(0,bankname).toString();
					indtranref = strDate2;
					
					if (indtranref.length() != 30 && indtranref.length() < 30) 
					{
						int temp = 30 - indtranref.length();
						for (int j = 0; j < temp; j++) 
						{
							indtranref = indtranref.concat(" ");
						}
					} 
					else if (indtranref.length() != 30 && indtranref.length() > 30)
					{

						indtranref = indtranref.substring(0, 29);
						int temp = 30 - indtranref.length();
						for (int j = 0; j < temp; j++) {
							indtranref = indtranref.concat(" ");
						}
					}
					
					out.write(indtranref);
					out.write("10 ");
					out.write("               ");
					
					//UMRN
					umrn = ((String)ind.get("UMRN"+i)).toString().trim();
					if (umrn.length() != 20 && umrn.length() < 20) 
					{
						int temp = 20 - umrn.length();
						for (int j = 0; j < temp; j++) 
						{
							umrn = umrn.concat(" ");
						}
					}
					else if (umrn.length() != 20 && umrn.length() > 20) 
					{

						umrn = umrn.substring(0, 19);
						int temp = 20 - umrn.length();
						for (int j = 0; j < temp; j++) {
							umrn = umrn.concat(" ");
						}
					}
					
					out.write(umrn);
					out.write("       ");
					out.newLine();
					
					logger.info("WRITE record for"+umrn);
					
					int rows = repo.updatefiledata(npcifilename, NewtransactionNo.trim(),bankname,umrn.trim(), DT1);
					logger.info("Rows updated are .."+rows);
		            logger.info("Creating Individual records for file " + npcifilename + " for bank " + bankname + " at " + DT1);
		            logger.info("ACHtrancode:" + ACHtrancode + "username:" + username + "ACHfileno:" + ACHfileno + "Amount" + Amount + 
		            		"today:" + today + "userno:" + userno + "userref:" + userref + "sponifsc:" + totitems);
		           
				}
				
				logger.info("Creating Individual records for file "
						+ npcifilename + " for bank " + bankname + " at "
						+ dm.getDateTime());

				logger.info("ACHtrancode:" + ACHtrancode + "username:"
						+ username + "ACHfileno:" + ACHfileno + "Amount" + Amount
						+ "today:" + today + "userno:" + userno + "userref:"
						+ userref + "sponifsc:" + totitems);

				out.flush();
				out.close();
				
				logger.debug(DT1);
				logger.debug("writeCnt: "+writeCnt+"Customer List: "+customer.size());
				
				if(customer.size()==writeCnt)
				{
					try 
					{
						 File dir = new File(npciinpfolder);
						 if (!dir.exists())
						 {
						        dir.mkdir();
						 }
						File srcfile = new File(FileLocation);
						File destfile = new File(npciinpfolder+npcifilename);
						logger.info("source path: "+srcfile.getAbsolutePath()+ " dest: "+destfile.getAbsolutePath());
						if(srcfile.renameTo(destfile))
				        {
						   inpflag = true;
					 	   logger.info("File moved successfully");
					 	   if(sms_flag.equalsIgnoreCase("Y"))
					 	   {
					 		  if(new sendMandateSms().sendInpSMS(bankname, smsusername, smsid, smspass, repo))
				        	  {
				        		  logger.info("SMS SENT SUCESSFULLY");
				        	  }
					 		  else
					 		  {
					 			 logger.info("SMS NOT SENT");
					 		  }
					 	   }
					 	    
				        }
						else
						{
							logger.info("Failed to move the file");
						}
						
					}
					catch(Exception e)
					{
						logger.error("Error occured while moving file"+e);
					}
				}  
				 
			 }
		}
		catch(Exception e)
		{
			
			logger.error("Exception while writing INP file :"+e);
			e.printStackTrace();
		}
		finally
		{
			long end=System.currentTimeMillis();
			logger.debug("Total time taken to generate input file: "+npcifilename+" : "+ ((end - start) / 1000) + 
  				" seconds "+ (end - start) + " miliseconds "+ ((end - start)/ (1000 * 60)) +" Minutes");
		}
		logger.info("<-- Flag value of inp generation -->"+inpflag);
		return inpflag;
	
		
	}		
}
