package com.margus.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.margus.blog.dao.BlogDAO;
import com.margus.blog.domain.BlogPost;
import com.margus.blog.domain.Comment;

@Service
@Repository
@Transactional
public class BlogServiceImpl implements BlogService {

	private BlogDAO blogDAO;

	@Autowired
	public BlogServiceImpl(BlogDAO blogDAO) {
		this.blogDAO = blogDAO;
	}

	public void addPost(BlogPost post) {
		blogDAO.addPost(post);

	}

	public BlogPost getPostById(int id) {
		return blogDAO.getPostById(id);
	}

	public void deletePost(BlogPost post) {
		blogDAO.deletePost(post);

	}

	public void updatePost(BlogPost post) {
		blogDAO.updatePost(post);

	}

	public List<BlogPost> getRecentPosts(int count) {
		return blogDAO.getRecentPosts(count);
	}

	@Override
	public List<BlogPost> getAllPosts() {

		return blogDAO.getAllPosts();
	}

	@Override
	public void deletePost(int id) {

		BlogPost post = getPostById(id);
		if (post != null) {
			deletePost(post);
		}

	}


	@Override
	public void deleteComment(Comment comment) {
		blogDAO.deleteComment(comment);
		
	}

	@Override
	public void addComment(int postId, Comment comment) {
		blogDAO.addComment(postId, comment);
	}

}
