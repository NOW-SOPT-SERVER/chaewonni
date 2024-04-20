package org.sopt.practice.service.dto;

import org.sopt.practice.domain.Part;

public record MemberCreateDto(
        String name,
        Part part,
        int age
) {

}
