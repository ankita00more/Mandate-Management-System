package com.example.demo.Service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.MdtInitRequestH2hRepository;
import com.example.demo.Dao.MdtransactionRepository;
import com.example.demo.entity.MdtInitRequestH2h;
import com.example.demo.entity.mdt_transaction_request_h2h;

@Service
public class AddUmrnServiceImpl {
	
	@Autowired
	private MdtransactionRepository repo;
	
	@Autowired
	private MdtInitRequestH2hRepository  dao;

	mdt_transaction_request_h2h transaction = new mdt_transaction_request_h2h();
	MdtInitRequestH2h init;
	
	private static final Logger logger = Logger.getLogger(AddUmrnServiceImpl.class);
	public boolean updatedata(String UMRN,String bankname) 
	{
		boolean flag = false;
		try 
		{
			init = dao.findByUmrn(UMRN);
			String initbank = init.getBank_name().trim();
			if(init != null && initbank.equalsIgnoreCase(bankname.trim()))
			{
				transaction.setUniqueId(init.getUniqueId());
				transaction.setTRANSACTION_TYPE(init.getTransaction_type());
				transaction.setOCCURENCE(init.getOccurence());
				transaction.setFREQUENCY(init.getFrequency());
				transaction.setFIRST_COLLECTION_DATE(init.getFirst_collection_date());
				transaction.setFINAL_COLLECTION_DATE(init.getFinal_collection_date());
				transaction.setAMOUNT_TYPE(init.getAmount_type());
				transaction.setAMOUNT(init.getAmount());
				transaction.setCREDITOR_NAME(init.getCreditor_name());
				transaction.setCREDITOR_IDENTIFICATION(init.getCreditor_identification());
				transaction.setDEBTOR_NAME(init.getDebtor_name());
				transaction.setDEBTOR_ACCOUNT_NO(init.getDebtor_account_no());
				transaction.setDEBTOR_ACCOUNT_TYPE(init.getDebtor_account_type());
				transaction.setDEBTOR_BANK_NAME(init.getDebtor_bank_name());
				transaction.setDEBTOR_IDENTIFICATION(init.getDebtor_identification());
				transaction.setDEBTOR_IDENTIFICATION_NO(init.getDebtor_identification_no());
				transaction.setBankName(init.getBank_name());
				transaction.setUMRN(init.getUmrn());
				transaction.setCREDITOR_ACCOUNT_NO(init.getCreditor_account_no());
				transaction.setUNTIL_CANCEL(init.getUntil_cancel());
				
				if(repo.save(transaction) != null) 
				{
					logger.info("Umrn added in database");
					flag = true;
				}
				else 
				{
					logger.info("Unable to add data in database");
					flag = false;
				}
				
			}
			else
			{
				flag = false;
			}
			
		}
		catch(Exception e)
		{
			flag = false;
			logger.error("Exception occured while adding umrn in transaction table:"+e);
		}
		return flag;
	}

}
