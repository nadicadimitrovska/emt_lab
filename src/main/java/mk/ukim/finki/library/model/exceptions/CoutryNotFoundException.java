package mk.ukim.finki.library.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CoutryNotFoundException extends RuntimeException{
    public CoutryNotFoundException(Long id) {
        super(String.format("Country with id: %d was not found", id));
    }
}
