package mk.ukim.finki.library.web.rest;

import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Country;
import mk.ukim.finki.library.model.exceptions.CoutryNotFoundException;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/author")
public class AuthorRestController {

    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorRestController(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping
    public List<Author> findAll() {
        return this.authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id) {
        return this.authorService.findById(id).map(author -> ResponseEntity.ok().body(author)).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/add")
    public ResponseEntity<Author> save(@RequestParam String name, @RequestParam String surname, @RequestParam Long country){
       Optional<Country> country1=this.countryService.findById(country);
       return this.authorService.save(name,surname,country1.get().getId()).map(author -> ResponseEntity.ok().body(author)).orElseGet(()->ResponseEntity.badRequest().build());

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.authorService.deleteById(id);
        if(this.authorService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
