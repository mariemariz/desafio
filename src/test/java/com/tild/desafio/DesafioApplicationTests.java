package com.tild.desafio;

import com.tild.desafio.blog.data.PostRepository;
import com.tild.desafio.blog.data.UserRepository;
import com.tild.desafio.blog.model.Post;
import com.tild.desafio.blog.model.Tag;
import com.tild.desafio.blog.model.User;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DesafioApplicationTests {

	@Autowired private PostRepository postRepository;
	@Autowired private UserRepository userRepository;

	@Test
	public void when_store_a_post_and_findAll_then_return_all_posts() {

		//given
		User user = new User();
		user.setName("Test User");
		user.setTwitter("test");
		userRepository.save(user);

		Post post = new Post();
		post.setTitle("Test post");
		post.setText("Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...");
		post.setUser(user);
		
		Tag tag = new Tag();
		tag.setTag("One Tag");
		Tag tag2 = new Tag();
		tag2.setTag("Other Tag");
		List<Tag> tags = new ArrayList<Tag>(2);
		tags.add(tag);
		tags.add(tag2);
		
		post.setTags(tags);
		
		postRepository.save(post);

		//when
		Post found = postRepository.findOne(post.getId());

		//then
		Assert.assertThat(found.getUser(), Matchers.is(user));
		Assert.assertThat(found.getTagsString(), Matchers.is(tag.getTag() + ", " + tag2.getTag()));
	}

}
