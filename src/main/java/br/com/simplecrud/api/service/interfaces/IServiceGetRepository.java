package br.com.simplecrud.api.service.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IServiceGetRepository<E, I> {

    JpaRepository<E, I> getRepository();
}
