package com.backendjavatown;

import com.backendjavatown.utils.TcpServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackEndJavatownApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndJavatownApplication.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            System.out.println("BackEndJavatownApplication has started successfully!");
            TcpServer.createTcpServer("JavaTown");
        };
    }
}
