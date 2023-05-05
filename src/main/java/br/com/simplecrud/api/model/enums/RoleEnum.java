package br.com.simplecrud.api.model.enums;

import br.com.simplecrud.api.util.Constants;

public enum RoleEnum {
    ADMIN,
    MANAGER,
    COMMON_USER;

    public String getRoleUnderline() {
        return new StringBuilder(Constants.ROLE_UNDERLINE).append(this).toString();
    }
}
