package ru.learnUP.springboottest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.learnUP.springboottest.entity.Post;

@Data
@NoArgsConstructor
public class DtoClassCountCommentsJPQL {
    private Post post;
    private Long count;

    public DtoClassCountCommentsJPQL(Post post, Long count) {
        this.post = post;
        this.count = count;
    }

    @Override
    public String toString() {
        return "DtoClassCountCommentsJPQL{" +
                "post=" + post.getId() +
                ", count=" + count +
                '}';
    }
}
