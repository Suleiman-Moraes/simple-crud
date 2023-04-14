package br.com.simplecrud.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.simplecrud.api.model.Person;

public interface IPersonRepository extends JpaRepository<Person, Long> {

}
