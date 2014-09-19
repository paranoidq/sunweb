package com.aentropi.dao;

import com.aentropi.entity.AdminBean;


public interface AdminDao {

	public AdminBean login(String username, String password);
	
	public AdminBean getAdminByUsername(String username);
}
