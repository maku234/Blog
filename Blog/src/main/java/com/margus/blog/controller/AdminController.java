package com.margus.blog.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.margus.blog.domain.BlogPost;
import com.margus.blog.domain.Tag;
import com.margus.blog.service.BlogService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private BlogService blogService;
	
	private final int POST_LENGHT = 200;
	private static final Logger logger = Logger.getLogger(AdminController.class);
	
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
		model.addAttribute("taglist", "");
		return "addNewPost";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPostFromForm(
			@ModelAttribute("post") @Validated BlogPost post,
			BindingResult bindingResult, Model model,@RequestParam("tags") String tags) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("taglist", tags);
			return "addNewPost";
		}
		

		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		post.setDate(new Timestamp(now.getTime()));
		
		
		
		
		
		
		post.setTags(getTagListFromString(tags));
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
			model.addAttribute("taglist", post.getStringOfTagNames());
		}
		return "editPost";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String editPostFromForm(@PathVariable("id") int id,@ModelAttribute("post") @Validated BlogPost post,
			BindingResult bindingResult,@RequestParam("tags") String tags) {
		if (bindingResult.hasErrors()) {

			return "editPost";
		}
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		post.setDate(new Timestamp(now.getTime()));
		post.setTags(getTagListFromString(tags));
		blogService.updatePost(post);

		return "redirect:/admin/";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deletePost(@PathVariable("id") int id){
		
		blogService.deletePost(id);
		return "redirect:/admin/";
		
		
	}
	
	@RequestMapping(value = "/edit/{post_id}/delete/{commend_id}", method = RequestMethod.GET)
	public String deleteComment(@PathVariable("post_id") int post_id,@PathVariable("commend_id") int commend_id){
		
		blogService.deleteComment(commend_id);
		return "redirect:/admin/edit/"+post_id;
		
		
	}
	
	@RequestMapping(value="/get_tags",method=RequestMethod.GET,headers ={"Accept=*/*"})
	public  @ResponseBody List<String> getTagsThatStartWith(@RequestParam("term") String start){
		
		List<String> returnList = new ArrayList<String>();
		List<Tag> tags = blogService.getTagsLike(start+"%");
		for (Tag tag : tags) {
			returnList.add(tag.getName());
		}
		return returnList;
	}
	
	/**
	 * Return list of tags from string of tag names, adds new tags to database
	 * @param tagNames
	 * @return
	 */
	private List<Tag> getTagListFromString(String tagNames){
		String[] tag_list = tagNames.split(" ");
		List<Tag> tagList = new ArrayList<Tag>();
		Set<String> addedTags = new HashSet<String>();
		for (String tag_name : tag_list) {
			
			logger.info("tag name"+tag_name+"-");
			if(tag_name.equals("") || addedTags.contains(tag_name)){
				continue;
			}else{
				addedTags.add(tag_name);
			}
			Tag tag = blogService.getTagByName(tag_name);
			if(tag==null){
				tag = new Tag(tag_name);
				
				
				blogService.addTag(tag);
			}
			
			tagList.add(tag);
			
		}
		return tagList;
	}
	
	
}

