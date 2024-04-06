package ru.learnUP.springboottest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import ru.learnUP.springboottest.dto.DtoClassCountComments;
import ru.learnUP.springboottest.entity.Comment;
import ru.learnUP.springboottest.entity.Post;
import ru.learnUP.springboottest.repository.PostRepository;
import ru.learnUP.springboottest.service.PostService;

import java.util.List;


@SpringBootTest
@ContextConfiguration(initializers = {SpringBootTestApplicationTests.Initializer.class})
@Testcontainers
class SpringBootTestApplicationTests {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:alpine")
            .withDatabaseName("myTestDb")
            .withUsername("vladimir")
            .withPassword("postgres")
            .withInitScript("db.sql");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    @Transactional
    public void getPosts() {
        List<Post> postsActual = postRepository.findAll();

        Post post1 = new Post(1L, "text1", "title1");
        Comment comment = new Comment(1L, "comment1", post1);
        post1.setComments(List.of(comment));
        List<Post> postsExpected = List.of(post1);

        Assertions.assertEquals(postsExpected, postsActual);
    }

    @Test
    @Transactional
    public void getCountCommentsByPost() {
        List<DtoClassCountComments> listActual = postService.getCountCommentsByPost();
        DtoClassCountComments dtoClassCountComments = new DtoClassCountComments(1L, 1L);
        List<DtoClassCountComments> listExpected = List.of(dtoClassCountComments);
        Assertions.assertEquals(listExpected, listActual);
    }
}
