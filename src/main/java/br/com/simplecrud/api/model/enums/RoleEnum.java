package br.com.simplecrud.api.model.enums;

import br.com.simplecrud.api.util.Constant;

public enum RoleEnum {
    ADMIN,
    MANAGER,
    COMMON_USER;

    public String getRoleUnderline() {
        return new StringBuilder(Constant.ROLE_UNDERLINE).append(this).toString();
    }
}
