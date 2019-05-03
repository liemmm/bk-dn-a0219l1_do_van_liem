package service;

import model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {
    Page<Author> findAll(Pageable pageable);
    Page<Author> findAllByFirstNameContaining(String firstName, Pageable pageable);
    void save(Author author);
    void remove(Long id);
    Author findById(Long id);
}
