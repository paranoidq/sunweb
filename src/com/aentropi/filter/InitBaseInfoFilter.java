package com.aentropi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitBaseInfoFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		httpRequest.setCharacterEncoding("UTF-8");
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		SecurityContext securityContext = new SecurityContext();
		securityContext.setHttpRequest(httpRequest);
		securityContext.setHttpResponse(httpResponse);
		securityContext.setHttpSession(httpRequest.getSession());
		SecurityContextHolder.setSecurityContext(securityContext);

		filterChain.doFilter(request, response);

		SecurityContextHolder.clearSecurityContext();
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
