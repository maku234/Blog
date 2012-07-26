package com.margus.blog.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Posts")
public class BlogPost implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "post_id", insertable = false, updatable = false, nullable = false)
	private int id;
	@NotEmpty(message = "Title must not be empty")
	@Size(min = 1, max = 100, message = " Title must be between 1 and 100 characters long")
	@Column(name = "title", insertable = true, updatable = true, nullable = false)
	private String title;
	@NotEmpty(message = "Post text must not be empty")
	@Column(name = "post_text", insertable = true, updatable = true, nullable = false)
	@Size(min = 1, max = 10000, message = " Post text must be between 1 and 10000 characters long")
	private String text;
	@Column(name = "date", insertable = true, updatable = true, nullable = false)
	private Timestamp date;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Comment> comments;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "posts_tag",
			joinColumns = { @JoinColumn(name = "post_id") },
			inverseJoinColumns = { @JoinColumn(name = "tag_name") })
	private List<Tag> tags;

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Transient
	public int getTextLength() {
		return text.length();
	}

	@Transient
	public String getTextBeginning(int length) {

		if (length > getTextLength()) {
			length = getTextLength();
		}
		return text.substring(0, length);
	}

}
