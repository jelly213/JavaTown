package com.javatownbackend.presentation;

import com.javatownbackend.service.PreposeService;
import com.javatownbackend.service.dto.DocumentDto;
import com.javatownbackend.service.dto.DocumentListDto;
import com.javatownbackend.service.dto.EmprunteurDto;
import com.javatownbackend.service.dto.EmprunteursListDto;
import com.javatownbackend.service.execptions.CreerNouveauDocumentException;
import com.javatownbackend.service.execptions.DocumentNonDisponibleException;
import com.javatownbackend.service.execptions.EmprunteurNonDisponibleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prepose")
@CrossOrigin(origins = "*")
public class PreposeController {

    private final PreposeService preposeService;

    public PreposeController(PreposeService preposeService) {
        this.preposeService = preposeService;
    }

    /**
     * Créer un nouveau CD
     */
    @PostMapping("/documents/cd")
    public ResponseEntity<String> creerNouveauCD(
            @RequestParam String titre,
            @RequestParam int nombreExemplaires,
            @RequestParam int anneePublication,
            @RequestParam String artiste,
            @RequestParam int duree,
            @RequestParam String genre) {
        try {
            preposeService.creerNouveauCD(titre, nombreExemplaires, anneePublication, artiste, duree, genre);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("CD créé avec succès");
        } catch (CreerNouveauDocumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la création du CD: " + e.getMessage());
        }
    }

    /**
     * Créer un nouveau DVD
     */
    @PostMapping("/documents/dvd")
    public ResponseEntity<String> creerNouveauDVD(
            @RequestParam String titre,
            @RequestParam int nombreExemplaires,
            @RequestParam int anneePublication,
            @RequestParam String director,
            @RequestParam int duree) {
        try {
            preposeService.creerNouveauDVD(titre, nombreExemplaires, anneePublication, director, duree);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("DVD créé avec succès");
        } catch (CreerNouveauDocumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la création du DVD: " + e.getMessage());
        }
    }

    /**
     * Créer un nouveau Livre
     */
    @PostMapping("/documents/livre")
    public ResponseEntity<String> creerNouveauLivre(
            @RequestParam String titre,
            @RequestParam int nombreExemplaires,
            @RequestParam int anneePublication,
            @RequestParam String auteur,
            @RequestParam String editeur,
            @RequestParam int nombrePages) {
        try {
            preposeService.creerNouveauLivre(titre, nombreExemplaires, anneePublication, auteur, editeur, nombrePages);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Livre créé avec succès");
        } catch (CreerNouveauDocumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la création du livre: " + e.getMessage());
        }
    }

    /**
     * Récupérer tous les documents
     */
    @GetMapping("/documents")
    public ResponseEntity<DocumentListDto> getTousLesDocuments() {
        try {
            List<DocumentDto> documents = preposeService.getTousLesDocuments();
            return ResponseEntity.ok(new DocumentListDto(documents));
        } catch (DocumentNonDisponibleException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new DocumentListDto(e.getMessage()));
        }
    }

    /**
     * Récupérer tous les documents disponibles
     */
    @GetMapping("/documents/disponibles")
    public ResponseEntity<DocumentListDto> getDocumentsDisponibles() {
        try {
            List<DocumentDto> documents = preposeService.getDocumentsDisponibles();
            return ResponseEntity.ok(new DocumentListDto(documents));
        } catch (DocumentNonDisponibleException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new DocumentListDto(e.getMessage()));
        }
    }

    /**
     * Récupérer tous les emprunteurs
     */
    @GetMapping("/emprunteurs")
    public ResponseEntity<EmprunteursListDto> getTousLesEmprunteurs() {
        try {
            List<EmprunteurDto> emprunteurs = preposeService.getTousLesEmprunteurs();
            return ResponseEntity.ok(new EmprunteursListDto(emprunteurs));
        } catch (EmprunteurNonDisponibleException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new EmprunteursListDto(e.getMessage()));
        }
    }

    /**
     * Récupérer les emprunteurs avec emprunts actifs
     */
    @GetMapping("/emprunteurs/actifs")
    public ResponseEntity<?> getEmprunteursAvecEmpruntsActifs() {
        try {
            var emprunteurs = preposeService.getEmprunteursAvecEmpruntsActifs();
            return ResponseEntity.ok(emprunteurs);
        } catch (EmprunteurNonDisponibleException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur: " + e.getMessage());
        }
    }
}