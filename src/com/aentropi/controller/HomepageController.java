package com.aentropi.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aentropi.service.HomepageService;


@Controller
public class HomepageController {
	
	@Autowired
	HomepageService hpService;
	
	@RequestMapping("/")
	public String DisplayHomepage(Model model, HttpServletResponse response) {
		//
		return "web/homepage";
	}
	
}
