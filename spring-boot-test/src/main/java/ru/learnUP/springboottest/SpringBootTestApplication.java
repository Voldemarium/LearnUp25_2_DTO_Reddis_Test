package ru.learnUP.springboottest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnUP.springboottest.entity.Post;
import ru.learnUP.springboottest.service.CommentService;
import ru.learnUP.springboottest.service.PostService;
import ru.learnUP.springboottest.repository.PostRepository;

@SpringBootApplication
@EnableCaching
//@EnableRedisRepositories
public class SpringBootTestApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootTestApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootTestApplication.class, args);
//		log.info("UUID = {}", UUID.randomUUID());

		PostService postService = context.getBean(PostService.class);
		CommentService commentService = context.getBean(CommentService.class);

		Post post = new Post();
		post.setTitle("another post");
		post.setText("Another post");

//		postService.createPost(post);

		log.info("Posts: {}", postService.getPosts());
		log.info("Comments: {}", commentService.getComments());

		PostRepository postRepository = context.getBean(PostRepository.class);

		log.info("Search result 1: {}", postRepository.findAllByTextContains("Another"));

		log.info("Search result 2: {}", postRepository.findByIdWithComments());

		log.info("SQL: Post 1 have {} comments", postRepository.getCommentsCountByIdSQL(1));
		log.info("JPQL: Post 2 have {} comments", postRepository.getCommentsCountByIdJPQL(2));

		log.info("JPQL: count comment by posts {}", postService.getCountCommentsByPostJPQL());
		log.info("SQL: count comment by posts {}", postService.getCountCommentsByPostSQL());

//		log.info("Post id = 1: {}", postRepository.findId1(1L));

		for (int i = 0; i < 5 ; i++) {
			log.info("Post id = 1: {}", postService.getPostById(1L));
		}

	}
}
