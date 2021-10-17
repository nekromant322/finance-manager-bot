package com.nekromant.finance.exception;

import com.nekromant.finance.contants.Errors;
import lombok.Getter;

@Getter
public class CommandExecuteException extends RuntimeException {

    private final Errors error;

    public CommandExecuteException(Errors error) {
        super();
        this.error = error;
    }
}
