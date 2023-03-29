package mk.ukim.finki.library.web.rest;

import mk.ukim.finki.library.model.Books;
import mk.ukim.finki.library.model.dto.BooksDto;
import mk.ukim.finki.library.service.BooksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BooksRestController {

    private final BooksService booksService;

    public BooksRestController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    private List<Books> findAll() {
        return this.booksService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Books> findById(@PathVariable Long id) {
        return this.booksService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Books> save(@RequestBody BooksDto booksDto) {
        return this.booksService.save(booksDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Books> save(@PathVariable Long id, @RequestBody BooksDto booksDto) {
        return this.booksService.edit(id, booksDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.booksService.deleteById(id);
        if(this.booksService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

//    @PostMapping("/mark/{id}")
//    public Books availableCopies(@PathVariable Long id){
//        return this.booksService.availableCopies(id);
//    }

    @PutMapping("/mark/{id}")
    public ResponseEntity<Books> availableCopies(@PathVariable Long id){
        return this.booksService.availableCopies(id).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
