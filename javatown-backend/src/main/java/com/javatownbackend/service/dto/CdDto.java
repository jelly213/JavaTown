package com.javatownbackend.service.dto;

import com.javatownbackend.models.Cd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CdDto extends DocumentDto {
    private String artiste;
    private int dureeCd;
    private String genre;

    public static CdDto toDTO(Cd cd) {
        CdDto dto = new CdDto();
        dto.setId(cd.getId());
        dto.setTitre(cd.getTitre());
        dto.setNombreExemplaires(cd.getNombreExemplaires());
        dto.setAnneePublication(cd.getAnneePublication());
        dto.setType("CD");
        dto.setArtiste(cd.getArtiste());
        dto.setDureeCd(cd.getDuree());
        dto.setGenre(cd.getGenre());

        return dto;
    }
}

