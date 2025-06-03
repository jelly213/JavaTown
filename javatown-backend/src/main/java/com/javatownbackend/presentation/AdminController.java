package com.javatownbackend.presentation;

import com.javatownbackend.models.Admin;
import com.javatownbackend.service.AdminService;
import com.javatownbackend.service.execptions.PreposeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * Créer un nouvel administrateur
     */
    @PostMapping("/create")
    public ResponseEntity<String> createAdmin(
            @RequestParam String username,
            @RequestParam String password) {
        try {
            Admin admin = new Admin(username, password);
            adminService.createAdmin(admin);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Administrateur créé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la création de l'administrateur: " + e.getMessage());
        }
    }

    /**
     * Créer un nouveau préposé
     */
    @PostMapping("/prepose")
    public ResponseEntity<String> creerNouveauPrepose(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phoneNumber) {
        try {
            adminService.creerNouveauPrepose(name, email, phoneNumber);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Préposé créé avec succès");
        } catch (PreposeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la création du préposé: " + e.getMessage());
        }
    }

    /**
     * Health check pour l'administration
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Admin API is running");
    }
}