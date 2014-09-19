package com.aentropi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aentropi.dao.AdminDao;
import com.aentropi.entity.AdminBean;


@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDaoImpl;
	
	public AdminBean login(String username, String password) {
		return adminDaoImpl.login(username, password);
	}
	
	public AdminBean getAdminByUsername(String username) {
		return adminDaoImpl.getAdminByUsername(username);
	}
	
}
