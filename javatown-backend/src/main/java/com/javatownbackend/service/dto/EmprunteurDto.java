package com.javatownbackend.service.dto;

import com.javatownbackend.models.Emprunteur;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmprunteurDto {
    private long id;
    private String name;
    private String email;
    private String phoneNumber;
    private int nombreEmprunts;
    private int nombreAmendes;
    private boolean aAmendesImpayees;

    // Méthode de conversion statique pour transformer un Emprunteur en EmprunteurDto
    public static EmprunteurDto toDTO(Emprunteur emprunteur) {
        EmprunteurDto dto = new EmprunteurDto();
        dto.setId(emprunteur.getId());
        dto.setName(emprunteur.getName());
        dto.setEmail(emprunteur.getEmail());
        dto.setPhoneNumber(emprunteur.getPhoneNumber());

        // Nombre total d'emprunts
        dto.setNombreEmprunts(emprunteur.getEmprunts().size());

        // Nombre total d'amendes
        dto.setNombreAmendes(emprunteur.getAmendes().size());

        // Vérification des amendes impayées
        dto.setAAmendesImpayees(
                emprunteur.getAmendes().stream()
                        .anyMatch(amende -> !amende.isStatus())
        );

        return dto;
    }

    // Méthode de conversion de liste pour faciliter les opérations de masse
    public static List<EmprunteurDto> toDtoList(List<Emprunteur> emprunteurs) {
        return emprunteurs.stream()
                .map(EmprunteurDto::toDTO)
                .collect(Collectors.toList());
    }
}