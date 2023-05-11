package br.com.simplecrud.api.controller;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.simplecrud.api.model.dto.v1.BasicTokenListDTO;
import br.com.simplecrud.api.service.interfaces.IBasicTokenService;
import br.com.simplecrud.mapper.Mapper;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/basictoken")
@AllArgsConstructor
public class BasicTokenController {

    private IBasicTokenService service;

    @GetMapping
    public ResponseEntity<List<BasicTokenListDTO>> findAll() throws NotFoundException {
        return ResponseEntity.ok(
                Mapper.parseObjects(service.findAll(), BasicTokenListDTO.class).orElseThrow(NotFoundException::new));
    }
}
