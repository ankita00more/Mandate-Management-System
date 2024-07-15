package com.example.demo.Dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.mdt_validity;


@Repository
public interface MdtValidityRepository extends JpaRepository<mdt_validity,String> 
{
	
	@Query(value = "select a.debtor_account_no,a.reason_count,a.reject_reason_1,"
			+ "(select rsn_desc from mdt_rsn_codes b where a.reject_reason_1 = b.status_rsn_code) as REJECT_REASON_1_DESC,"
			+ "a.reject_reason_2 as REJECT_REASON_2,(select rsn_desc from mdt_rsn_codes b where "
			+ "a.reject_reason_2 = b.status_rsn_code) as REJECT_REASON_2_DESC,a.date_of_mandate,a.creditor_utility_code,"
			+ "a.debtor_bank_name,a.amount from mdt_validity a where a.debtor_account_no = :debtor_account_no and "
			+ "a.date_of_mandate = to_date(:date_of_mandate,'DD-MM-YYYY') and a.creditor_utility_code = :creditor_utility_code"
			+ " and  a.debtor_bank_name = :debtor_bank_name and a.amount = :amount ",nativeQuery=true)
	public List<Object[]> getrejrsn(@Param("debtor_account_no") String debtor_account_no,@Param("date_of_mandate") String date_of_mandate,
			@Param("creditor_utility_code") String creditor_utility_code,@Param("debtor_bank_name") String debtor_bank_name,
			@Param("amount") String amount);
}
