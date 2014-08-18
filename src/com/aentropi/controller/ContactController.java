package com.aentropi.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aentropi.util.Constant;
import com.aentropi.util.MailIssue;


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
	@RequestMapping(value="/p_send_email", method=RequestMethod.POST)
	public void postEmail(Model model, HttpServletResponse response, 
			@RequestParam("fromAddress") String fromAddress, @RequestParam("content") String c) {
		String toAddress = Constant.mailAddress;
		StringBuilder title = new StringBuilder();
		title.append("【Aentropi】‘").append(fromAddress).append(" leave a message for you");

		StringBuilder content = new StringBuilder();
		content.append("<html>")
				.append("——来自：").append("<h3>").append(fromAddress).append("</h3>");
		content.append("<br/><p>内容：")
				.append(c)
				.append("</p>");
		content.append("<br/><p>他（她）的邮箱：").append(fromAddress)
				.append("</p></html>");
		
		try {
			MailIssue.send(fromAddress, c.toString(), toAddress);
			PrintWriter out = response.getWriter();
			out.write("1");
		} catch (Exception e) {
		}
	}
}
