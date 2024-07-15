package com.example.demo.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Dao.MdtransactionRepository;
import com.example.demo.entity.MdtInitRequestH2h;

public class sendMandateSms 
{
	@Autowired
	MdtransactionRepository repo;
	
	private static final Logger logger = Logger.getLogger(sendMandateSms.class);
	
	public boolean sendSMS(MdtInitRequestH2h customer,String MandateType,String username,String sender_id,String password) throws IOException, URISyntaxException
	{
		logger.info("Entering to send sms");
		String smsTemplate = "";
		String mobileno = customer.getDeb_mobno();
		logger.info("Mobile No:"+mobileno);
		
		String uniqueId = customer.getUniqueId();
		logger.info("Unique_id:"+uniqueId);
		
		String bankname = customer.getBank_name();
		logger.info("Bankname:"+bankname);
		
		String inputLine = "";
		boolean update=false;
		
		//Message if create
		if (MandateType.equalsIgnoreCase("CREATE")) 
		{
			smsTemplate = "Dear Customer, your Aadhar XXXXXX" + uniqueId
					+ " is successfully seeded with your account, thank you for banking with us-APG Bank.";
		}
		else if (MandateType.equalsIgnoreCase("CANCEL")) 
		{

			logger.info("CANCEL TEMPLETE");
			smsTemplate = "Dear Customer, your Aadhar XXXXXX" + uniqueId
					+ " is successfully seeded with your account, thank you for banking with us-APG Bank.";
		}
		else if(MandateType.equalsIgnoreCase("AMEND"))
		{
			logger.info("AMEND TEMPLETE");
			smsTemplate = "Dear Customer, your Aadhar XXXXXX" + uniqueId
					+ " is successfully seeded with your account, thank you for banking with us-APG Bank.";
		}
		
		String reqU = "https://hahttp2.myvfirst.com/smpp/sendsms?";
		String stRemitterMobileNo = mobileno.replace("-", "");
		
		stRemitterMobileNo = stRemitterMobileNo.replace("+", "");
		
		String stOTPParameters = "username=" + username + "&password=" + password + "&to=" + stRemitterMobileNo.trim()
		+ "&from=" + sender_id + "&text=" + URLEncoder.encode(smsTemplate, "UTF-8");
		
		String requestURL1 = reqU.concat(stOTPParameters);
		
		String proxyHost = "10.45.19.6";
		int proxyPort = 3128;
		
		// Create proxy configuration
		HttpHost proxy = new HttpHost(proxyHost, proxyPort);
		RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
		
		try (CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build()) 
		{
			HttpGet httpGet = new HttpGet(new URI(requestURL1));
			try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.execute(httpGet).getEntity().getContent())))
			{
				
				while ((inputLine = in.readLine()) != null) 
				{
					if(inputLine.equalsIgnoreCase("sent.")) 
					{
						logger.info("Msg"+inputLine);
						update = true;
					}
					else 
					{
						update = false;
					}
					logger.info("SMS :"+inputLine);
					
					
				}
				
			}
			catch(Exception e) 
			{
				update = false;
				logger.error("Exception when trying to send sms: "+e);
				
			}
		}
		catch(Exception e) 
		{
			update =false;
			logger.error("Exception when trying to send sms: "+e);
		}
		return update;
	}
	
	public boolean sendInpSMS(String bankname, String BankuserName, String Banksender_id, String BankpassWord,
			MdtransactionRepository repo) throws UnsupportedEncodingException 
	{	
		logger.info("Inside send inpsms");
		List<String> data = new ArrayList<>();
		boolean update = false;
		data = repo.getinpsmsdata(bankname);
		logger.info("data size"+data.size());
		for (String mobileNumber : data) 
		{
			
			logger.info("Data would be -->"+mobileNumber);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
			Date Currentdatetime = new Date();
			String DT1 = dateFormat.format(Currentdatetime);
			
			String smsTemplate="";
			
			smsTemplate = "Dear Customer, your Aadhar XXXXXX" + bankname
					+ " is successfully seeded with your account, thank you for banking with us-APG Bank.";
			
			String userName = BankuserName;
			String sender_id = Banksender_id;
			String passWord = BankpassWord;
			String inputLine = "";
			
			
			
				String reqU = "https://hahttp2.myvfirst.com/smpp/sendsms?";
				String stRemitterMobileNo = mobileNumber.replace("-", "");
				stRemitterMobileNo = stRemitterMobileNo.replace("+", "");
				String stOTPParameters = "username=" + userName + "&password=" + passWord + "&to="
						+ stRemitterMobileNo.trim() + "&from=" + sender_id + "&text="
						+ URLEncoder.encode(smsTemplate, "UTF-8");
				
				String requestURL1 = reqU.concat(stOTPParameters);
				
				//PROXY IP AND PORT 
				String proxyHost = "10.1.24.97";
				String Port = "8080";
				int proxyPort = Integer.parseInt(Port);
				
				// Create proxy configuration
				HttpHost proxy = new HttpHost(proxyHost, proxyPort);
				RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
				
				try (CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build()) 
				{
			 
					HttpGet httpGet = new HttpGet(new URI(requestURL1));
			 
					try (BufferedReader in = new BufferedReader(
						new InputStreamReader(httpClient.execute(httpGet).getEntity().getContent()))) 
					{
						while ((inputLine = in.readLine()) != null) 
						{
							logger.info("Msg"+inputLine);
							if(inputLine.equalsIgnoreCase("sent.")) 
							{
								inputLine = "Y";
								update = true;
							}
							else 
							{
								inputLine = "NS";
								update = false;
							}
							logger.info("SMS :"+inputLine);
							if (repo.updatesmsstatus(inputLine,mobileNumber) > 0)
							{
								logger.info("DB update successful");
							}
							else
							{
								logger.info("Cannot update data in database");
							}
							
						}
				}
			} 
			catch (Exception e) 
			{
				update = false;
				logger.error("Exception occurred while sending sms: "+e);
			}
			 
			
		}
		return update;
		
		
		
	}
			
}
