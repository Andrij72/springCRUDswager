package com.andrkul.handler.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
public class NotFoundRuntimeException extends RuntimeException {

    private Long id;
    private String message;

    public NotFoundRuntimeException(Long id, String message) {
        super(message);
        this.id = id;
        this.message = message;
        log.error("Cannot find resource by id: {} with message {}", id, message);
    }
}
