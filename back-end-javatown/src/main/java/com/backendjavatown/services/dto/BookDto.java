package com.backendjavatown.services.dto;

import com.backendjavatown.models.Book;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto extends DocumentDto {
    private String author;
    private String editor;
    private int numberOfPages;
    private int publicationYear;

    public BookDto(Long id, String title, int copies, int maxLoanDays,
                   String author, String editor, int numberOfPages, int publicationYear) {
        super(id, title, copies, maxLoanDays);
        this.author = author;
        this.editor = editor;
        this.numberOfPages = numberOfPages;
        this.publicationYear = publicationYear;
    }

    public static BookDto toDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitre(),
                book.getCopies(),
                book.getMaxLoanDays(),
                book.getAuthor(),
                book.getEditor(),
                book.getNumberOfPages(),
                book.getPublicationYear()
        );
    }
}