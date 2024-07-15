package com.example.demo.Service;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Dao.AcctImagesRepository;

@Service
public class AcctImagesService {

	private final Logger logger = Logger.getLogger(AcctImagesService.class);

	@Autowired
	private AcctImagesRepository AcctImagesRepo;

	public List<Object[]> getCBSdata(String debtorAccNum) {
		try {
			List<Object[]> acctImagesList = AcctImagesRepo.getCBSData(debtorAccNum);
			if (acctImagesList == null) {
				logger.error("CBS Data is Empty in AcctImage Table");
			}
			return acctImagesList;
		} catch (NumberFormatException | NullPointerException ex) {
			logger.error("Error parsing debtorAccNum: " + ex.getMessage());
			return null;
		}
	}

	public List<Object[]> getCBSdata1(String debtorAccNum) throws SQLException {

		List<Object[]> imageObjects = AcctImagesRepo.getCBSdata1(debtorAccNum);
		if (imageObjects == null) {
			logger.error("CBS Image is Empty in AcctImage Table");
		}
		return imageObjects;
	}
}
