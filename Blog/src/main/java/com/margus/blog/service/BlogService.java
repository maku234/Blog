package com.margus.blog.service;

import java.util.List;

import com.margus.blog.domain.BlogPost;

public interface BlogService {

	public void addPost(BlogPost post);
	public BlogPost getPostById(int id);
	public void deletePost(BlogPost post);
	public void deletePost(int id);
	public void updatePost(BlogPost post);
	public List<BlogPost> getRecentPosts(int count);
	public List<BlogPost> getAllPosts();
}
