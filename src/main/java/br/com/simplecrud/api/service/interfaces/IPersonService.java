package br.com.simplecrud.api.service.interfaces;

import br.com.simplecrud.api.model.Person;
import br.com.simplecrud.api.model.dto.v1.PersonDTO;

public interface IPersonService extends IService<Person, Long> {

    void update(PersonDTO object, Long id);
}
