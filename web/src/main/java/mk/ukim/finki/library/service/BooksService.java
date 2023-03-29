package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Books;
import mk.ukim.finki.library.model.dto.BooksDto;
import mk.ukim.finki.library.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BooksService {

    List<Books> findAll();
    Optional<Books>findById(Long id);
    Optional<Books>findByName(String name);
    Optional<Books> save(String name, Category category, Long author, Integer availableCopies);
    Optional<Books> save(BooksDto booksDto);
    Optional<Books> edit(Long id, String name, Category category, Long author, Integer availableCopies);
    Optional<Books> edit(Long id,BooksDto booksDto);
    void deleteById(Long id);
    Optional<Books> availableCopies(Long id);



}
