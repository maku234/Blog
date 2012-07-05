package com.margus.blog.dao;

import java.util.List;

import com.margus.blog.domain.BlogPost;

public interface BlogDAO {

	public void addPost(BlogPost post);
	public BlogPost getPostById(int id);
	public void deletePost(BlogPost post);
	public void updatePost(BlogPost post);
	public List<BlogPost> getRecentPosts(int count);
	public List<BlogPost> getAllPosts();
}
