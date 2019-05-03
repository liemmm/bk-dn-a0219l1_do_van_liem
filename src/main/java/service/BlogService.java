package service;

import model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Page<Blog> findAll(Pageable pageable);
    Page<Blog> findAllByTitleContaining(String firstName, Pageable pageable);
    void save(Blog blog);
    void remove(Long id);
    Blog findById(Long id);
}
