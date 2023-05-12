package br.com.simplecrud.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.simplecrud.api.util.Messages;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends PatternException {

    public ResourceNotFoundException() {
        super(Messages.getMessage(Messages.RESOURCE_NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
