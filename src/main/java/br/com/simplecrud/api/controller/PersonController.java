package br.com.simplecrud.api.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.simplecrud.api.controller.interfaces.IController;
import br.com.simplecrud.api.model.Person;
import br.com.simplecrud.api.model.dto.v1.PersonDTO;
import br.com.simplecrud.api.service.interfaces.IPersonService;
import br.com.simplecrud.config.WebConfig;
import br.com.simplecrud.mapper.Mapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "People", description = "Endpoints for Managing People")
@RestController
@RequestMapping("/api/v1/person")
@AllArgsConstructor
public class PersonController implements IController<PersonDTO, Long> {

    private IPersonService service;

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
            WebConfig.MEDIA_TYPE_APPLICATION_YML_VALUE })
    @Operation(summary = "Find all People", description = "Find all People", tags = { "People" }, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(mediaType = "application/json", 
                    array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class)))
            }),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
    })
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.ok(service.parseObjects(service.findAll(), PersonDTO.class, PersonController.class));
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> findByKey(@PathVariable Long id) {
        return ResponseEntity.ok(service.parseObject(service.findByKey(id), PersonDTO.class, PersonController.class));
    }

    @PostMapping
    public ResponseEntity<Long> insert(@RequestBody @Valid PersonDTO object) {
        final Long id = service.insert(Mapper.parseObject(object, Person.class));
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri()).body(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO object, @PathVariable long id) {
        service.update(object, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
