package ru.learnUP.springboottest.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString(exclude = {"post"})
@EqualsAndHashCode(exclude = {"post"})
//@RedisHash
public class Comment implements Serializable {

    @Id
    @GeneratedValue  //(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn
    @Fetch(FetchMode.JOIN) //JOIN - чтобы все комментарии присоединились  одному посту
    private Post post;

    public Comment(Long id, String text, Post post) {
        this.id = id;
        this.text = text;
        this.post = post;
    }

    //    @Override
//    public String toString() {
//        return "Comment{" +
//                "id=" + id +
//                ", text='" + text + '\'' +
//                ", post=" + post.getId() +
//                '}';
//    }
}
