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
	public List<BlogPost> getPosts(int start,int count);
	public int getPostsCount();
	public void addComment(int postId,Comment comment);
	public void deleteComment(int id);
	public Comment getCommentById(int id);
	public void addTag(Tag tag);
	public void deleteTag(Tag tag);
	public Tag getTagByName(String name);
	public void updateTag(Tag tag);
	public List<Tag> getAllTags();
	public List<Tag> getTagsLike(String text);
	public List<Tag> getTags(int start,int count);
	public int getTagsCount();
}
