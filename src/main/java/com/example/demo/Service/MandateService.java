package com.example.demo.Service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.MdtInitRequestH2h;


public interface MandateService {

	public void createMandate(HttpSession session,MdtInitRequestH2h customer, MultipartFile jpg_image, MultipartFile tif_image,Model model);
}
