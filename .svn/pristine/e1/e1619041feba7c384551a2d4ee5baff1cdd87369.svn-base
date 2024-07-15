package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.Dao.CatCodeListRepository;
import com.example.demo.Dao.MdtInitRequestH2hRepository;
import com.example.demo.entity.CatCodeList;
import com.example.demo.entity.MdtInitRequestH2h;
import com.example.demo.entity.User;

@Service
public class CancelServiceImpl implements CancelService {
	
	@Autowired
	MdtInitRequestH2hRepository Dao;
	
	@Autowired
	private CatCodeListRepository codeList;
	
	private static final Logger logger = Logger.getLogger(CancelServiceImpl.class);
	
	
	public void search_data(HttpSession session,Model model,String canclopt,String opt) 
	{
		User attribute = (User) session.getAttribute("user");
		String bank_name = attribute.getUsername();
		
		
		logger.debug("Bank Name will be -->"+bank_name);
		List<String> msgId  =new ArrayList<String>();
		
		msgId = getUniqueIddata(canclopt, opt, bank_name);
		logger.debug("Unique id value for cancel would be -->"+msgId);
		  if (msgId.isEmpty()) 
		  {
	            model.addAttribute("messageId", "No Records Found");
	            model.addAttribute("data", msgId);
	      } 
		  else 
		  {
	            model.addAttribute("messageId", msgId.size() + " Mandate found with below Unique ID for Cancellation\n\n");
	            model.addAttribute("data", msgId);
	      }
		
	}
	
    @Override
	public List<String> getUniqueIddata(String data, String value, String bankName) {
		
		List<String> id = null;
		if(data.equalsIgnoreCase("DEBTOR_ACCOUNT_NO")) 
		{
			id = Dao.getuniqueidbydebtor_account_no(value, bankName);
			logger.debug("Inside debtor account number query..."+id);
			return id;
			
		}
		else if(data.equalsIgnoreCase("UMRN")) 
		{
			id = Dao.getuniqueidbyumrn(value, bankName);
			logger.debug("Inside umrn query..."+id);
			return id;
			
		}
		else 
		{
			id = Dao.getuniqueid(value, bankName);
			logger.debug("Inside unique id query...."+id);
			return id;
		
		}
			
	}
    
    @SuppressWarnings("deprecation")
	public void insertdataIntoMdtBkp(String UID) {
    	
    	//Insert data in bkp table
    	Dao.getOne(UID);
    	
    	
    }
    
    public List<CatCodeList> codeList() {
		// TODO Auto-generated method stub
		List<CatCodeList> catCodeList = codeList.findAll();
		return catCodeList;
	}
    
    
    public boolean updateMdt_Int_Requestdata(MdtInitRequestH2h customer,String customerId) {
    	
    	boolean flag = false;
    	try {
    		logger.debug("<-----------------------Unique id for update------------->"+customerId);
    		MdtInitRequestH2h data = Dao.findByUniqueId(customerId);
        	
        	if(data != null) {
        		data.setMdt_request("CANCEL");
        		data.setReason_code(customer.getReason_code());
        		if(Dao.save(data) != null) 
        		{
        			flag = true;
        		}
        		
        		
        	}
        	else {
        		flag =false;
        	}
        	
    		
    	}
    	
    	catch(Exception e) {
    		e.printStackTrace();
    		
    	}
    	logger.debug("Flag value is cancel -------------------->"+flag);
		return flag;
    		
		    	
   }
    
    
    
    
    
    
    
    
    
    
    
    

	

	}


