package com.Satisfyre.app.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class dotenvConfig {
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("/Users/michealsmacbook/Downloads/app")
            .filename(".env")
            .load();

    public static String get(String key) {
        return dotenv.get(key);
    }
}
