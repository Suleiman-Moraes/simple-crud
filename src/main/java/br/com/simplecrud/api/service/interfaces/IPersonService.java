package br.com.simplecrud.api.service.interfaces;

import java.util.List;

import br.com.simplecrud.api.model.Person;
import br.com.simplecrud.api.model.dto.v1.PersonDTO;

public interface IPersonService extends IConverterService<Person> {

    List<Person> findAll();

    Person findByKey(Long id);

    Long insert(Person object);

    void update(PersonDTO object, Long id);

    void delete(Long id);
}
