package com.javatownbackend.service;

import com.javatownbackend.models.*;
import com.javatownbackend.persistence.*;
import com.javatownbackend.service.dto.DocumentDto;
import com.javatownbackend.service.dto.DtoConverter;
import com.javatownbackend.service.dto.EmprunteurDto;
import com.javatownbackend.service.execptions.CreerNouveauDocumentException;
import com.javatownbackend.service.execptions.DocumentNonDisponibleException;
import com.javatownbackend.service.execptions.EmprunteurNonDisponibleException;
import com.javatownbackend.service.execptions.PreposeException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PreposeService {
    private final EmprunteurRepository emprunteurRepository;
    private final DocumentRepository documentRepository;
    private final PreposeRepository preposeRepository;
    private final CdRepository cdRepository;
    private final DvdRepository dvdRepository;
    private final LivreRepository livreRepository;
    private final DtoConverter dtoConverter;

    public PreposeService(PreposeRepository preposeRepository,
                          DocumentRepository documentRepository,
                          EmprunteurRepository emprunteurRepository,
                          CdRepository cdRepository,
                          DvdRepository dvdRepository,
                          LivreRepository livreRepository,
                          DtoConverter dtoConverter) {
        this.livreRepository = livreRepository;
        this.cdRepository = cdRepository;
        this.preposeRepository = preposeRepository;
        this.dvdRepository = dvdRepository;
        this.documentRepository = documentRepository;
        this.emprunteurRepository = emprunteurRepository;
        this.dtoConverter = dtoConverter;
    }

    @Transactional
    public void creerNouveauPrepose(String name, String email, String phoneNumber) throws PreposeException {
        try {
            preposeRepository.save(new Prepose(name, email, phoneNumber));
        } catch (Exception e) {
            throw new PreposeException("Erreur lors de la création du préposé: " + e.getMessage());
        }
    }

    @Transactional
    public void creerNouveauCD(String titre, int nombreExemplaires, int anneePublication, String artiste, int duree, String genre) throws CreerNouveauDocumentException {
        try {
            cdRepository.save(new Cd(titre, nombreExemplaires, anneePublication, artiste, duree, genre));
        }
        catch (Exception e) {
            throw new CreerNouveauDocumentException("Erreur lors de la création du CD: " + e.getMessage());
        }
    }

    @Transactional
    public void creerNouveauDVD(String titre, int nombreExemplaires, int anneePublication, String director, int duree) throws CreerNouveauDocumentException {
        try {
            dvdRepository.save(new Dvd(titre, nombreExemplaires, anneePublication, director, duree));
        } catch (Exception e) {
            throw new CreerNouveauDocumentException("Erreur lors de la création du DVD: " + e.getMessage());
        }
    }

    @Transactional
    public void creerNouveauLivre(String titre, int nombreExemplaires, int anneePublication, String auteur, String editeur, int nombrePages) throws CreerNouveauDocumentException {
        try{
            livreRepository.save(new Livre(titre, nombreExemplaires, anneePublication, auteur, editeur, nombrePages));
        } catch (Exception e) {
            throw new CreerNouveauDocumentException("Erreur lors de la création du Livre: " + e.getMessage());
        }
    }

    @Transactional
    public List<DocumentDto> getTousLesDocuments() throws DocumentNonDisponibleException {
        try {
        List<Document> documents = documentRepository.findAll();
        return documents.stream()
                .map(dtoConverter::convertDocumentToDto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DocumentNonDisponibleException("Erreur lors de la récupération des documents: " + e.getMessage());
        }
    }

    @Transactional
    public List<EmprunteurDto> getTousLesEmprunteurs() throws EmprunteurNonDisponibleException {
        try {
        List<Emprunteur> emprunteurs = emprunteurRepository.findAll();
        return EmprunteurDto.toDtoList(emprunteurs);
        } catch (Exception e) {
            throw new EmprunteurNonDisponibleException("Erreur lors de la récupération des emprunteurs: " + e.getMessage());
        }
    }

    @Transactional
    public List<DocumentDto> getDocumentsDisponibles() throws DocumentNonDisponibleException {
        try {
        List<Document> allDocuments = documentRepository.findAll();

        return allDocuments.stream()
                .filter(doc -> {
                    long empruntsActifs = doc.getEmpruntDetails().stream()
                            .filter(detail -> detail.getDateRetourActuelle() == null)
                            .count();

                    return empruntsActifs < doc.getNombreExemplaires();
                })
                .map(dtoConverter::convertDocumentToDto)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DocumentNonDisponibleException("Erreur lors de la récupération des documents disponibles: " + e.getMessage());
        }
    }

    @Transactional
    public List<Emprunteur> getEmprunteursAvecEmpruntsActifs() throws EmprunteurNonDisponibleException {
        try {
        return emprunteurRepository.findAll().stream()
                .filter(emprunteur ->
                        emprunteur.getEmprunts().stream()
                                .flatMap(emprunt -> emprunt.getEmpruntDetails().stream())
                                .anyMatch(detail -> detail.getDateRetourActuelle() == null)
                )
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new EmprunteurNonDisponibleException("Erreur lors de la récupération des emprunteurs avec emprunts actifs: " + e.getMessage());
        }
    }
}