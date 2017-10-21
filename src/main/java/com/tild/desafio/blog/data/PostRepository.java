package com.tild.desafio.blog.data;

import com.tild.desafio.blog.model.Post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	@Query("SELECT p FROM Post p JOIN p.tags t WHERE t.tag = :tag")
	List<Post> findByTag(@Param("tag") String tag);
	
	List<Post> findByTextContainingIgnoreCaseOrTitleContainingIgnoreCase(String text, String title);
	
}
