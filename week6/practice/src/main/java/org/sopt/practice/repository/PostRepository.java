package org.sopt.practice.repository;

import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.domain.Post;
import org.sopt.practice.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    default Post findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new NotFoundException(ErrorMessage.POST_NOT_FOUND)
        );
    }
}
