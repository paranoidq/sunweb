package com.aentropi.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aentropi.entity.NewsBean;
import com.aentropi.service.NewsService;
import com.aentropi.util.PageUtil;


@Controller
public class AdminNewsController extends AbstractAdminController{
	
	private static final int PAGE_SIZE = 10;
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping(value="/admin/news", method=RequestMethod.GET) 
	public String getNewsDefault(Model model, HttpServletResponse response) {
		putAdmin(model, response);
		
		int pageIndex = 1;
		List<NewsBean> list = newsService.getNews(pageIndex, PAGE_SIZE);
		model.addAttribute("newsList", list);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("prePageIndex", pageIndex-1);
		model.addAttribute("nextPageIndex", pageIndex+1);
		model.addAttribute("pageCount", PageUtil.getPageCount(newsService.countNews(), PAGE_SIZE));
		return "admin/news_list";
	}
	
	
	@RequestMapping(value="/admin/news/p={pageIndex}", method=RequestMethod.GET) 
	public String getNews(@PathVariable("pageIndex") int index, Model model, HttpServletResponse response) {
		putAdmin(model, response);
		
		int pageIndex = index <= 0 ? 1 : index;
		List<NewsBean> list = newsService.getNews(pageIndex, PAGE_SIZE);
		model.addAttribute("newsList", list);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("prePageIndex", pageIndex-1);
		model.addAttribute("nextPageIndex", pageIndex+1);
		model.addAttribute("pageCount", PageUtil.getPageCount(newsService.countNews(), PAGE_SIZE));
		return "admin/news_list";
	}
	
	@RequestMapping(value="/admin/news/id={id}", method=RequestMethod.GET)
	public String getNewsById(@PathVariable("id") int id, Model model, HttpServletResponse response) {
		putAdmin(model, response);
		
		NewsBean news = newsService.getNewsById(id);
		model.addAttribute("news", news);
		return "admin/news_detail";
	}
	
	// add
	@RequestMapping(value="/admin/addnews", method=RequestMethod.GET)
	public String getAddNews(Model model, HttpServletResponse response) {
		putAdmin(model, response);
		
		return "admin/news_add";
	}
	
	@RequestMapping(value="/admin/addnews", method=RequestMethod.POST)
	public void postAddNews(@RequestParam("title") String title, @RequestParam("content") String content, 
			Model model, HttpServletResponse response) {
		NewsBean news = new NewsBean();
		news.setTitle(title);
		news.setContent(content);
		news.setTimestamp(System.currentTimeMillis());
		newsService.addNews(news);
	}
	
	// update
	@RequestMapping(value="/admin/updatenews/id={id}", method=RequestMethod.GET)
	public String getUpdateNews(@PathVariable("id")int id, Model model, HttpServletResponse response) {
		putAdmin(model, response);
		
		NewsBean news = newsService.getNewsById(id);
		model.addAttribute("news", news);
		return "admin/news_update";
	}
	
	@RequestMapping(value="/admin/updatenews", method=RequestMethod.POST)
	public void postUpdateNews(@RequestParam("id") int id, @RequestParam("title") String title, @RequestParam("content") String content, 
			Model model, HttpServletResponse response) {
		NewsBean news = new NewsBean();
		news.setId(id);
		news.setTitle(title);
		news.setContent(content);
		newsService.updateNews(news);
	}
	
	
	//  delete
	@RequestMapping(value="/admin/deletenews", method=RequestMethod.POST)
	public void postDeleteNews(@RequestParam("id")int id, Model model, HttpServletResponse response) {
		newsService.deleteNews(id);
	}
	
}
