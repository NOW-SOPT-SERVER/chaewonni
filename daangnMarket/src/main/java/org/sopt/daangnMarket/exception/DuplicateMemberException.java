package org.sopt.daangnMarket.exception;

import lombok.Getter;
import org.sopt.daangnMarket.util.dto.ErrorMessage;

@Getter
public class DuplicateMemberException extends DaangnException {

    public DuplicateMemberException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
