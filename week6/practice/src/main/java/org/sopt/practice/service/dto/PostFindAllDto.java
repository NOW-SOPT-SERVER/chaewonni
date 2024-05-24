package org.sopt.practice.service.dto;

import org.sopt.practice.domain.Blog;
import org.sopt.practice.domain.Post;

import java.util.List;

public record PostFindAllDto(

        Long id,
        String title,
        String content
) {
    public static List<PostFindAllDto> listOf(List<Post> posts) {
        return posts
                .stream()
                .map(post -> new PostFindAllDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContent()
                )).toList();
    }
}
