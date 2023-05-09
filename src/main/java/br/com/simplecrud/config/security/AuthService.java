package br.com.simplecrud.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import br.com.simplecrud.api.model.User;
import br.com.simplecrud.api.model.dto.security.AccountCredentialsDTO;
import br.com.simplecrud.api.model.dto.security.TokenDTO;
import br.com.simplecrud.api.service.UserService;
import br.com.simplecrud.config.Messages;
import br.com.simplecrud.config.security.interfaces.IAuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider tokenProvider;

    private UserService userService;

    @Override
    public TokenDTO signin(AccountCredentialsDTO data) {
        try {
            final String username = data.getUsername();
            final String password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return tokenProvider.createAccessToken(username,
                    ((User) userService.loadUserByUsername(username)).getRoles());
        } catch (Exception e) {
            log.warn("signin " + e.getMessage(), e);
            throw new BadCredentialsException(Messages.getMessage("invalid_username_password"));
        }
    }
}
