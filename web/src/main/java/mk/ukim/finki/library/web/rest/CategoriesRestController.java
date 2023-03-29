package mk.ukim.finki.library.web.rest;

import mk.ukim.finki.library.model.enumerations.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoriesRestController {


    @GetMapping
    public ResponseEntity<Category[]>findCategories(){
        return ResponseEntity.ok(Category.values());
    }
}
