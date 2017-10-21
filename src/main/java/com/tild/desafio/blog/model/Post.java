package com.tild.desafio.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Preconditions;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@Lob
	private String text;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties("posts")
	private User user;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "post_tag", 
		joinColumns = @JoinColumn(name = "post_id"), 
		inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags;

	public Post() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getTagsString() {
		if (this.getTags() != null) {
			return tags.stream().map(Tag::getTag).collect(Collectors.joining(", "));
		} else {
			return "";
		}
	}

	public void setTagsString(String tagsString) {
		if (tagsString != null && !"".equals(tagsString)) {
			this.tags = Arrays.stream(tagsString.split(",")).map(s -> {
				Tag t = new Tag();
				t.setTag(s.trim());
				return t;
			}).collect(Collectors.toList());
		} else {
			this.tags = new ArrayList<Tag>();
		}
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", text=" + text + ", user=" + user + ", tags=" + tags + "]";
	}

	public boolean isValid() {
		boolean valid = false;

		try {
			Arrays.asList(this.getUser(), this.getText(), this.getTitle(), this.getTags())
					.forEach(Preconditions::checkNotNull);

			Arrays.asList(this.getText(), this.getTitle()).forEach(txt -> {
				Preconditions.checkArgument(!txt.isEmpty());
			});

			this.getTags().forEach(t -> {
				Preconditions.checkArgument(!t.getTag().isEmpty());
			});

			valid = true;
		} catch (Exception e) {
			valid = false;
		}

		return valid;
	}
}
