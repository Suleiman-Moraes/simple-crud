package br.com.simplecrud.api.controller.interfaces;

import org.springframework.http.ResponseEntity;

public interface IController<D> {

    ResponseEntity<D> findByKey(long id);
}
