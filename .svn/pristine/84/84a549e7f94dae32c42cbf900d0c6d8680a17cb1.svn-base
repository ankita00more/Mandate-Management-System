package com.example.demo.Service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.example.demo.Dao.MandateMasterImdh2hRepository;
import com.example.demo.Dao.UpdatemasterRepo;
import com.example.demo.Dao.AcctImagesRepository;
import com.example.demo.Dao.BankDetailsAchRepository;
import com.example.demo.entity.MandateAuthDto;
import com.example.demo.entity.cbsAuthDto;
import com.example.demo.entity.mandate_master_imd_h2h;
import oracle.jdbc.OracleTypes;
import java.io.DataInputStream;

@Service
public class CustDetailsService {

	private static final Logger logger = Logger.getLogger(CustDetailsService.class);
	
	@Autowired
	BankDetailsAchRepository bankach;

	private final MandateMasterImdh2hRepository mdtRepo;
	private final AcctImagesRepository cbsRepo;
	private final UpdatemasterRepo mdtRepod;
	private final JdbcTemplate jdbcTemplate;

//	@Autowired
	public CustDetailsService(MandateMasterImdh2hRepository mdtRepo, AcctImagesRepository cbsRepo,
			UpdatemasterRepo mdtRepod, JdbcTemplate jdbcTemplate) {
		this.mdtRepo = mdtRepo;
		this.cbsRepo = cbsRepo;
		this.mdtRepod = mdtRepod;
		this.jdbcTemplate = jdbcTemplate;
	}

	public mandate_master_imd_h2h getMandateDetails(String umrn, String detailFrontImageName, String frontImageName,
			String debitorAccountNumber) {
		return null;
	}

	public Blob getJPEG_file(String umrn, String filename, String zipFileName, String bankName, String ftype)
			throws SQLException {
		Blob docBlob = null;
		List<Object[]> rs = mdtRepo.getJPEGDocuments(bankName, zipFileName, filename);
		if (rs != null && rs.size() > 0 && rs.get(0) != null) {
			for (Object[] result : rs) {
				docBlob = (Blob) result[2];
			}
		} else {
			logger.info("JPEG Blob is empty or null. Skipping...");
		}
		return docBlob;
	}

	public Blob getTIFF_file(String umrn, String filename, String zipFileName, String bankName, String ftype)
			throws SQLException {
		Blob docBlob = null;
		List<Object[]> rs = mdtRepo.getTIFFDocuments(bankName, zipFileName, filename);
		if (rs != null && !rs.isEmpty()) {
			for (Object[] result : rs) {
				docBlob = (Blob) result[2];
			}
		} else {
			logger.info("TIFF Blob is empty or null. Skipping...");
		}
		return docBlob;
	}

	public MandateAuthDto viewDetailByUMRN(String UMRN) throws ParseException {
		List<Object[]> viewDetailByUMRN = mdtRepo.viewDetailByUMRN(UMRN);
		MandateAuthDto dto = new MandateAuthDto();
		if (viewDetailByUMRN != null) {
			Object[] row = viewDetailByUMRN.get(0);

			dto.setUMRN(row[0].toString());
			dto.setMessageId(row[1].toString());
			dto.setPaymentType(row[2].toString());
			dto.setCurrency("RUPEE");
			dto.setFixedAmt(" ");
			dto.setMaxAmt(row[4].toString());
			dto.setFrequency(row[6].toString());
			dto.setStartDate(row[7].toString());
			if (row[8] != null) {
				dto.setEndDate(row[8].toString());
			} else {
				dto.setEndDate(null);
			}
			dto.setValidDate(row[9].toString());
			dto.setDebetorAcName(row[9].toString());
			dto.setCategory(row[5].toString());
			if (row[10] != null) {
				dto.setMobile(row[10].toString());
			} else {
				dto.setMobile(null);
			}
			dto.setCreditorName(row[11].toString());
			dto.setCustName(row[9].toString());
			if (row[12] != null) {
				dto.setCustEmail(row[12].toString());
			} else {
				dto.setCustEmail(null);
			}

			dto.setGifImg(" ");
			dto.setDebitorAcNum(row[13].toString());
		}

		return dto;
	}

