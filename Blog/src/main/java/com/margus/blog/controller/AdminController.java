package com.margus.blog.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.margus.blog.domain.BlogPost;
import com.margus.blog.service.BlogService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private BlogService blogService;
	
	private final int POST_LENGHT = 200;
	
	
	@Autowired
	public AdminController(BlogService blogService) {
		this.blogService = blogService;
	}
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String adminHome(Model model){
		model.addAttribute("posts", blogService.getAllPosts());
		model.addAttribute("max_lenght", POST_LENGHT);
		return "adminHome";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addPostFromForm(Model model) {

		model.addAttribute("post", new BlogPost());

		return "addNewPost";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPostFromForm(
			@ModelAttribute("post") @Validated BlogPost post,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {

			return "addNewPost";
		}
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		post.setDate(new Timestamp(now.getTime()));
		blogService.addPost(post);

		return "redirect:/admin/";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editPostFromForm(@PathVariable("id") int id,Model model) {

		BlogPost post = blogService.getPostById(id);
		if(post==null){
			return "redirect:/admin/";
		}else{
			model.addAttribute("post", post);
		}
		return "editPost";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String editPostFromForm(@PathVariable("id") int id,@ModelAttribute("post") @Validated BlogPost post,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {

			return "editPost";
		}
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		post.setDate(new Timestamp(now.getTime()));
		blogService.updatePost(post);

		return "redirect:/admin/";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deletePost(@PathVariable("id") int id){
		
		blogService.deletePost(id);
		return "redirect:/admin/";
		
		
	}
}

