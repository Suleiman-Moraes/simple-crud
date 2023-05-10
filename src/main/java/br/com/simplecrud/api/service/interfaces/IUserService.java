package br.com.simplecrud.api.service.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    boolean existsByUsername(String username);
}
