package br.com.simplecrud.mock;

import br.com.simplecrud.api.model.Permission;
import br.com.simplecrud.api.model.enums.RoleEnum;
import br.com.simplecrud.mock.interfaces.IMock;

public class MockPermission implements IMock<Permission> {

    @Override
    public Permission mockEntity(Integer number) {
        return Permission.builder()
                .role(RoleEnum.MANAGER)
                .description("description " + number)
                .build();
    }
}
