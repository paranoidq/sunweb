package com.aentropi.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aentropi.entity.NewsBean;
import com.aentropi.service.HomepageService;
import com.aentropi.service.NewsService;


@Controller
public class HomepageController {
	
	public static final int NEWS_COUNT = 3;
	
	@Autowired
	HomepageService hpService;
	@Autowired
	NewsService newsService;
	
	@RequestMapping("/favicon.ico")
	public void favicon(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(
				request.getContextPath() + "/static/images/favicon.ico")
				.forward(request, response);
	}

	
	@RequestMapping("/")
	public String displayHomepage(Model model, HttpServletResponse response) {
		///
		List<NewsBean> list = newsService.getLatestNews(NEWS_COUNT);
		model.addAttribute("news", list);
		return "web/homepage";
	}
	
	
	@RequestMapping("/company")
	public String displayCompany(Model model, HttpServletResponse response) {
		model.addAttribute("sectionId", 1);
		return "web/company";
	}
	@RequestMapping("/company/{sectionId}")
	public String displayCompany(Model model, HttpServletResponse response, @PathVariable("sectionId") int sectionId) {
		model.addAttribute("sectionId", sectionId);
		return "web/company";
	}
	
	@RequestMapping("/contact")
	public String displayContact(Model model, HttpServletResponse response) {
		return "web/contact";
	}
	
	@RequestMapping("/products")
	public String displayProducts(Model model, HttpServletResponse response) {
		model.addAttribute("sectionId", 1);
		return "web/products";
	}
	
	@RequestMapping("/products/{sectionId}")
	public String displayProducts(Model model, HttpServletResponse response, @PathVariable("sectionId") int sectionId) {
		model.addAttribute("sectionId", sectionId);
		return "web/products";
	}
	
}
