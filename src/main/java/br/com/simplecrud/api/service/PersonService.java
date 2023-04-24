package br.com.simplecrud.api.service;

import org.springframework.stereotype.Service;

import br.com.simplecrud.api.model.Person;
import br.com.simplecrud.api.model.dto.v1.PersonDTO;
import br.com.simplecrud.api.repository.IPersonRepository;
import br.com.simplecrud.api.service.interfaces.IPersonService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
public class PersonService implements IPersonService {

    @Getter
    private IPersonRepository repository;

    @Override
    public void update(PersonDTO object, Long id) {
        Person entity = findByKey(id);
        entity.setFirstName(object.getFirstName());
        entity.setLastName(object.getLastName());
        entity.setAddress(object.getAddress());
        entity.setGender(object.getGender());
        save(entity);
    }

    public Person save(Person object) {
        return repository.save(object);
    }
}
