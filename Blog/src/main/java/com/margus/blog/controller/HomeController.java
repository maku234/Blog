package com.margus.blog.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.margus.blog.domain.BlogPost;
import com.margus.blog.domain.Comment;
import com.margus.blog.domain.Tag;
import com.margus.blog.service.BlogService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class HomeController {
	
	private final int RECENT_POSTS = 10;
	private final int POST_LENGHT = 300;


	private BlogService blogService;

	@Autowired
	public HomeController(BlogService blogService) {
		this.blogService = blogService;
	}

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		

		
		model.addAttribute("posts", blogService.getRecentPosts(RECENT_POSTS));
		model.addAttribute("max_lenght", POST_LENGHT);
		
		return "home";
	}
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String viewPost(@PathVariable("id") int id,Model model) {

		BlogPost post = blogService.getPostById(id);
		if(post == null){
			return "redirect:/";
		}else{
			model.addAttribute("post", post);
			model.addAttribute("comment", new Comment());
		}

		
		return "viewPost";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.POST)
	public String addCommentToPost(@PathVariable("id") int id,Model model,@ModelAttribute("comment") @Validated Comment comment,BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return "viewPost";
		}
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		comment.setDate(new Timestamp(now.getTime()));
		blogService.addComment(id, comment);
		BlogPost post = blogService.getPostById(id);
		if(post == null){
			return "redirect:/";
		}else{
			model.addAttribute("post", post);
			model.addAttribute("comment", new Comment());
		}

		return "viewPost";
	}

	@RequestMapping(value = "/tag/{tagName}", method = RequestMethod.GET)
	public String viewTagPosts(@PathVariable("tagName") String tagName,Model model) {

		Tag tag = blogService.getTagByName(tagName);
		if(tag == null){
			return "redirect:/";
		}else{
			model.addAttribute("tagName", tagName);
			model.addAttribute("posts", tag.getPosts());
			model.addAttribute("max_lenght", POST_LENGHT);
			
			
		}
		return "viewPostsByTag";
	}
	
}
