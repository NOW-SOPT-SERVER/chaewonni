package org.sopt.practice.common.dto;

import lombok.Getter;

public record SuccessStatusResponse(
        int status,
        String message
) {

    public static SuccessStatusResponse of(SuccessMessage successMessage) {
        return new SuccessStatusResponse(successMessage.getStatus(),successMessage.getMessage());
    }
}
