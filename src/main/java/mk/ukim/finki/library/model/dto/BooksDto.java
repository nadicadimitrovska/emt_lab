package mk.ukim.finki.library.model.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


import lombok.Data;
import mk.ukim.finki.library.model.enumerations.Category;

@Data
public class BooksDto {
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private Long author;
    private Integer availableCopies;

    public BooksDto() {
    }

    public BooksDto(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
