package com.zhiqu.ctrl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhiqu.dto.ListResult;
import com.zhiqu.dto.Pagination;
import com.zhiqu.model.News;
import com.zhiqu.service.NewsService;

@Controller
@RequestMapping("/")
public class NewsCtrl {

	@Autowired
	private NewsService newsService;
	
	@RequestMapping(value = "/newsIndex")
	public ModelAndView toIndex(){
		return new ModelAndView("news/newsIndex");
	}
	@RequestMapping(value = "/newsList")
	public String loadList(Pagination pagination, ModelMap model,
			HttpServletRequest request) {

		ListResult listResult = newsService.getNews(pagination);
		@SuppressWarnings("unchecked")
		List<News> newsList = listResult.getResult();
		
		model.addAttribute("newsList", newsList);

		return "news/newsList";
	}
	@RequestMapping(value="newsDetail/{nid}")
	public String loadModify(@PathVariable Integer nid, ModelMap model,
			HttpServletRequest request) {
		
		News news = newsService.findById(nid);
		model.addAttribute("news", news);
		return "news/newsDetail";
	}
}
