package ru.learnUP.springboottest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnUP.springboottest.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
