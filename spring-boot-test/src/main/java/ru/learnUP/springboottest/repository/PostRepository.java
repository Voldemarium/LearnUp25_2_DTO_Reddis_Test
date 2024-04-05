package ru.learnUP.springboottest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnUP.springboottest.dto.DtoInterfaceCountComments;
import ru.learnUP.springboottest.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository <Post, Long> {
    //JPQL(HQL) запрос
    @Override //Показывает, что мы изменяем стандартный запрос (со стандартным названием)
//    @Query(value = "FROM Post p JOIN FETCH p.comments")
    @Query(value = "FROM Post")
    List<Post> findAll();

    //Поиск по названию
    List<Post> findAllByTitle(String title);

    //Поиск по тексту одного поста
    //Optional<Post> findPostByTextContains(String text);
    //Поиск по тексту всех постов
    List<Post> findAllByTextContains(String text);

//    Нативный SQL запрос (Список всех постов с комментариями)
       @Query(value = "SELECT *\n" +
               "FROM post p\n" +
               "LEFT JOIN comment c  ON p.id = c.post_id\n" +
               "WHERE c.id IS NOT NULL",
               nativeQuery = true)
       List<Post> findByIdWithComments();

    //Нативный SQL запрос (Количество постов с комментариями)
       @Query(value = "SELECT count(*)\n" +
               "FROM post p\n" +
               "LEFT JOIN comment c ON p.id = c.post_id\n" +
               "WHERE p.id = ?1\n" +
               "  AND c.id IS NOT NULL",
               nativeQuery = true
       )
       Long getCommentsCountById(long id);

    //Нативный SQL запрос (Количество комментариев в каждом посте)
    @Query(value =
            "select post_id, COUNT(*)\n" +
            "from comment\n" +
            "GROUP by post_id\n" +
            "ORDER by post_id",
            nativeQuery = true
    )
    List<DtoInterfaceCountComments> getCommentsCountByPosts();

    //JPQL(HQL) запрос
       @Query(value = "FROM Post p\n" +
               "INNER JOIN FETCH p.comments\n" +
               "WHERE p.id = :id")
       Post findId1(Long id);
}
