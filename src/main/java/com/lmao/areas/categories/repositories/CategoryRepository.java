package com.lmao.areas.categories.repositories;

import com.lmao.areas.categories.entities.Category;
import com.lmao.areas.comments.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
}
