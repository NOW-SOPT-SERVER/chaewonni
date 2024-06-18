package org.sopt.practice.exception;

import lombok.Getter;
import org.sopt.practice.common.dto.ErrorMessage;

@Getter
public class DuplicateMemberException extends BusinessException {

    public DuplicateMemberException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
