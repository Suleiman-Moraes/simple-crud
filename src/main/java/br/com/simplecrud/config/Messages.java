package br.com.simplecrud.config;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Messages {

    private static final String BUNDLE_NAME = "message.messages";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    public static final String RESOURCE_NOT_FOUND = "resource_not_found";

    private Messages() {
    }

    public static String getMessage(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            log.warn(e.getMessage(), e);
            return key;
        }
    }
}
