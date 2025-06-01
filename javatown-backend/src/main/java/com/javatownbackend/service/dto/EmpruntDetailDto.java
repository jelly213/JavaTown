package com.javatownbackend.service.dto;

import com.javatownbackend.models.EmpruntDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpruntDetailDto {
    private long id;
    private String titre;
    private String type;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourActuelle;

    public static EmpruntDetailDto toDto(EmpruntDetail empruntDetail) {
        EmpruntDetailDto dto = new EmpruntDetailDto();
        dto.setId(empruntDetail.getId());
        dto.setTitre(empruntDetail.getDocument().getTitre());

        // Déterminer le type basé sur la classe du document
        String type = switch (empruntDetail.getDocument().getClass().getSimpleName()) {
            case "Livre" -> "Livre";
            case "Cd" -> "CD";
            case "Dvd" -> "DVD";
            default -> "Document";
        };
        dto.setType(type);

        dto.setDateEmprunt(empruntDetail.getEmprunt().getDateEmprunt());
        dto.setDateRetourPrevue(empruntDetail.getDateRetourPrevue());
        dto.setDateRetourActuelle(empruntDetail.getDateRetourActuelle());
        return dto;
    }
}