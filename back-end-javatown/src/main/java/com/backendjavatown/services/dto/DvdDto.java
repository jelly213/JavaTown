package com.backendjavatown.services.dto;

import com.backendjavatown.models.Dvd;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DvdDto extends DocumentDto {
    private String director;
    private int duration;

    public DvdDto(Long id, String title, int copies, int maxLoanDays,
                  String director, int duration) {
        super(id, title, copies, maxLoanDays);
        this.director = director;
        this.duration = duration;
    }

    public static DvdDto toDto(Dvd dvd) {
        return new DvdDto(
                dvd.getId(),
                dvd.getTitre(),
                dvd.getCopies(),
                dvd.getMaxLoanDays(),
                dvd.getDirector(),
                dvd.getDuration()
        );
    }
}