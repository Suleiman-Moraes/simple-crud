package br.com.simplecrud.mock;

import java.util.LinkedList;
import java.util.List;

import br.com.simplecrud.api.model.BasicToken;

public class MockBasicToken {

    /**
     * 123456
     */
    public final static String PASSWORD = "{pbkdf2}f88acbc6eccb60d08e92318843587d22533a955d8e8d6246a360d23b9dfacbb2dfab247eb1d0a9c5";

    public BasicToken mockEntity(Integer number) {
        return BasicToken.builder()
                .username("Teste" + number)
                .enabled(true)
                .password(PASSWORD)
                .build();
    }

    public List<BasicToken> mockEntityList() {
        List<BasicToken> entitys = new LinkedList<>();
        for (int i = 1; i <= 14; i++) {
            entitys.add(mockEntity(i));
        }
        return entitys;
    }
}
