package br.com.simplecrud.api.model.dto.security;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDTO implements Serializable {

    private String username;

    private String accessToken;

    private String refreshToken;

    private boolean authenticated;

    private Date created;

    private Date expiration;
}
