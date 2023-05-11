package br.com.simplecrud.api.service.interfaces;

import br.com.simplecrud.api.model.BasicToken;

public interface IBasicTokenService extends IServiceFindAll<BasicToken, Long> {

    boolean validateBasicToken();
}
