package com.aentropi.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aentropi.entity.AdminBean;

public class SecurityContext {
	private HttpServletRequest httpRequest;
	private HttpSession httpSession;
	private HttpServletResponse httpResponse;
	private AdminBean admin;
	
	/**
	 * @return the httpRequest
	 */
	public HttpServletRequest getHttpRequest() {
		return httpRequest;
	}

	/**
	 * @param httpRequest
	 *            the httpRequest to set
	 */
	public void setHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	/**
	 * @return the httpServletResponse
	 */
	public HttpServletResponse getHttpResponse() {
		return httpResponse;
	}

	/**
	 * @param httpServletResponse
	 *            the httpServletResponse to set
	 */
	public void setHttpResponse(HttpServletResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	/**
	 * @return the httpSession
	 */
	public HttpSession getHttpSession() {
		return httpSession;
	}

	/**
	 * @param httpSession
	 *            the httpSession to set
	 */
	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	/**
	 * @return the admin
	 */
	public AdminBean getAdmin() {
		return admin;
	}

	/**
	 * @param admin
	 *            the admin to set
	 */
	public void setAdmin(AdminBean admin) {
		if (httpSession != null && admin != null) {
			httpSession.setAttribute("admin", admin.getUsername());
		} else if (admin == null) {
			httpSession.setAttribute("admin", null);
		}
		this.admin = admin;
	}
	
}
