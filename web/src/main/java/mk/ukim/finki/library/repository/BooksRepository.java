package mk.ukim.finki.library.repository;

import mk.ukim.finki.library.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Books,Long> {
    Optional<Books> findByName(String name);
}
