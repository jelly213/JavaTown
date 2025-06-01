package com.javatownbackend.service.dto;

import com.javatownbackend.models.Livre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivreDto extends DocumentDto {
    private String auteur;
    private String editeur;
    private int nombrePages;

    public static LivreDto toDTO(Livre livre) {
        LivreDto dto = new LivreDto();
        dto.setId(livre.getId());
        dto.setTitre(livre.getTitre());
        dto.setNombreExemplaires(livre.getNombreExemplaires());
        dto.setAnneePublication(livre.getAnneePublication());
        dto.setType("Livre");
        dto.setAuteur(livre.getAuteur());
        dto.setEditeur(livre.getEditeur());
        dto.setNombrePages(livre.getNombrePages());

        return dto;
    }
}