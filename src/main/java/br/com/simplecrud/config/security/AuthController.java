package br.com.simplecrud.config.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.simplecrud.api.model.dto.security.AccountCredentialsDTO;
import br.com.simplecrud.api.model.dto.security.TokenDTO;
import br.com.simplecrud.config.Messages;
import br.com.simplecrud.config.security.interfaces.IAuthService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private IAuthService authService;

    @PostMapping(value = "/signin")
    public ResponseEntity<Object> signin(@RequestBody AccountCredentialsDTO data) {
        if (data != null && StringUtils.hasText(data.getUsername()) && StringUtils.hasText(data.getPassword())) {
            final TokenDTO token = authService.signin(data);
            if (token != null) {
                return ResponseEntity.ok(token);
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Messages.getMessage("invalid_client_request"));
    }
}
