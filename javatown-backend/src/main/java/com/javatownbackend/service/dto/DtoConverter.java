package com.javatownbackend.service.dto;

import com.javatownbackend.models.Cd;
import com.javatownbackend.models.Document;

import com.javatownbackend.models.Dvd;
import com.javatownbackend.models.Livre;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    public DocumentDto convertDocumentToDto(Document doc) {
        DocumentDto dto = null;

        if (doc != null) {
            switch (doc) {
                case Livre livre -> dto = LivreDto.toDTO(livre);
                case Cd cd -> dto = CdDto.toDTO(cd);
                case Dvd dvd -> dto = DvdDto.toDTO(dvd);
                default -> System.out.println("Type de document inconnu : " + doc.getClass().getSimpleName());
            }
        }

        return dto;
    }
}