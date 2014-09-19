package com.aentropi.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import com.aentropi.entity.NewsBean;
import com.ibatis.sqlmap.client.SqlMapClient;

@SuppressWarnings("unchecked")
@Component
public class NewsDaoImpl extends SqlMapClientDaoSupport implements NewsDao{
	
	
	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public List<NewsBean> getNews(int start, int end) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		return getSqlMapClientTemplate().queryForList("NEWS.selectNewsByPageIndexAndPageSize", map);
	}

	@Override
	public NewsBean getNewsById(int id) {
		return (NewsBean)getSqlMapClientTemplate().queryForObject("NEWS.selectNewsById", id);
	}


	@Override
	public void addNews(NewsBean news) {
		getSqlMapClientTemplate().insert("NEWS.insertNews", news);
		
	}

	@Override
	public void updateNews(NewsBean news) {
		getSqlMapClientTemplate().update("NEWS.updateNews", news);
	}

	@Override
	public void deleteNews(int id) {
		getSqlMapClientTemplate().delete("NEWS.deleteNews", id);
	}
	
	@Override
	public int countNews() {
		return (int)getSqlMapClientTemplate().queryForObject("NEWS.selectNewsCount");
	}

	@Override
	public List<NewsBean> getLatestNews(int k) {
		return getSqlMapClientTemplate().queryForList("NEWS.selectLastestNews", k);
	}
		
}
