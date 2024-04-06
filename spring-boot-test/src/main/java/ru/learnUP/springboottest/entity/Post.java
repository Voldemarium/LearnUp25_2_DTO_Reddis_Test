package ru.learnUP.springboottest.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
//@Table(name = "post")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
//в запросе SQL вместо * (select *) можно указывать имена столбцов таблицы
//@NamedQuery(name = "Post", query = "select p from Post p inner join p.comments where p.id = :id")
//@RedisHash
public class Post implements Serializable {
    @Id
    @GeneratedValue //(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String text;

//    FetchType.EAGER - значит комментарии всегда подтягиваются из базы данных при вызове
//    CascadeType.ALL - значит все комментарии после удаления поста Post удаляются из базы данных
//@OnDelete(action = OnDeleteAction.CASCADE) тоже значит все комментарии после удаления поста Post удаляются из базы данных
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.JOIN)
    private List<Comment> comments;

    public Post(Long id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

//    @Override
//    public String toString() {
//        return "Post{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", text='" + text + '\'' +
//                ", comments=" + comments +
//                '}';
//    }

}
