package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Optional<Country> findById(Long id);

    List<Country> findAll();

    Country save(String name, String continent);

    void deleteById(Long id);

}
