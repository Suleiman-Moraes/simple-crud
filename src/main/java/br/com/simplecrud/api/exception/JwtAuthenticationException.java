package br.com.simplecrud.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.simplecrud.api.util.Messages;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg) {
        super(msg);
    }

    public JwtAuthenticationException() {
        this(Messages.getMessage("expire_or_invalid_token"));
    }
}