	public List<cbsAuthDto> viewCbsDetrails(String debtorAccNum) throws ParseException {
		List<Object[]> viewCbsDetrail = cbsRepo.getCBSData(debtorAccNum);
		cbsAuthDto dto = new cbsAuthDto();
		if (viewCbsDetrail != null && !viewCbsDetrail.isEmpty()) {
			Object[] row = viewCbsDetrail.get(0);
			dto.setACCT_NO(row[0].toString());
			dto.setACCOUNT_NAME(row[3] != null ? row[3].toString() : "");
			dto.setACCT_STATUS(row[4] != null ? row[4].toString() : "");
			dto.setACCT_TYPE(row[5] != null ? row[5].toString() : "");
			dto.setMOP(row[6] != null ? row[6].toString() : "");
		}
		List<cbsAuthDto> list = new ArrayList<cbsAuthDto>();
		list.add(dto);
		return list;
	}

//new code - Ankita
	public List<cbsAuthDto> getCbsDatabyString(String debtorAccNum, String bankname) throws IOException {
		cbsAuthDto dto = new cbsAuthDto();
		String response = "";
		String Accnumber = debtorAccNum;
		String IP = "";
		String Port = "";

		int len = Accnumber.length();
		if (Accnumber.length() < 17) {
			for (int i = 0; i < 17 - len; i++) {
				Accnumber = "0" + Accnumber;
			}
		}
		List<String> getCbsServer = bankach.getCbsDetails(bankname);
		if (!getCbsServer.isEmpty()) {
			String x = getCbsServer.get(0);
			String[] getCbsServerArray = x.split(",");
			if (getCbsServerArray.length >= 1) {
				IP = getCbsServerArray[0];
				Port = getCbsServerArray[1];
				logger.info("CbsIp: " + IP + " CbsPort: " + Integer.parseInt(Port));
			}
		}

		String cbsRequest = " 0145                    **  0000      003999330039902008069029000000000     0         00000000        000000000000000000000000000000"
				+ Accnumber + " ";

		response = requesttoCBS(IP, Integer.parseInt(Port), cbsRequest, 17000);

		if (response != null && !response.equals("") && !response.isEmpty()) {
			dto.setACCT_NO(debtorAccNum);
			String acc_name = response.substring(232, 292).trim();
			acc_name = acc_name.replace(".", "");

			String account_name = acc_name.replaceAll("\\s{2,}", " ").trim();
			dto.setACCOUNT_NAME(account_name);
			dto.setACCT_STATUS(response.substring(313, 331).trim());
			dto.setACCT_TYPE(response.substring(389, 419).trim());
			dto.setMOP(response.substring(292, 312).trim());

		} else {
			dto.setACCOUNT_NAME("");
			dto.setACCT_STATUS("");
			dto.setACCT_TYPE("");
			dto.setMOP("");
		}

		List<cbsAuthDto> list = new ArrayList<cbsAuthDto>();
		list.add(dto);
		return list;
	}

	public static String requesttoCBS(String serverIp, int serverPort, String requestData, int timeOut)
			throws IOException {
		String ResponseString = "";
		Socket clientSocket = null;
		DataOutputStream outToServer = null;
		InputStream is = null;
		DataInputStream dis = null;
		try {
			InetAddress address = InetAddress.getByName(serverIp);
			clientSocket = new Socket(address, serverPort);
			clientSocket.setSoTimeout(timeOut);
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			String requestSend = requestData;
			logger.info("Request to CBS =" + requestSend);
			outToServer.writeBytes(requestSend);
			outToServer.flush();
			is = clientSocket.getInputStream();
			byte[] b = new byte[5];
			byte[] b1 = null;
			dis = new DataInputStream(is);
			try {
				dis.read(b, 0, b.length);
				ResponseString = new String(b);
				int len = Integer.parseInt(ResponseString.trim());
				b1 = new byte[len];
				dis.readFully(b1, 0, b1.length);

			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Timeout" + e.getMessage());
			}
			ResponseString += new String(b1);
			logger.info("response: " + ResponseString);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			clientSocket.close();
		}
		return ResponseString;
	}

	// end new code - Ankita

	public boolean acceptMandate(int isReject, int isAccept, String statusRsnCode, String status, String umrn) {
		// boolean trnSts = false;

		int updateMandateResult = mdtRepod.updateMandate(isReject, isAccept, statusRsnCode, status, umrn);
		logger.info("Flag updated: " + updateMandateResult);
		return updateMandateResult > 0;

	}

	public String deleteMdt(String umrn) {
		int cancelMandateByUmrn = mdtRepo.cancelMandateByUmrn(umrn);
		String status = "";
		if (cancelMandateByUmrn > 0) {
			status = "SUCCESS";
		} else {
			status = "FAIL";
		}
		return status;
	}

	public List<Object[]> GetMdtDocH2h(String umrn, String fileName, String zipFileName, String fileType,
			String bankName) {
		String sql = "begin ? := MDT_MGMNT_H2H.GET_MDT_DOC_H2H(?,?,?,?,?); end;";
		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall(sql);
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			callableStatement.setString(2, umrn);
			callableStatement.setString(3, fileName);
			callableStatement.setString(4, zipFileName);
			callableStatement.setString(5, "JPEG");
			callableStatement.setString(6, bankName);

			callableStatement.execute();
			resultSet = (ResultSet) callableStatement.getObject(1);
			System.out.println("inside procedure" + resultSet);
			List<Object[]> resultList = new ArrayList<>();
			while (resultSet.next()) {
				Object[] row = new Object[] { resultSet.getString("umrn"), resultSet.getString("name"),
						resultSet.getObject("doc") };
				resultList.add(row);
			}

			return resultList;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (callableStatement != null) {
					callableStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
