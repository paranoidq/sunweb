package com.sunweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sunweb.service.HomepageService;


@Controller
public class HomepageController {
	
	@Autowired
	HomepageService hpService;
	
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String DisplayHomepage() {
		
		return "homepage";
	}
	
}
