package com.k4rnaj1k.blog.repository;

import com.k4rnaj1k.blog.model.Author;
import com.k4rnaj1k.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findByBlogsContaining(Blog blog);
}
