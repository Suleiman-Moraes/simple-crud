package br.com.simplecrud.unit.api.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.simplecrud.api.exception.ResourceNotFoundException;
import br.com.simplecrud.api.model.Person;
import br.com.simplecrud.api.model.dto.v1.PersonDTO;
import br.com.simplecrud.api.repository.IPersonRepository;
import br.com.simplecrud.api.service.PersonService;
import br.com.simplecrud.mock.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    private MockPerson input;

    @Spy
    @InjectMocks
    private PersonService service;

    @Mock
    private IPersonRepository repository;

    private final Long key = 1l;
    private Person entity;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);

        entity = input.mockEntity();
        entity.setKey(key);
    }

    @Test
    void testFindByKey() {
        when(repository.findById(key)).thenReturn(Optional.of(entity));
        Person ret = service.findByKey(key);
        assertNotNull(ret, "Retunr null");
        assertEquals(entity, ret, "Return not equal");
    }

    @Test
    void testFindByKeyThrowResourceNotFoundException() {
        when(repository.findById(key)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.findByKey(key), "Does Not Throw");
    }

    @Test
    void testDelete() {
        when(repository.findById(key)).thenReturn(Optional.of(entity));
        assertDoesNotThrow(() -> service.delete(key), "Does Not Throw");
    }

    @Test
    void testFindAll() {
        final List<Person> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        assertEquals(list, service.findAll(), "Return not equal");
    }

    @Test
    void testInsert() {
        when(service.save(entity)).thenReturn(entity);
        assertEquals(key, service.insert(entity), "Return not equal");
    }

    @Test
    void testUpdate() {
        Person entity = input.mockEntity();
        entity.setKey(key);
        when(repository.save(any())).thenReturn(entity);
        when(repository.findById(key)).thenReturn(Optional.of(entity));
        PersonDTO dto = input.mockDTO();
        dto.setFirstName("Melquisedeque");
        // ArgumentCaptor<Person> argCapt = ArgumentCaptor.forClass(Person.class);
        // argCapt.capture();
        assertDoesNotThrow(() -> service.update(dto, key), "Does Not Throw");
        assertNotNull(entity, "Retunr null");
        assertNotEquals(this.entity, entity, "Return equal");
    }

    @Test
    void testSave() {
        when(repository.save(entity)).thenReturn(entity);
        Person ret = service.save(entity);
        assertNotNull(ret, "Retunr null");
        assertEquals(entity, ret, "Return not equal");
    }
}
