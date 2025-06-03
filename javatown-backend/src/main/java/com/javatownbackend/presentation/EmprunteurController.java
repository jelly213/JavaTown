package com.javatownbackend.presentation;

import com.javatownbackend.service.EmprunteurService;
import com.javatownbackend.service.dto.DocumentDto;
import com.javatownbackend.service.dto.DocumentListDto;
import com.javatownbackend.service.dto.EmpruntDetailDto;
import com.javatownbackend.service.dto.EmpruntDetailListDto;
import com.javatownbackend.service.execptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprunteur")
@CrossOrigin(origins = "*")
public class EmprunteurController {

    private final EmprunteurService emprunteurService;

    public EmprunteurController(EmprunteurService emprunteurService) {
        this.emprunteurService = emprunteurService;
    }

    /**
     * Créer un nouvel emprunteur
     */
    @PostMapping("/register")
    public ResponseEntity<String> creerNouvelEmprunteur(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phoneNumber) {
        try {
            emprunteurService.creerNouvelEmprunteur(name, email, phoneNumber);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Emprunteur créé avec succès");
        } catch (CreerNouveauEmprunteurException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la création de l'emprunteur: " + e.getMessage());
        }
    }

    /**
     * Rechercher un document par titre
     */
    @GetMapping("/documents/recherche/titre")
    public ResponseEntity<DocumentDto> rechercheDocumentParTitre(@RequestParam String titre) {
        try {
            DocumentDto document = emprunteurService.rechercheDocumentParTitre(titre);
            return ResponseEntity.ok(document);
        } catch (DocumentNonDisponibleException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DocumentDto(e.getMessage()));
        }
    }

    /**
     * Rechercher des documents par auteur
     */
    @GetMapping("/documents/recherche/auteur")
    public ResponseEntity<DocumentListDto> rechercheDocumentParAuteur(@RequestParam String auteur) {
        try {
            List<DocumentDto> documents = emprunteurService.rechercheDocumentParAuteur(auteur);
            return ResponseEntity.ok(new DocumentListDto(documents));
        } catch (DocumentNonDisponibleException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DocumentListDto(e.getMessage()));
        }
    }

    /**
     * Rechercher des documents par année de publication
     */
    @GetMapping("/documents/recherche/annee")
    public ResponseEntity<DocumentListDto> rechercheDocumentParAnneePublication(@RequestParam long anneePublication) {
        try {
            List<DocumentDto> documents = emprunteurService.rechercheDocumentParAnneePublication(anneePublication);
            return ResponseEntity.ok(new DocumentListDto(documents));
        } catch (DocumentNonDisponibleException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DocumentListDto(e.getMessage()));
        }
    }

    /**
     * Rechercher des documents par catégorie
     */
    @GetMapping("/documents/recherche/categorie")
    public ResponseEntity<DocumentListDto> rechercheDocumentParCategorie(@RequestParam String categorie) {
        try {
            List<DocumentDto> documents = emprunteurService.rechercheDocumentParCategorie(categorie);
            return ResponseEntity.ok(new DocumentListDto(documents));
        } catch (DocumentNonDisponibleException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DocumentListDto(e.getMessage()));
        }
    }

    /**
     * Emprunter un document
     */
    @PostMapping("/emprunts")
    public ResponseEntity<String> emprunterDocument(
            @RequestParam long idEmprunteur,
            @RequestParam long idDocument) {
        try {
            emprunteurService.emprunterDocument(idEmprunteur, idDocument);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Document emprunté avec succès");
        } catch (EmpruntNonDisponibleException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de l'emprunt: " + e.getMessage());
        }
    }

    /**
     * Retourner un document
     */
    @PutMapping("/emprunts/{idEmpruntDetail}/retour")
    public ResponseEntity<String> retournerDocument(@PathVariable long idEmpruntDetail) {
        try {
            emprunteurService.retournerDocument(idEmpruntDetail);
            return ResponseEntity.ok("Document retourné avec succès");
        } catch (RetournerDocumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors du retour: " + e.getMessage());
        }
    }

    /**
     * Retourner un document en retard (pour simulation)
     */
    @PutMapping("/emprunts/{idEmpruntDetail}/retour-retard")
    public ResponseEntity<String> retournerDocumentEnRetard(@PathVariable long idEmpruntDetail) {
        try {
            emprunteurService.retournerDocumentEnRetard(idEmpruntDetail);
            return ResponseEntity.ok("Document retourné en retard avec succès");
        } catch (RetournerDocumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors du retour: " + e.getMessage());
        }
    }

    /**
     * Payer une amende
     */
    @PutMapping("/amendes/{idAmende}/payer")
    public ResponseEntity<String> payerAmende(@PathVariable long idAmende) {
        try {
            emprunteurService.payerAmende(idAmende);
            return ResponseEntity.ok("Amende payée avec succès");
        } catch (PayerAmendeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors du paiement: " + e.getMessage());
        }
    }

    /**
     * Récupérer les emprunts retournés d'un emprunteur
     */
    @GetMapping("/{idEmprunteur}/emprunts/retournes")
    public ResponseEntity<EmpruntDetailListDto> getEmpruntsRetournes(@PathVariable Long idEmprunteur) {
        try {
            List<EmpruntDetailDto> emprunts = emprunteurService.getEmpruntsRetournesParEmprunteur(idEmprunteur);
            return ResponseEntity.ok(new EmpruntDetailListDto(emprunts));
        } catch (EmpruntNonDisponibleException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new EmpruntDetailListDto(e.getMessage()));
        }
    }

    /**
     * Récupérer les emprunts actifs d'un emprunteur
     */
    @GetMapping("/{idEmprunteur}/emprunts/actifs")
    public ResponseEntity<EmpruntDetailListDto> getEmpruntsActifs(@PathVariable Long idEmprunteur) {
        try {
            List<EmpruntDetailDto> emprunts = emprunteurService.getEmpruntsActifsParEmprunteur(idEmprunteur);
            return ResponseEntity.ok(new EmpruntDetailListDto(emprunts));
        } catch (EmpruntNonDisponibleException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new EmpruntDetailListDto(e.getMessage()));
        }
    }
}