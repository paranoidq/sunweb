package com.aentropi.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aentropi.service.HomepageService;


@Controller
public class HomepageController {
	
	@Autowired
	HomepageService hpService;
	
	@RequestMapping("/")
	public String displayHomepage(Model model, HttpServletResponse response) {
		///
		return "web/homepage";
	}
	
	@RequestMapping("/company")
	public String displayCompany(Model model, HttpServletResponse response) {
		return "web/company";
	}
	
	@RequestMapping("/contact")
	public String displayContact(Model model, HttpServletResponse response) {
		return "web/contact";
	}
	
	@RequestMapping("/products")
	public String displayProducts(Model model, HttpServletResponse response) {
		model.addAttribute("sectionId", 0);
		return "web/products";
	}
	
	@RequestMapping("/products/{sectionId}")
	public String displayProducts(Model model, HttpServletResponse response, @PathVariable("sectionId") int sectionId) {
		model.addAttribute("sectionId", sectionId);
		return "web/products";
	}
	
	@RequestMapping("/news")
	public String displayNews(Model model, HttpServletResponse response) {
		return "web/news";
	}
	
}
