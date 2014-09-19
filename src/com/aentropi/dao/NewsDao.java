package com.aentropi.dao;

import java.util.List;

import com.aentropi.entity.NewsBean;


public interface NewsDao {
	
	// for visitors
	List<NewsBean> getNews(int start, int end);
	
	NewsBean getNewsById(int id);	
	
	List<NewsBean> getLatestNews(int k);
	
	// for admin
	void addNews(NewsBean news);
	
	void updateNews(NewsBean news);
	
	void deleteNews(int id);
	
	int countNews();
}
