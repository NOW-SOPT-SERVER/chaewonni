package org.sopt.daangnMarket.exception;

import org.sopt.daangnMarket.util.dto.ErrorMessage;

public class ForbiddenException extends DaangnException{

    public ForbiddenException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
