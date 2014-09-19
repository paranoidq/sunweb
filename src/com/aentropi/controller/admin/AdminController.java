package com.aentropi.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aentropi.entity.AdminBean;
import com.aentropi.filter.SecurityContextHolder;
import com.aentropi.service.AdminService;
import com.aentropi.view.util.ViewUtil;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model, HttpServletResponse response) {
		return "admin/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public void login(@RequestParam("username") String username, @RequestParam("password") String password, 
			Model model, HttpServletResponse response) {
		AdminBean admin = adminService.login(username, password);
		if (admin != null) {
			SecurityContextHolder.getSecurityContext().setAdmin(admin);
		}
		String to = ViewUtil.getContextPath() + "/login";
		if (admin != null) {
			to = ViewUtil.getContextPath() + "/admin/news";
		}
		try {
			response.sendRedirect(to);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/logout")
	public void logoff(Model model, HttpServletResponse response) {
		SecurityContextHolder.getSecurityContext().setAdmin(null);
		try {
			response.sendRedirect(ViewUtil.getContextPath() + "/login");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
