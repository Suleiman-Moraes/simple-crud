package br.com.simplecrud.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.simplecrud.api.exception.ResourceNotFoundException;

@RestController
@RequestMapping("api/v1/test")
public class TestController {

    @GetMapping
    public ResponseEntity<String> testExecption() throws Exception {
        throw new ResourceNotFoundException();
    }
}
