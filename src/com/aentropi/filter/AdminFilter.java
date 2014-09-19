package com.aentropi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.aentropi.core.SystemContainer;
import com.aentropi.service.AdminService;
import com.aentropi.view.util.ViewUtil;

public class AdminFilter implements Filter {

	private static AdminService adminService = (AdminService) SystemContainer
			.lookup("adminService");

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();

		SecurityContext securityContext = SecurityContextHolder
				.getSecurityContext();

		String username = (String)session.getAttribute("admin");
		if (username != null) {
			securityContext.setAdmin(adminService.getAdminByUsername(username));
		} else {
			securityContext.getHttpResponse().sendRedirect(ViewUtil.getContextPath() + "/login");
		}

		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
