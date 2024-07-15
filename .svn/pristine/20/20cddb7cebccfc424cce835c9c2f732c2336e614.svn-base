package com.example.demo.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.Dao.MdtInitRequestH2hRepository;
import com.example.demo.entity.MdtInitRequestH2h;
import com.example.demo.entity.User;

@Service
public class ReportsServiceImpl 
{
	
	//logger
	private static final Logger logger = Logger.getLogger(ReportsServiceImpl.class);
	@Autowired
	MdtInitRequestH2hRepository initrepo;
	
	
	public boolean MandateStatus(HttpSession session,Model model,String fromdate,String todate,String productType)
	{
		Boolean flag = false;
		try
		{
			
			String fromdt = "";
			String todt = "";
			User attribute = (User) session.getAttribute("user");
			String bank_name = attribute.getBankname();
			
			 SimpleDateFormat from_date = new SimpleDateFormat("yyyy-MM-dd");
	         Date date1 = from_date.parse(fromdate);
	         from_date = new SimpleDateFormat("dd-MM-yy");
	         fromdt = from_date.format(date1);
	         
	         SimpleDateFormat to_date = new SimpleDateFormat("yyyy-MM-dd");
	         Date date2 = to_date.parse(todate);
	         to_date = new SimpleDateFormat("dd-MM-yy");
	         todt = to_date.format(date2);
	         
	         logger.info("From date: "+fromdt+" To date: "+todt);
			
			
			if(fromdt != null && todt != null)
			{
				List<MdtInitRequestH2h> init_data = returninitdata(bank_name,fromdt,todt,productType);
				
				if(init_data.size() > 0)
				{
					model.addAttribute("initdata",init_data);
					model.addAttribute("productType",productType);
				}
				else
				{
					model.addAttribute("message","No data found");
				}
				
			}
			
			flag = true;
			
		}
		catch(Exception e)
		{
			flag = false;
			logger.error("Exception Occured:"+e);
		}
		return flag;
		
		
	}
	
	
	public List<MdtInitRequestH2h> returninitdata(String bankname,String fromdt,String todt,String producttype)
	{
		List<MdtInitRequestH2h> initdata = initrepo.getmandatestatus(bankname,fromdt,todt);
		try
		{
			
			logger.info("Init data size: "+initdata.size());
			String reason_desc = "";
			
			for (MdtInitRequestH2h mdtbean : initdata) 
			{
				
				//If we have received ack only from NPCI
				if (producttype.equalsIgnoreCase("ACK")) 
				{
					 if (mdtbean.getStatus()!= null && mdtbean.getStatus().trim().equalsIgnoreCase("Positive ACK from NPCI")) 
					 {
						 mdtbean.setStatus("ACCEPTED");
					 }
					 else if (mdtbean.getStatus()!= null && mdtbean.getStatus().trim().equalsIgnoreCase("Negative ACK from NPCI")) 
					 {
						 mdtbean.setStatus("REJECTED");
					 }
					 else
					 {
						 mdtbean.setStatus("");
					 }
				}
				else if (producttype.equalsIgnoreCase("RES"))
				{
					 if (mdtbean.getBenificiary_status()!= null && mdtbean.getBenificiary_status().trim().equalsIgnoreCase("true")) 
					 {
						 mdtbean.setBenificiary_status("ACCEPTED");
					 }
					 else if (mdtbean.getBenificiary_status()!= null && mdtbean.getBenificiary_status().trim().equalsIgnoreCase("false")) 
					 {
						 
						 mdtbean.setBenificiary_status("REJECTED");
						 mdtbean.setReject_reason(mdtbean.getReject_reason());
						 if(mdtbean.getReject_reason() != null)
						 { 
							 reason_desc = initrepo.getreasondesc(mdtbean.getReject_reason().trim());
						 }
						 mdtbean.setReasonDesc(reason_desc);
					 }
					 else
					 {
						 mdtbean.setBenificiary_status("");
					 }
					 mdtbean.setReject_reason(mdtbean.getReject_reason());
					 mdtbean.setReasonDesc(mdtbean.getReasonDesc());
					 logger.info("Reject Reason outside else >>>"+mdtbean.getReasonDesc());
				}
				
				
				
			}
			

			
		}
		catch(Exception e)
		{
			logger.error("Exception occured in if else of fetching data"+e);
		}
		return initdata;
	}


	
}
