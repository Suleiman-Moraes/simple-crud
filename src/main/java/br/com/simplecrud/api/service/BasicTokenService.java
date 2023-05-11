package br.com.simplecrud.api.service;

import java.util.Base64;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.simplecrud.api.exception.UnauthorizedBasic;
import br.com.simplecrud.api.model.BasicToken;
import br.com.simplecrud.api.repository.IBasicTokenRepository;
import br.com.simplecrud.api.service.interfaces.IBasicTokenService;
import br.com.simplecrud.api.util.Constants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
public class BasicTokenService implements IBasicTokenService {

    @Getter
    private IBasicTokenRepository repository;

    private HttpServletRequest request;

    private PasswordEncoder passwordEncoder;

    @Override
    public boolean validateBasicToken() {
        final String BASIC_ESPACE = Constants.BASIC + " ";
        String basic = request.getHeader(Constants.AUTHORIZATION);
        if (StringUtils.hasText(basic) && basic.contains(BASIC_ESPACE)) {
            basic = basic.substring(BASIC_ESPACE.length());
            basic = new String(Base64.getDecoder().decode(basic));
            final String[] userPassword = basic.split(":");
            final BasicToken basicToken = repository.findByUsername(userPassword[0])
                    .orElseThrow(UnauthorizedBasic::new);
            if (passwordEncoder.matches(userPassword[1], basicToken.getPassword())) {
                return true;
            }
        }
        throw new UnauthorizedBasic();
    }
}
