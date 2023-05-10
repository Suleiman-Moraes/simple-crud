package br.com.simplecrud.api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.simplecrud.api.repository.IUserRepository;
import br.com.simplecrud.api.service.interfaces.IUserService;
import br.com.simplecrud.config.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Getter
    private IUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(Messages.getMessage("user_not_found")));
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }
}
