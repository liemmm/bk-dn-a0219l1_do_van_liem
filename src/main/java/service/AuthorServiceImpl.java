package service;

import model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import repository.AuthorRepository;

public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Page<Author> findAllByFirstNameContaining(String firstName, Pageable pageable) {
        return authorRepository.findAllByNameContaining(firstName, pageable);
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void remove(Long id) {
        authorRepository.delete(id);
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findOne(id);
    }
}
