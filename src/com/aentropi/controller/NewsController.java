package com.aentropi.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aentropi.entity.NewsBean;
import com.aentropi.service.NewsService;
import com.aentropi.util.PageUtil;

@Controller
public class NewsController {

	private static final int PAGE_SIZE = 10;
	
	@Autowired
	private NewsService newsService;
	
	
	@RequestMapping(value="/news", method=RequestMethod.GET) 
	public String getNews(Model model, HttpServletResponse response) {
		int pageIndex = 1;
		List<NewsBean> list = newsService.getNews(pageIndex, PAGE_SIZE);
		model.addAttribute("newsList", list);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("prePageIndex", pageIndex-1);
		model.addAttribute("nextPageIndex", pageIndex+1);
		model.addAttribute("pageCount", PageUtil.getPageCount(newsService.countNews(), PAGE_SIZE));
		return "web/news";
	}
	
	
	@RequestMapping(value="/news/p={pageIndex}", method=RequestMethod.GET) 
	public String getNews(@PathVariable("pageIndex") int index, Model model, HttpServletResponse response) {
		int pageIndex = index <= 0 ? 1 : index;
		List<NewsBean> list = newsService.getNews(pageIndex, PAGE_SIZE);
		model.addAttribute("newsList", list);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("prePageIndex", pageIndex-1);
		model.addAttribute("nextPageIndex", pageIndex+1);
		model.addAttribute("pageCount", PageUtil.getPageCount(newsService.countNews(), PAGE_SIZE));
		return "web/news";
	}
	
	@RequestMapping(value="/news/id={id}", method=RequestMethod.GET)
	public String getNewsById(@PathVariable("id") int id, Model model, HttpServletResponse response) {
		NewsBean news = newsService.getNewsById(id);
		model.addAttribute("news", news);
		return "web/news_detail";
	}
}
