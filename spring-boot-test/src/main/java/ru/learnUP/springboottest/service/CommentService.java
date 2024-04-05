package ru.learnUP.springboottest.service;

import org.springframework.stereotype.Service;
import ru.learnUP.springboottest.entity.Comment;
import ru.learnUP.springboottest.repository.CommentRepository;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    //    Получение списка всех комментариев
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

}
