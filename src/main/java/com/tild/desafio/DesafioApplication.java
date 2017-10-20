package com.tild.desafio;

import com.tild.desafio.blog.data.PostRepository;
import com.tild.desafio.blog.data.UserRepository;
import com.tild.desafio.blog.model.Post;
import com.tild.desafio.blog.model.Tag;
import com.tild.desafio.blog.model.User;

import org.h2.server.web.WebServlet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DesafioApplication {

	@Bean
	ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}

	@Bean
	CommandLineRunner loadSampleData(UserRepository userRepository, PostRepository postRepository) {
		return args -> {
			// users
			Arrays.asList("Josh Long@starbuxman, Trisha Gee@trisha_gee, Mario Fusco@mariofusco".split(",")).stream()
					.forEach(t -> {
						User user = new User();
						user.setName(t.split("@")[0]);
						user.setTwitter(t.split("@")[1]);

						userRepository.save(user);
					});

			String text = "<p>\n"
					+ "                        Cloud-native is an approach to building and running applications that fully exploits the\n"
					+ "                        advantages of the cloud computing delivery model. Cloud-native is about how applications are\n"
					+ "                        created and deployed, not where. While today public cloud impacts the thinking about\n"
					+ "                        infrastructure investment for virtually every industry, a cloud-like delivery model isn’t\n"
					+ "                        exclusive to public environments. It's appropriate for both public and private clouds. Most\n"
					+ "                        important is the ability to offer nearly limitless computing power, on-demand, along with\n"
					+ "                        modern data and application services for developers. When companies build and operate\n"
					+ "                        applications in a cloud-native fashion, they bring new ideas to market faster and respond\n"
					+ "                        sooner to customer demands.\n" + "                    </p>";

			User joshLong = userRepository.findOne(1L);

			Post post = new Post();
			post.setTitle("What are Cloud-Native Applications?");
			post.setText(text);
			post.setUser(joshLong);
			
			Tag tag = new Tag();
			tag.setTag("Cloud-Native Applications");
			Tag tag2 = new Tag();
			tag2.setTag("Other Tag");
			List<Tag> tags = new ArrayList<Tag>(2);
			tags.add(tag);
			tags.add(tag2);
			
			post.setTags(tags);
			
			postRepository.save(post);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}
}
