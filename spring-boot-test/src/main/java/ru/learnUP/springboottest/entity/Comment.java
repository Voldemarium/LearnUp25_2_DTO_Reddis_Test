package ru.learnUP.springboottest.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Table(name = "comment")
@Getter
@Setter
@ToString(exclude = {"post"})
@RequiredArgsConstructor
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

//    @Override
//    public String toString() {
//        return "Comment{" +
//                "id=" + id +
//                ", text='" + text + '\'' +
//                ", post=" + post.getId() +
//                '}';
//    }
}
