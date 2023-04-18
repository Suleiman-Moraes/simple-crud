package br.com.simplecrud.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.simplecrud.api.exception.ResourceNotFoundException;
import br.com.simplecrud.config.security.ValidBasicToken;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/test")
public class TestController {

    @GetMapping
    public ResponseEntity<String> testExecption() {
        throw new ResourceNotFoundException();
    }

    @GetMapping(value = "2")
    public ResponseEntity<String> testExecption2(HttpServletRequest request) {
        ValidBasicToken.valid(request);
        return ResponseEntity.ok("Tudo nos conformes da lei.");
    }
}
