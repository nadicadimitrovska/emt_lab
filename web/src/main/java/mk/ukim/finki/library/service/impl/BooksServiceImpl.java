package mk.ukim.finki.library.service.impl;


import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Books;
import mk.ukim.finki.library.model.dto.BooksDto;
import mk.ukim.finki.library.model.enumerations.Category;
import mk.ukim.finki.library.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.library.model.exceptions.BooksNotFoundException;
import mk.ukim.finki.library.repository.AuthorRepository;
import mk.ukim.finki.library.repository.BooksRepository;
import mk.ukim.finki.library.service.BooksService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BooksServiceImpl implements BooksService {

    private final BooksRepository booksRepository;
    private final AuthorRepository authorRepository;

    public BooksServiceImpl(BooksRepository booksRepository, AuthorRepository authorRepository) {
        this.booksRepository = booksRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Books> findAll() {
        return this.booksRepository.findAll();
    }

    @Override
    public Optional<Books> findById(Long id) {
        return this.booksRepository.findById(id);
    }

    @Override
    public Optional<Books> findByName(String name) {
        return this.booksRepository.findByName(name);
    }

    //todo deleteByName
    @Override
    public Optional<Books> save(String name, Category category, Long author, Integer availableCopies) {
        Author author1=this.authorRepository.findById(author).orElseThrow(()->new AuthorNotFoundException(author));

        Books books=new Books(name,category,author1,availableCopies);
        this.booksRepository.save(books);
        return Optional.of(books);
    }

    //todo deleteByName
    @Override
    public Optional<Books> save(BooksDto booksDto) {
        Author author1=this.authorRepository.findById(booksDto.getAuthor()).orElseThrow(()->new AuthorNotFoundException(booksDto.getAuthor()));

        Books books=new Books(booksDto.getName(),booksDto.getCategory(),author1, booksDto.getAvailableCopies());
        this.booksRepository.save(books);
        return Optional.of(books);
    }

    @Override
    public Optional<Books> edit(Long id, String name, Category category, Long author, Integer availableCopies) {
        Books books=this.booksRepository.findById(id).orElseThrow(()->new BooksNotFoundException(id));

        books.setName(name);
        books.setCategory(category);
        Author author1=this.authorRepository.findById(author).orElseThrow(()->new AuthorNotFoundException(author));

        books.setAuthor(author1);
        books.setAvailableCopies(availableCopies);
        this.booksRepository.save(books);

        return Optional.of(books);
    }

    @Override
    public Optional<Books> edit(Long id, BooksDto booksDto) {
        Books books=this.booksRepository.findById(id).orElseThrow(()->new BooksNotFoundException(id));

        books.setName(booksDto.getName());
        books.setCategory(booksDto.getCategory());
        Author author1=this.authorRepository.findById(booksDto.getAuthor()).orElseThrow(()->new AuthorNotFoundException(booksDto.getAuthor()));

        books.setAuthor(author1);
        books.setAvailableCopies(booksDto.getAvailableCopies());
        this.booksRepository.save(books);

        return Optional.of(books);
    }

    @Override
    public void deleteById(Long id) {
        this.booksRepository.deleteById(id);
    }


    @Override
    public Optional<Books> availableCopies(Long id) {
        Books books=this.booksRepository.findById(id).orElseThrow(()->new BooksNotFoundException(id));
        int availableCopies=books.getAvailableCopies();
        books.setAvailableCopies(--availableCopies);
        if (availableCopies<=0)
            books.setAvailableCopies(0);
        this.booksRepository.save(books);
        return Optional.of(books);
    }



}
