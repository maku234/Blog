package com.margus.blog.service;

import java.util.List;

import com.margus.blog.domain.BlogPost;
import com.margus.blog.domain.Comment;
import com.margus.blog.domain.Tag;

public interface BlogService {

	public void addPost(BlogPost post);
	public BlogPost getPostById(int id);
	public void deletePost(BlogPost post);
	public void deletePost(int id);
	public void updatePost(BlogPost post);
	public List<BlogPost> getRecentPosts(int count);
	public List<BlogPost> getAllPosts();
	public void addComment(int postId,Comment comment);
	public void deleteComment(int id);
	public Comment getCommentById(int id);
	public void addTag(Tag tag);
	public Tag getTagByName(String name);
	public void updateTag(Tag tag);
	
}
