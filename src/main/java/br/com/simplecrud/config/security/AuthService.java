package br.com.simplecrud.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.simplecrud.api.model.User;
import br.com.simplecrud.api.service.interfaces.IUserService;
import br.com.simplecrud.config.Messages;
import br.com.simplecrud.config.security.dto.AccountCredentialsDTO;
import br.com.simplecrud.config.security.dto.TokenDTO;
import br.com.simplecrud.config.security.interfaces.IAuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider tokenProvider;

    private IUserService userService;

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

    @Override
    public TokenDTO refreshToken(String refreshToken) {
        final TokenDTO token = tokenProvider.refreshToken(refreshToken);
        if(!userService.existsByUsername(token.getUsername())){
            throw new UsernameNotFoundException(Messages.getMessage("user_not_found"));
        }
        return token;
    }
}
