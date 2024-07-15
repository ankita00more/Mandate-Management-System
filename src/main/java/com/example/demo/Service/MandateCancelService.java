package com.example.demo.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Dao.MdtInitRequestH2hRepository;


public class MandateCancelService {
	
	/*
	 * MdtInitRequestH2hRepository repo;
	 * 
	 * @Autowired public void MDTInitRequestService(MdtInitRequestH2hRepository
	 * repository) { this.repo = repository; }
	 * 
	 * public List<String> getUniqueIddata(String data, String value, String
	 * bankName) { return repo.findUniqueIdByCriteria(data, value, bankName); }
	 * 
	 */
	@Autowired
	MdtInitRequestH2hRepository Dao;
	public List<String> getUniqueIddata(String data, String value, String bankName){
		
		List<String> id = null;
		if(data.equalsIgnoreCase("DEBTOR_ACCOUNT_NO")) {
			
			
			id = Dao.getuniqueidbydebtor_account_no(value, bankName);
			return id;
			
		}
		else if(data.equalsIgnoreCase("UNIQUE_ID")) {
			
			id = Dao.getuniqueidbyumrn(value, bankName);
			return id;
			
		}
		else {
			
			id = Dao.getuniqueid(value, bankName);
			return id;
		
		}
		
		
		
	}

}
