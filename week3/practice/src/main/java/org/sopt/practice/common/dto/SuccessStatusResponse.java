package org.sopt.practice.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

public record SuccessStatusResponse<T>(
        int status,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        T data
) {

    public static SuccessStatusResponse<Void> of(SuccessMessage successMessage) {
        return new SuccessStatusResponse<Void>(successMessage.getStatus(),successMessage.getMessage(), null);
    }

    public static <T> SuccessStatusResponse<T> of(SuccessMessage successMessage, T data) {
        return new SuccessStatusResponse<T>(successMessage.getStatus(),successMessage.getMessage(),data);
    }
}
