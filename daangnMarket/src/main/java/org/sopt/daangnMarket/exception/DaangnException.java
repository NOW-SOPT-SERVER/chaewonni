package org.sopt.daangnMarket.exception;

import lombok.Getter;
import org.sopt.daangnMarket.util.dto.ErrorMessage;

@Getter
public class DaangnException extends RuntimeException {

    private ErrorMessage errorMessage;
    public DaangnException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
