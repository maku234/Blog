package com.margus.blog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.margus.blog.domain.BlogPost;
import com.margus.blog.domain.Comment;

@Repository
public class HibernateBlogDAO implements BlogDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public HibernateBlogDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addPost(BlogPost post) {
		currentSession().save(post);

	}

	public BlogPost getPostById(int id) {
		return (BlogPost) currentSession().get(BlogPost.class, id);
	}

	public void deletePost(BlogPost post) {
		currentSession().delete(post);

	}

	public void updatePost(BlogPost post) {
		currentSession().update(post);

	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	public List<BlogPost> getRecentPosts(int count) {

		Query query = currentSession().createQuery(
				"FROM BlogPost AS Post ORDER BY Post.date DESC");
		query.setFirstResult(0);
		query.setMaxResults(count);
		return (List<BlogPost>) query.list();
	}

	@Override
	public List<BlogPost> getAllPosts() {
		return (List<BlogPost>) currentSession().createQuery(
				"FROM BlogPost AS Post ORDER BY Post.date DESC").list();
	}

	@Override
	public void addComment(int postId, Comment comment) {
		BlogPost post = getPostById(postId);
		if (post == null) {
			return;
		}

		currentSession().save(comment);
		post.getComments().add(comment);
		currentSession().save(post);

	}

	@Override
	public Comment getCommandById(int id) {
		return (Comment) currentSession().get(Comment.class, id);
	}

	@Override
	public void deleteComment(Comment comment) {
		
		Query query = currentSession().createSQLQuery("DELETE FROM posts_comment WHERE comments_comment_id = ?");
		query.setParameter(0, comment.getId());
		query.executeUpdate();
		currentSession().delete(comment);

	}

}
