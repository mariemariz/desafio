package com.tild.desafio.blog.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String tag;

	public Tag() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Tag tag = (Tag) o;
		return Objects.equals(id, tag.id) && Objects.equals(this.tag, tag.tag);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tag);
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", tag=" + tag + "]";
	}

}
