package br.com.simplecrud.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.simplecrud.api.exception.ResourceNotFoundException;
import br.com.simplecrud.api.model.Person;
import br.com.simplecrud.api.model.dto.v1.PersonDTO;
import br.com.simplecrud.api.repository.IPersonRepository;
import br.com.simplecrud.api.service.interfaces.IPersonService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonService implements IPersonService {

    private IPersonRepository repository;

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public Person findByKey(Long id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Long insert(Person object) {
        return save(object).getKey();
    }

    @Override
    public void update(PersonDTO object, Long id) {
        Person entity = findByKey(id);
        entity.setFirstName(object.getFirstName());
        entity.setLastName(object.getLastName());
        entity.setAddress(object.getAddress());
        entity.setGender(object.getGender());
        save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findByKey(id));
    }

    public Person save(Person object) {
        return repository.save(object);
    }
}
