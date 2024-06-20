package org.sopt.daangnMarket.exception;

import lombok.Getter;
import org.sopt.daangnMarket.util.dto.ErrorMessage;

@Getter
public class ConflictException extends DaangnException {

    public ConflictException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
