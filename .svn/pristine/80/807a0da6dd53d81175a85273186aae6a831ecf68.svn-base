package com.example.demo.Service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.Dao.MandateMasterImdh2hRepository;
import com.example.demo.Dao.BankDetailsAchRepository;
import com.example.demo.entity.mandate_master_imd_h2h;

@Service
public class mdtAuthService {
	private static final Logger logger = Logger.getLogger(mdtAuthService.class);

	@Autowired
	private static MandateMasterImdh2hRepository mdtRepo;

	@Autowired
	BankDetailsAchRepository bankDetailsAchRepo;

	@Autowired
	public mdtAuthService(MandateMasterImdh2hRepository repository) {
		super();
		this.mdtRepo = repository;
	}

	public List<Object[]> fetchDataForZipFileName(@RequestParam("zipfilename") String zipfilename, String bankname) {
		List<Object[]> list1 = mdtRepo.findMandatesByFilename(zipfilename, bankname);
		logger.info("List of extracted data by zipfilename" + list1.size());
		return list1;
	}

	public static mandate_master_imd_h2h findByUMRN(@RequestParam("umrn") String umrn) {

		mandate_master_imd_h2h findByUMRN = mdtRepo.findByUMRN(umrn);
		logger.info("findByUMRN -->" + findByUMRN);
		return findByUMRN;
	}

}
