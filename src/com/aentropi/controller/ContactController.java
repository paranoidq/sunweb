package com.aentropi.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ContactController {
	
	/**
	 * send email from front page
	 * @param model
	 * @param response
	 * @param name
	 * @param email
	 * @param content
	 * @return
	 */
	@RequestMapping(value="/aentropi/p_email", method=RequestMethod.POST)
	public String DisplayHomepage(Model model, HttpServletResponse response, 
			@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("content") String content) {
	
		
		return "web/homepage";
	}
}
