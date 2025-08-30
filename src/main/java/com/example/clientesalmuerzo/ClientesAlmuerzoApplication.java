package com.example.clientesalmuerzo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example")
@EnableJpaRepositories("com.example.services")
public class ClientesAlmuerzoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientesAlmuerzoApplication.class, args);
    }

}
