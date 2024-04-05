package ru.learnUP.springboottest.service;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.learnUP.springboottest.dto.DtoClassCountComments;
import ru.learnUP.springboottest.dto.DtoInterfaceCountComments;
import ru.learnUP.springboottest.entity.Post;
import ru.learnUP.springboottest.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    //Создание Post
    public Post createPost(Post post) {
        return repository.save(post);
    }

    //    Получение списка всех постов
    public List<Post> getPosts() {
           return repository.findAll();
    }

    public List<DtoClassCountComments> getCountCommentsByPost() {
        ModelMapper modelMapper = new ModelMapper(); //объект ModelMapper для конвертации
        List<DtoInterfaceCountComments> list1 = repository.getCommentsCountByPosts();
        List<DtoClassCountComments> list = list1.stream()
                .map(postDtoCountComments -> modelMapper.map(postDtoCountComments, DtoClassCountComments.class))
                .collect(Collectors.toList());
        return list;
    }

    //Кеширование обьекта Post (кеш записывается в установленную программу Redis
    //дальнейшие запросы к этому посту идут через кеш (если он там есть, если нет, то идет запрос в базу данных)
        @Cacheable(value = "post")
    public Post getPostById(Long id) {
        return repository.findId1(id) ;
    }

}
