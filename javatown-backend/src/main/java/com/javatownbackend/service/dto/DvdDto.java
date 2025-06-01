package com.javatownbackend.service.dto;

import com.javatownbackend.models.Dvd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DvdDto extends DocumentDto {
    private String director;
    private int dureeDvd;
    private String rating;

    public static DvdDto toDTO(Dvd dvd) {
        DvdDto dto = new DvdDto();
        dto.setId(dvd.getId());
        dto.setTitre(dvd.getTitre());
        dto.setNombreExemplaires(dvd.getNombreExemplaires());
        dto.setAnneePublication(dvd.getAnneePublication());
        dto.setType("DVD");
        dto.setDirector(dvd.getDirector());
        dto.setDureeDvd(dvd.getDuree());

        return dto;
    }
}