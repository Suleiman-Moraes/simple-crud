package br.com.simplecrud.mock;

import java.util.Arrays;

import br.com.simplecrud.api.model.User;
import br.com.simplecrud.mock.interfaces.IMock;

public class MockUser implements IMock<User> {

    /**
     * 123456
     */
    public final static String PASSWORD = "{pbkdf2}f88acbc6eccb60d08e92318843587d22533a955d8e8d6246a360d23b9dfacbb2dfab247eb1d0a9c5";

    @Override
    public User mockEntity(Integer number) {
        return User.builder()
                .username("Teste" + number)
                .enabled(true)
                .password(PASSWORD)
                .authorities(Arrays.asList(new MockPermission().mockEntity(1)))
                .build();
    }
}
