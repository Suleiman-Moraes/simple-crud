package br.com.simplecrud.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;

public final class ManualConnection {

    @Value("${spring.datasource.url}")
    private static String url;

    @Value("${spring.datasource.username}")
    private static String user;

    @Value("${spring.datasource.password}")
    private static String password;

    private static Connection connect;

    private ManualConnection() {
        // empty
    }

    public static Connection getConnection() throws SQLException {
        if (connect == null) {
            connect = DriverManager.getConnection(url, user, password);
        }
        return connect;
    }

    public static void closeConnection() throws SQLException {
        if (connect != null) {
            connect.close();
            connect = null;
        }
    }
}
