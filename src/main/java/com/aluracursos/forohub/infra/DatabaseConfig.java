package com.aluracursos.forohub.infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    public void printDatabaseUrl() {
        System.out.println("URL de la base de datos: " + databaseUrl);
    }
}