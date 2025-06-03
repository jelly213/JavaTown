package com.javatownbackend.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class IndexController {

    /**
     * Point d'entrée principal de l'API
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> index() {
        Map<String, Object> response = new HashMap<>();
        response.put("application", "JavaTown Library Backend");
        response.put("version", "1.0.0");
        response.put("status", "running");
        response.put("timestamp", LocalDateTime.now());

        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("emprunteurs", "/api/emprunteur");
        endpoints.put("preposes", "/api/prepose");
        endpoints.put("admin", "/api/admin");
        endpoints.put("health", "/api/health");

        response.put("available_endpoints", endpoints);

        return ResponseEntity.ok(response);
    }

    /**
     * Health check général de l'application
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", LocalDateTime.now());

        Map<String, String> services = new HashMap<>();
        services.put("database", "UP");
        services.put("api", "UP");

        health.put("services", services);

        return ResponseEntity.ok(health);
    }

    /**
     * Informations sur l'API
     */
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> info() {
        Map<String, Object> info = new HashMap<>();
        info.put("name", "JavaTown Library Management System");
        info.put("description", "API pour la gestion d'une bibliothèque en ligne");
        info.put("version", "1.0.0");
        info.put("technology", "Spring Boot 3.5.0 + Java 21");
        info.put("database", "H2 Database (In-Memory)");

        Map<String, String> features = new HashMap<>();
        features.put("user_management", "Gestion des emprunteurs et préposés");
        features.put("document_management", "Gestion des livres, CD et DVD");
        features.put("loan_management", "Système d'emprunts et retours");
        features.put("fine_management", "Gestion des amendes");

        info.put("features", features);

        return ResponseEntity.ok(info);
    }

    /**
     * Documentation des endpoints disponibles
     */
    @GetMapping("/docs")
    public ResponseEntity<Map<String, Object>> documentation() {
        Map<String, Object> docs = new HashMap<>();

        docs.put("emprunteur_endpoints", getEmprunteurEndpoints());
        docs.put("prepose_endpoints", getPreposeEndpoints());
        docs.put("admin_endpoints", getAdminEndpoints());

        return ResponseEntity.ok(docs);
    }

    /**
     * Retourne la documentation des endpoints emprunteur
     */
    private Map<String, Object> getEmprunteurEndpoints() {
        Map<String, Object> endpoints = new HashMap<>();
        endpoints.put("POST /api/emprunteur/register", "Créer un nouvel emprunteur");
        endpoints.put("GET /api/emprunteur/documents/recherche/titre", "Rechercher par titre");
        endpoints.put("GET /api/emprunteur/documents/recherche/auteur", "Rechercher par auteur");
        endpoints.put("POST /api/emprunteur/emprunts", "Emprunter un document");
        endpoints.put("PUT /api/emprunteur/emprunts/{id}/retour", "Retourner un document");
        endpoints.put("GET /api/emprunteur/{id}/emprunts/actifs", "Emprunts actifs");
        return endpoints;
    }

    /**
     * Retourne la documentation des endpoints préposé
     */
    private Map<String, Object> getPreposeEndpoints() {
        Map<String, Object> endpoints = new HashMap<>();
        endpoints.put("POST /api/prepose/documents/livre", "Créer un livre");
        endpoints.put("POST /api/prepose/documents/cd", "Créer un CD");
        endpoints.put("POST /api/prepose/documents/dvd", "Créer un DVD");
        endpoints.put("GET /api/prepose/documents", "Tous les documents");
        endpoints.put("GET /api/prepose/emprunteurs", "Tous les emprunteurs");
        return endpoints;
    }

    /**
     * Retourne la documentation des endpoints admin
     */
    private Map<String, Object> getAdminEndpoints() {
        Map<String, Object> endpoints = new HashMap<>();
        endpoints.put("POST /api/admin/create", "Créer un admin");
        endpoints.put("POST /api/admin/prepose", "Créer un préposé");
        return endpoints;
    }
}