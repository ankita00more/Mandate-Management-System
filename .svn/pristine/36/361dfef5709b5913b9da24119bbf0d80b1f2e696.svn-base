package com.example.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.Dao.MandateMasterImdh2hRepository;
import com.example.demo.Dao.ReasonCodeRepositoryDest;
import com.example.demo.Service.AcctImagesService;
import com.example.demo.Service.CustDetailsService;
import com.example.demo.Service.mdtAuthService;
import com.example.demo.entity.MandateAuthDto;
import com.example.demo.entity.User;
import com.example.demo.entity.cbsAuthDto;
import com.example.demo.entity.mandate_master_imd_h2h;
import com.example.demo.entity.mdtBean;
import com.example.demo.utilities.SessionChecker;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CustDetailsController {

	private final Logger logger = Logger.getLogger(CustDetailsController.class);

	@Autowired
	CustDetailsService CustService;

	@Autowired
	private mdtAuthService mdtAuthService;

	@Autowired
	AcctImagesService AcctImagesSer;

	@Autowired
	MandateMasterImdh2hRepository MasterRepo;

	@Autowired
	ReasonCodeRepositoryDest rsncodesRepo;

	@Autowired
	SessionChecker sessionChecker;

	@Value("${WAR_NAME}")
	String WAR_NAME;

	public String accept_mdt() {
		return WAR_NAME;
	}

	List<cbsAuthDto> cbsDto = null;

	@GetMapping("/viewDetail")
	public String Details(@RequestParam(name = "typeImg", required = false) String imageType,
			@RequestParam(name = "umrn", required = false) String umrn,
			@RequestParam(name = "MDT_DETAIL_FRNT_IMG_NAME", required = false) String detailFrontImageName,
			@RequestParam(name = "MDT_FRNT_IMG_NAME", required = false) String frontImageName,
			@RequestParam(name = "DEBITOR_ACC_NUM", required = false) String debitorAccountNumber, HttpSession session,
			Model model, HttpServletResponse response, mandate_master_imd_h2h mandatedata)
			throws SQLException, ParseException, IOException {

		if (sessionChecker.isSessionExpire(session)) {
			return "redirect:/";
		} else {
			imageType = getSessionAttribute(session, imageType, "imageType");
			umrn = getSessionAttribute(session, umrn, "umrn");
			detailFrontImageName = getSessionAttribute(session, detailFrontImageName, "MDT_DETAIL_FRNT_IMG_NAME");
			frontImageName = getSessionAttribute(session, frontImageName, "MDT_FRNT_IMG_NAME");
			debitorAccountNumber = getSessionAttribute(session, debitorAccountNumber, "DEBITOR_ACC_NUM");

			User attribute = (User) session.getAttribute("user");
			String isString_live = attribute.getISSTRING_LIVE();
			String bankname = attribute.getBankname();
			logger.info("is string live: " + isString_live + " bank: " + bankname);
			String zipName = ((String) session.getAttribute("zipfilename"));

			mandatedata.setUMRN(umrn);
			mandatedata.setMDT_ZIP_FILE_NAME(zipName);
			mandatedata.setBANK_NAME(bankname);
			List<Object[]> STATUS_RSN_CODE = rsncodesRepo.findDistinctStatusRsnCodes();

			if (imageType.equalsIgnoreCase("jpeg") || imageType.equalsIgnoreCase("tiff")) {
				model.addAttribute("STATUS_RSN_CODE", STATUS_RSN_CODE);

				if (isString_live.trim().equals("Y")) {
					cbsDto = CustService.getCbsDatabyString(debitorAccountNumber, bankname);
				} else {
					cbsDto = CustService.viewCbsDetrails(debitorAccountNumber);
				}
				model.addAttribute("cbsdata", cbsDto);

				MandateAuthDto authDto = CustService.viewDetailByUMRN(umrn);
				model.addAttribute("authDto", authDto);

				Blob JPEG = CustService.getJPEG_file(umrn, detailFrontImageName, zipName, bankname, imageType);
				if (JPEG != null) {
					try (InputStream inputStream = JPEG.getBinaryStream()) {
						if (inputStream != null) {
							ByteArrayOutputStream buffer = new ByteArrayOutputStream();
							int nRead;
							byte[] data = new byte[1024];
							while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
								buffer.write(data, 0, nRead);
							}
							buffer.flush();
							byte[] bytes = buffer.toByteArray();
							String base64Image = Base64.getEncoder().encodeToString(bytes);
							model.addAttribute("JPEG", base64Image);
						} else {
							logger.info("JPEG Image is null.");
						}
					} catch (IOException e) {
						logger.info("Error while gettin JPEG Image " + e.getMessage());
						e.printStackTrace();
					}
				}

				Blob TIFF = CustService.getTIFF_file(umrn, frontImageName, zipName, bankname, imageType);
				if (TIFF != null) {
					try (InputStream inputStream = TIFF.getBinaryStream()) {
						if (inputStream != null) {
							ByteArrayOutputStream buffer = new ByteArrayOutputStream();
							int nRead;
							byte[] data = new byte[1024];
							while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
								buffer.write(data, 0, nRead);
							}
							buffer.flush();
							byte[] bytes = buffer.toByteArray();
							String base64Image = Base64.getEncoder().encodeToString(bytes);
							model.addAttribute("TIFF", base64Image);

						} else {
							logger.error("TIFF Image is empty inputStream is null.");
						}
					} catch (IOException e) {
						logger.error("Error while gettin TIFF Image  " + e.getMessage());
						e.printStackTrace();
					}
				} else {
					logger.error("TIFF Image is empty or image is not TIFF.");
				}

				List<Object[]> cbSdata1 = AcctImagesSer.getCBSdata1(debitorAccountNumber);
				try {
					if (cbSdata1.size() > 0 && cbSdata1.get(0) != null) {
						for (int i = 0; i < Math.min(3, cbSdata1.size()); i++) {
							if (cbSdata1.get(i) != null) {
								Blob blob = (Blob) cbSdata1.get(i)[0];
								InputStream inputStream = blob.getBinaryStream();
								if (inputStream != null) {
									ByteArrayOutputStream buffer = new ByteArrayOutputStream();
									int nRead;
									byte[] data = new byte[1024];
									while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
										buffer.write(data, 0, nRead);
									}
									buffer.flush();
									byte[] bytes = buffer.toByteArray();
									logger.info("Signature data is not null");
									String cbsdataimg = Base64.getEncoder().encodeToString(bytes);
									model.addAttribute("cbsdataimg" + i, cbsdataimg);
								}
							}
						}
					} else {
						logger.error("Customer Signature not present.");
					}
				} catch (SQLException e) {
					logger.error("Got error while getting Customer Signature:---->  " + e);
					e.printStackTrace();
				}
			}
			return "viewDetails";
		}
	}

	private String getSessionAttribute(HttpSession session, String param, String sessionAttrName) {
		if (param != null) {
			session.setAttribute(sessionAttrName, param);
			return param;
		} else {
			return (String) session.getAttribute(sessionAttrName);
		}
	}

	@PostMapping("/process_M")
	public String processMandate(@RequestBody String data, HttpSession session, HttpServletRequest request, Model model,
			RedirectAttributes redirect) throws IOException {
		if (sessionChecker.isSessionExpire(session)) {
			return "redirect:/";
		} else {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> jsonData = mapper.readValue(data, new TypeReference<Map<String, String>>() {
			});

			try {
				logger.debug("Accept or Reject button clicked for authorization");
				String umrn = jsonData.get("umrn");
				String accept = jsonData.get("accept");
				String reject = jsonData.get("reject");
				String rsnCode = jsonData.get("rsnCode");
				model.addAttribute("accept_chk", accept);
				model.addAttribute("reject_chk", reject);
				model.addAttribute("STATUS_RSN_CODE", rsnCode);

				String status = "";
				int isReject = "check".equals(reject) ? 1 : 0;
				if ("check".equals(reject)) {
					isReject = 1;
					status = "R";
				}
				int isAccept = "check".equals(accept) ? 1 : 0;
				if ("check".equals(accept)) {
					isAccept = 1;
					status = "A";
				}
				boolean flag = CustService.acceptMandate(isReject, isAccept, rsnCode, status, umrn);
				if (flag) {
					logger.info(
							"Updated mandate_master_imd_h2h for isreject, isaccept, status_rsn_code, status - Is successful...");
				} else {
					logger.info(
							"Failed to update mandate_master_imd_h2h for isreject, isaccept, status_rsn_code, status...");
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("Error Occured While Accept or Reject button clicked for authorization " + e);
			}
			return "MDT_auth_DATA";
		}
	}

	@PostMapping("/back_mndt")
	public String accept(Model model, HttpSession session, @RequestParam("zipfilename") String zipfilename) {
		if (sessionChecker.isSessionExpire(session)) {
			return "redirect:/";
		} else {
			try {
				User attribute = (User) session.getAttribute("user");
				String bankname = attribute.getBankname();
				mdtBean mdt1 = new mdtBean();
				mdt1.setBANK_NAME(bankname);
				model.addAttribute("datalist", mdtAuthService.fetchDataForZipFileName(zipfilename, bankname));
				model.addAttribute("rsncodes", rsncodesRepo.findDistinctStatusRsnCodes());
				model.addAttribute("zipfilename", zipfilename);
			} catch (Exception e) {
				logger.error("error occured when back button clicked" + e);
				e.printStackTrace();
			}

			return "MDT_auth_DATA";
		}
	}

}