package com.javatownbackend;

import com.javatownbackend.models.Admin;
import com.javatownbackend.service.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavatownBackendApplication {

    private final AdminService adminService;

    public JavatownBackendApplication(AdminService adminService) {
        this.adminService = adminService;
    }

    public static void main(String[] args) {
        SpringApplication.run(JavatownBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            adminService.createAdmin(new Admin("Admin", "Admin"));
        };
    }
}
