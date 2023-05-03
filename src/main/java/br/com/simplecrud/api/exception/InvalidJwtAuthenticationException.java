package br.com.simplecrud.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.simplecrud.config.Messages;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationException extends PatternException {

    public InvalidJwtAuthenticationException() {
        this(Messages.getMessage(Messages.RESOURCE_NOT_FOUND));
    }

    public InvalidJwtAuthenticationException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
