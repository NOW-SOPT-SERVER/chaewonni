package org.sopt.daangnMarket.exception;

import lombok.Getter;
import org.sopt.daangnMarket.util.dto.ErrorMessage;

@Getter
public class UnauthorizedException extends DaangnException {

    public UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
