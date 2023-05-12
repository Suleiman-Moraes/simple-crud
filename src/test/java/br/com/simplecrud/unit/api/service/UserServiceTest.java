package br.com.simplecrud.unit.api.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.simplecrud.api.model.User;
import br.com.simplecrud.api.repository.IUserRepository;
import br.com.simplecrud.api.service.UserService;
import br.com.simplecrud.mock.MockUser;

class UserServiceTest {

    private MockUser input;

    @Spy
    @InjectMocks
    private UserService service;

    @Mock
    private IUserRepository repository;

    private final Long key = 1l;
    private User entity;

    @BeforeEach
    void setUp() {
        input = new MockUser();
        MockitoAnnotations.openMocks(this);

        entity = input.mockEntity(1);
        entity.setKey(key);
    }

    @Test
    void testExistsByUsername() {
        assertFalse(service.existsByUsername("Test"), "Return not equal");
    }

    @Test
    void testLoadUserByUsername() {
        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("Test"), "Return not equal");
    }
}
