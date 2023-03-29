package mk.ukim.finki.library.config;

import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Country;
import mk.ukim.finki.library.model.enumerations.Category;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.BooksService;
import mk.ukim.finki.library.service.CountryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class DataHolder {

    private final BooksService booksService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public DataHolder(BooksService booksService, AuthorService authorService, CountryService countryService) {
        this.booksService = booksService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @PostConstruct
    public void init(){
        Country country1=this.countryService.save("Texas", "North America");
        Country country2=this.countryService.save("England", "Europe");
        Optional <Author> author1=this.authorService.save("Colleen","Hoover", country1.getId());
        Optional <Author> author2=this.authorService.save("Tony","Strong", country2.getId());

        this.booksService.save("Ugly Love", Category.NOVEL, author1.get().getId(), 3);
        this.booksService.save("Ugly Love1", Category.NOVEL, author1.get().getId(), 3);
        this.booksService.save("It Ends with Us", Category.NOVEL, author1.get().getId(), 5);
        this.booksService.save("The Girl Before", Category.THRILLER, author2.get().getId(), 2);

    }
}
