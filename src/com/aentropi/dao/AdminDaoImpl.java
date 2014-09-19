package com.aentropi.dao;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import com.aentropi.entity.AdminBean;
import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class AdminDaoImpl extends SqlMapClientDaoSupport implements AdminDao{

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public AdminBean login(String username, String password) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("username", username);
		map.put("password", password);
		return (AdminBean)getSqlMapClientTemplate().queryForObject("ADMIN.selectAdmin", map);
	}

	@Override
	public AdminBean getAdminByUsername(String username) {
		return (AdminBean)getSqlMapClientTemplate().queryForObject("ADMIN.selectAdminByUsername", username);
	}
	
	

}
