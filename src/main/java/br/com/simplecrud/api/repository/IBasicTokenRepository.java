package br.com.simplecrud.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.simplecrud.api.model.BasicToken;

public interface IBasicTokenRepository extends JpaRepository<BasicToken, Long> {

    Optional<BasicToken> findByUsername(String username);

}
