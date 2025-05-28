package com.backendjavatown.services.dto;
import com.backendjavatown.models.Cd;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CdDto extends DocumentDto{
    private String artist;
    private int duration;
    private String genre;

    public CdDto(Long id, String title, int copies, int maxLoanDays, String artist, int duration, String genre) {
        super(id, title, copies, maxLoanDays);
        this.artist = artist;
        this.duration = duration;
        this.genre = genre;
    }

    public static CdDto toDto(Cd cd) {
        return new CdDto(
                cd.getId(),
                cd.getTitre(),
                cd.getCopies(),
                cd.getMaxLoanDays(),
                cd.getArtist(),
                cd.getDuration(),
                cd.getGenre()
        );
    }
}