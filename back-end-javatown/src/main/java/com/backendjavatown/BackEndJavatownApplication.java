package com.backendjavatown;

import com.backendjavatown.models.Book;
import com.backendjavatown.models.Cd;
import com.backendjavatown.models.Dvd;
import com.backendjavatown.services.EmployeeService;
import com.backendjavatown.utils.TcpServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackEndJavatownApplication {
    private final EmployeeService employeeService;
    
    public BackEndJavatownApplication(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackEndJavatownApplication.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            System.out.println("BackEndJavatownApplication has started successfully!");
            TcpServer.createTcpServer("JavaTown");


            // Create sample CD, DVD, and Book objects
            Cd cd = new Cd("Cd Test", 5, "Abdou", 3, "Pop");
            Dvd dvd = new Dvd("Dvd Test", 5, "Abdou", 3);
            Book book = new Book("Book Test", 5, 2023, "Abdou", "Publisher", 300);

            // Create CD, DVD, and Book using the EmployeeService
            employeeService.createCD(cd);
            employeeService.createDVD(dvd);
            employeeService.creatBook(book);
        };
    }
}
