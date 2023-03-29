package mk.ukim.finki.library.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BooksNotFoundException extends RuntimeException{
    public BooksNotFoundException(Long id) {
        super(String.format("Book with id: %d was not found", id));
    }
}
