package br.com.simplecrud.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.simplecrud.config.Messages;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedBasic extends PatternException {

    public UnauthorizedBasic() {
        super(Messages.getMessage(Messages.USER_NOT_PERMISSION), HttpStatus.UNAUTHORIZED);
    }

    public UnauthorizedBasic(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
