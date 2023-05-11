package br.com.simplecrud.config.security.interfaces;

import br.com.simplecrud.config.security.dto.AccountCredentialsDTO;
import br.com.simplecrud.config.security.dto.TokenDTO;

public interface IAuthService {
    
    TokenDTO signin(AccountCredentialsDTO data);

    TokenDTO refreshToken(String refreshToken);
}
