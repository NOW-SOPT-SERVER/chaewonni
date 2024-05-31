package org.sopt.daangnMarket.exception;

import lombok.Getter;
import org.sopt.daangnMarket.util.dto.ErrorMessage;

@Getter
public class NotFoundException extends DaangnException {

    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
