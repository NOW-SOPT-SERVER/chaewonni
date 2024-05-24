package org.sopt.practice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    private Post(Blog blog, String title, String content) {
        this.blog = blog;
        this.title = title;
        this.content = content;
    }

    public static Post create(
            Blog blog, String title, String content
    ) {
        return new Post(blog, title, content);
    }
}
