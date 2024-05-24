package org.sopt.practice.repository;

import jakarta.persistence.EntityNotFoundException;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.domain.Member;
import org.sopt.practice.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    default Member findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND)
        );
    }
}
