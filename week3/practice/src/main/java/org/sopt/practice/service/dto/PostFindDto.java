package org.sopt.practice.service.dto;

import org.sopt.practice.domain.Post;

public record PostFindDto(
        String title,
        String content,
        BlogFindDto blog
) {
    public static PostFindDto of(Post post){
        return new PostFindDto(
                post.getTitle(),
                post.getContent(),
                BlogFindDto.of(post.getBlog())
        );
    }
}
