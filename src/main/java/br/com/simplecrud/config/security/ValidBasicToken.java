package br.com.simplecrud.config.security;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import br.com.simplecrud.api.exception.UnauthorizedBasic;
import br.com.simplecrud.api.util.Constants;
import br.com.simplecrud.config.ManualConnection;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ValidBasicToken {

    private ValidBasicToken() {
        // empty
    }

    public static int valid(HttpServletRequest request) {
        try {
            final String authorization = request.getHeader(Constants.AUTHORIZATION);
            if (StringUtils.hasText(authorization) &&
                    authorization.toUpperCase().contains(Constants.BASIC.toUpperCase())) {
                final String[] loginPassword = new String(Base64.getDecoder().decode(authorization.substring(6)))
                        .split(":");
                if (loginPassword.length == Constants.TWO) {
                    ManualConnection.getConnection();
                    listar();
                    ManualConnection.closeConnection();
                    return 1;
                }
            }
        } catch (Exception e) {
            log.error("valid " + e.getMessage(), e);
        }
        throw new UnauthorizedBasic();
    }

    private static Map<String, Object> listar() throws Exception {
        Statement statement = ManualConnection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT first_name, last_name, gender AS qtd FROM person");
        Map<String, Object> convidados = new HashMap<>();
        while (resultSet.next()) {
            convidados.put("firstName", resultSet.getString("first_name"));
            convidados.put("lastName", resultSet.getString("last_name"));
            convidados.put("gender", resultSet.getString("qtd"));
        }
        System.out.println(convidados);
        return convidados;
    }
}
