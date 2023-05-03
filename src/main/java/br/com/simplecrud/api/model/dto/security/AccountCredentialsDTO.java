package br.com.simplecrud.api.model.dto.security;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountCredentialsDTO implements Serializable {

    private String username;

    private String password;
}
