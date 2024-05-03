package org.sopt.daangnMarket.exception;

import lombok.Getter;
import org.sopt.daangnMarket.util.dto.ErrorMessage;

@Getter
public class NotFoundException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

}
