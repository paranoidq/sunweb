package com.aentropi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aentropi.dao.NewsDao;
import com.aentropi.entity.NewsBean;


@Service
public class NewsService {
	
	@Autowired
	private NewsDao newsDaoImpl;
	
	
	public List<NewsBean> getLatestNews(int k) {
		return newsDaoImpl.getLatestNews(k);
	}
	
	public NewsBean getNewsById(int id) {
		return newsDaoImpl.getNewsById(id);
	}
	
	public List<NewsBean> getNews(int pageIndex, int pageSize) {
		return newsDaoImpl.getNews(pageSize * (pageIndex-1), pageSize);
	}
	
	public void addNews(NewsBean news) {
		newsDaoImpl.addNews(news);
	}
	
	public void deleteNews(int id) {
		newsDaoImpl.deleteNews(id);
	}
	
	public void updateNews(NewsBean news) {
		newsDaoImpl.updateNews(news);
	}
	
	public int countNews() {
		return newsDaoImpl.countNews();
	}
}
