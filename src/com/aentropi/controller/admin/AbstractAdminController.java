package com.aentropi.controller.admin;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.aentropi.entity.AdminBean;
import com.aentropi.filter.SecurityContextHolder;

public class AbstractAdminController {

	protected void putAdmin(Model model, HttpServletResponse response) {
		AdminBean admin = SecurityContextHolder.getSecurityContext().getAdmin();
		if(admin != null) {
			model.addAttribute("admin", admin.getUsername());
		}
	}
}
