package com.mackenzie.repomonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Ponto de entrada da aplicação.
 *
 * @SpringBootApplication faz três coisas ao mesmo tempo:
 * - Habilita a configuração automática do Spring
 * - Escaneia todas as classes do pacote em busca de @Controller, @Service, etc.
 * - Permite declarar configurações extras nessa própria classe
 */
@SpringBootApplication
public class RepoMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepoMonitorApplication.class, args);
    }
}
