package com.javatownbackend.service;



import com.javatownbackend.models.*;
import com.javatownbackend.persistence.AmendeRepository;
import com.javatownbackend.persistence.DocumentRepository;
import com.javatownbackend.persistence.EmpruntDetailRepository;
import com.javatownbackend.persistence.EmprunteurRepository;
import com.javatownbackend.service.dto.DocumentDto;
import com.javatownbackend.service.dto.DtoConverter;
import com.javatownbackend.service.dto.EmpruntDetailDto;
import com.javatownbackend.service.execptions.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class EmprunteurService {
    private final EmprunteurRepository emprunteurRepository;
    private final DocumentRepository documentRepository;
    private final EmpruntDetailRepository empruntDetailRepository;
    private final AmendeRepository amendeRepository;
    private final DtoConverter dtoConverter;

    public EmprunteurService(EmprunteurRepository emprunteurRepository, DocumentRepository documentRepository, EmpruntDetailRepository empruntDetailRepository, AmendeRepository amendeRepository, DtoConverter dtoConverter) {
        this.emprunteurRepository = emprunteurRepository;
        this.documentRepository = documentRepository;
        this.empruntDetailRepository = empruntDetailRepository;
        this.amendeRepository = amendeRepository;
        this.dtoConverter = dtoConverter;

    }

    @Transactional
    public void creerNouvelEmprunteur(String name, String email, String phoneNumber) throws CreerNouveauEmprunteurException {
        try {
            if (emprunteurRepository.existsByEmail(email)) {
                throw new CreerNouveauEmprunteurException("Un emprunteur avec cet email existe déjà");
            }
            emprunteurRepository.save(new Emprunteur(name, email, phoneNumber));
        }
        catch (Exception e) {
            throw new CreerNouveauEmprunteurException("Erreur technique lors de la création de l'emprunteur: " + e.getMessage());
        }
    }

    @Transactional
    public DocumentDto rechercheDocumentParTitre(String titre) throws DocumentNonDisponibleException {
        DocumentDto result;

        try {
            List<Document> documents = documentRepository.findByTitreContainingIgnoreCase(titre);
            System.out.println("Nombre de documents trouvés : " + documents.size());

            if (documents.isEmpty()) {
                throw new DocumentNonDisponibleException("Aucun document trouvé avec le titre : " + titre);
            } else {
                Document doc = documents.getFirst();
                result = dtoConverter.convertDocumentToDto(doc);
            }
        } catch (DocumentNonDisponibleException e) {
            throw e;
        } catch (Exception e) {
            throw new DocumentNonDisponibleException("Erreur technique lors de la recherche du document");
        }

        return result;
    }

    @Transactional
    public List<DocumentDto> rechercheDocumentParAuteur(String auteur) throws DocumentNonDisponibleException {
        List<DocumentDto> result;

        try {
            List<Document> documents = documentRepository.findByAuteur(auteur);

            if (documents.isEmpty()) {
                throw new DocumentNonDisponibleException("Aucun document trouvé pour l'auteur : " + auteur);
            } else {
                result = documents.stream()
                        .map(dtoConverter::convertDocumentToDto)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
            }
        } catch (DocumentNonDisponibleException e) {
            throw e;
        } catch (Exception e) {
            throw new DocumentNonDisponibleException("Erreur technique lors de la recherche des documents");
        }

        return result;
    }

    @Transactional
    public List<DocumentDto> rechercheDocumentParAnneePublication(long anneePublication) throws DocumentNonDisponibleException {
        List<DocumentDto> result;

        try {
            List<Document> documents = documentRepository.findByAnneePublication(anneePublication);

            if (documents.isEmpty()) {
                throw new DocumentNonDisponibleException("Aucun document trouvé pour l'année : " + anneePublication);
            } else {
                result = documents.stream()
                        .map(dtoConverter::convertDocumentToDto)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
            }
        } catch (DocumentNonDisponibleException e) {
            throw e;
        } catch (Exception e) {
            throw new DocumentNonDisponibleException("Erreur technique lors de la recherche des documents");
        }

        return result;
    }

    public List<DocumentDto> rechercheDocumentParCategorie(String categorie) throws DocumentNonDisponibleException {
        List<DocumentDto> result;
        try {
            List<Document> documents = documentRepository.findByTypeDocument(categorie);

            if (documents.isEmpty()) {
                throw new DocumentNonDisponibleException("Aucun document trouvé pour la catégorie : " + categorie);
            } else {
                result = documents.stream()
                        .map(dtoConverter::convertDocumentToDto)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
            }
        } catch (DocumentNonDisponibleException e) {
            throw e;
        } catch (Exception e) {
            throw new DocumentNonDisponibleException("Erreur technique lors de la recherche des documents");
        }

        return result;
    }

    @Transactional
    public void emprunterDocument(long idEmprunteur, long idDocument) throws EmpruntNonDisponibleException {
        try {
            // Récupérer l'emprunteur
            Emprunteur emprunteur = emprunteurRepository.findById(idEmprunteur)
                    .orElseThrow(() -> new EmpruntNonDisponibleException("Emprunteur introuvable avec l'ID: " + idEmprunteur));

            // Vérifier les amendes impayées
            boolean aAmendeImpayee = emprunteur.getAmendes().stream()
                    .anyMatch(amende -> !amende.isStatus());

            if (aAmendeImpayee) {
                throw new EmpruntNonDisponibleException("Impossible d'emprunter, des amendes sont impayées.");
            }

            // Récupérer le document
            Document document = documentRepository.findById(idDocument)
                    .orElseThrow(() -> new EmpruntNonDisponibleException("Document introuvable avec l'ID: " + idDocument));

            // Vérifier la disponibilité
            int empruntsActifs = empruntDetailRepository.getNombreEmpruntsParDocument(idDocument);
            if (empruntsActifs >= document.getNombreExemplaires()) {
                throw new EmpruntNonDisponibleException("Document non disponible pour emprunt.");
            }

            // Créer un nouvel emprunt
            LocalDate dateEmprunt = LocalDate.now();
            LocalDate dateRetourPrevue = dateEmprunt.plusDays(document.getDureeEmpruntJours());

            Emprunt emprunt = new Emprunt(emprunteur, dateEmprunt);
            EmpruntDetail detail = new EmpruntDetail(document, dateRetourPrevue);
            detail.setEmprunt(emprunt);
            emprunt.add(detail);

            emprunteur.addEmprunts(emprunt);

            // Sauvegarder
            emprunteurRepository.save(emprunteur);
            documentRepository.save(document);

            System.out.println("Document emprunté avec succès. Date de retour prévue: " + dateRetourPrevue);
        } catch (Exception e) {
            throw new EmpruntNonDisponibleException("Erreur technique lors de l'emprunt du document." + e.getMessage());
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Transactional
    public void retournerDocument(long idEmpruntDetail) throws RetournerDocumentException {
        try {
            // Récupérer l'emprunt
            EmpruntDetail empruntDetail = empruntDetailRepository.findById(idEmpruntDetail)
                    .orElseThrow(() -> new RetournerDocumentException("EmpruntDetail introuvable"));

            // Vérifier si le document est déjà retourné
            if (empruntDetail.getDateRetourActuelle() != null) {
                throw new RetournerDocumentException("Le document a déjà été retourné.");
            }

            // Mettre à jour la date de retour
            LocalDate dateRetourPrevue = empruntDetail.getDateRetourPrevue();
            LocalDate dateRetourActuelle = LocalDate.now();
            empruntDetail.setDateRetourActuelle(dateRetourActuelle);

            // Verifier si des amendes sont dues
            if (dateRetourActuelle.isAfter(dateRetourPrevue)) {
                long joursRetard = ChronoUnit.DAYS.between(dateRetourPrevue, dateRetourActuelle);
                double montantAmende = joursRetard * Amende.getFraiDeRetard();
                Emprunteur emprunteur = empruntDetail.getEmprunteur();

                Amende amende = new Amende(empruntDetail.getEmprunt().getEmprunteur(), montantAmende);
                amende.setDateCreation(dateRetourActuelle);
                emprunteur.addAmende(amende);

                System.out.println("Document retourné avec " + joursRetard + " jours de retard. Amende de " + montantAmende + "$ créée.");
            } else {
                System.out.println("Document retourné dans les délais.");
            }

            empruntDetailRepository.save(empruntDetail);
        } catch (Exception e) {
            throw new RetournerDocumentException("Erreur technique lors du retour du document " + e.getMessage());
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Transactional
    public void retournerDocumentEnRetard(long idEmpruntDetail) throws RetournerDocumentException {
        try {
            // Récupérer l'emprunt
            EmpruntDetail empruntDetail = empruntDetailRepository.findById(idEmpruntDetail)
                    .orElseThrow(() -> new RetournerDocumentException("EmpruntDetail introuvable"));

            // Vérifier si le document est déjà retourné
            if (empruntDetail.getDateRetourActuelle() != null) {
                throw new RetournerDocumentException("La retour du emprunteur du document.");
            }

            // Mettre à jour la date de retour
            LocalDate dateRetourPrevue = empruntDetail.getDateRetourPrevue();
            LocalDate dateRetourActuelle = LocalDate.now().plusDays(40);
            empruntDetail.setDateRetourActuelle(dateRetourActuelle);

            // Verifier si des amendes sont dues
            if (dateRetourActuelle.isAfter(dateRetourPrevue)) {
                long joursRetard = ChronoUnit.DAYS.between(dateRetourPrevue, dateRetourActuelle);
                double montantAmende = joursRetard * Amende.getFraiDeRetard();
                Emprunteur emprunteur = empruntDetail.getEmprunteur();

                Amende amende = new Amende(empruntDetail.getEmprunt().getEmprunteur(), montantAmende);
                amende.setDateCreation(dateRetourActuelle);
                emprunteur.addAmende(amende);

                System.out.println("Document retourné avec " + joursRetard + " jours de retard. Amende de " + montantAmende + "$ créée.");
            } else {
                System.out.println("Document retourné dans les délais.");
            }

            empruntDetailRepository.save(empruntDetail);
        } catch (Exception e) {
            throw new RetournerDocumentException("Erreur technique lors du retour du document "+ e.getMessage());
        }
    }

    public void payerAmende(long idAmende) throws PayerAmendeException {
        try {
            Amende amende = amendeRepository.findById(idAmende)
                    .orElseThrow(() -> new PayerAmendeException("Amende de " + idAmende + " introuvable"));

            if (amende.isStatus()) {
                throw new PayerAmendeException("Amende de " + idAmende + " introuvable");
            }

            amende.setStatus(true);
            amendeRepository.save(amende);
            System.out.println("Amende payée avec succès. Montant: " + amende.getMontant() + "$");
        } catch (Exception e) {
            throw new PayerAmendeException("Erreur technique lors du paiement de l'amende : " + e.getMessage());
        }
    }

    @Transactional
    public List<EmpruntDetailDto> getEmpruntsRetournesParEmprunteur(Long idEmprunteur) throws EmpruntNonDisponibleException {
        try {
            // Récupérer l'emprunteur
            Emprunteur emprunteur = emprunteurRepository.findById(idEmprunteur)
                    .orElseThrow(() -> new RuntimeException("Emprunteur introuvable avec l'ID: " + idEmprunteur));

            // Filtrer tous les emprunts pour ne récupérer que les détails d'emprunts retournés
            // et les convertir en DTOs
            return emprunteur.getEmprunts().stream()
                    .flatMap(emprunt -> emprunt.getEmpruntDetails().stream())
                    .filter(detail -> detail.getDateRetourActuelle() != null)
                    .map(EmpruntDetailDto::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new EmpruntNonDisponibleException("Erreur lors de la récupération des emprunts retournés : " + e.getMessage());
        }
    }

    @Transactional
    public List<EmpruntDetailDto> getEmpruntsActifsParEmprunteur(Long idEmprunteur) throws EmpruntNonDisponibleException {
        try {
            // Récupérer l'emprunteur
            Emprunteur emprunteur = emprunteurRepository.findById(idEmprunteur)
                    .orElseThrow(() -> new RuntimeException("Emprunteur introuvable avec l'ID: " + idEmprunteur));

            // Filtrer tous les emprunts pour ne récupérer que les détails d'emprunts actifs
            // et les convertir en DTOs
            return emprunteur.getEmprunts().stream()
                    .flatMap(emprunt -> emprunt.getEmpruntDetails().stream())
                    .filter(detail -> detail.getDateRetourActuelle() == null)
                    .map(EmpruntDetailDto::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new EmpruntNonDisponibleException("Erreur lors de la récupération des emprunts actifs : " + e.getMessage());
        }
    }
}