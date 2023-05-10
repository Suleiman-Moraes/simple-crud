package br.com.simplecrud.config.security.interfaces;

import br.com.simplecrud.api.model.dto.security.AccountCredentialsDTO;
import br.com.simplecrud.api.model.dto.security.TokenDTO;

public interface IAuthService {
    
    TokenDTO signin(AccountCredentialsDTO data);

    TokenDTO refreshToken(String refreshToken);
}
