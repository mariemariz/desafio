package com.tild.desafio.blog.data;

import com.tild.desafio.blog.model.Tag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
	
	List<Tag> findByTagIgnoreCase(String tag);
	
}
