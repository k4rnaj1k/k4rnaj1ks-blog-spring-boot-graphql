package com.k4rnaj1k.blog.controller;

import com.k4rnaj1k.blog.dto.BlogInput;
import com.k4rnaj1k.blog.model.Author;
import com.k4rnaj1k.blog.model.Blog;
import com.k4rnaj1k.blog.repository.AuthorRepository;
import com.k4rnaj1k.blog.repository.BlogRepository;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BlogController {

    public BlogController(BlogRepository blogRepository, AuthorRepository authorRepository) {
        this.blogRepository = blogRepository;
        this.authorRepository = authorRepository;
    }

    private final BlogRepository blogRepository;
    private final AuthorRepository authorRepository;

    @QueryMapping
    public Blog blogById(@Argument Integer id) {
        return blogRepository.findById(id).orElseThrow();
    }

    @SchemaMapping
    public Author author(Blog blog) {
        return authorRepository.findByBlogsContaining(blog);
    }

    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public Blog newBlog(@Argument BlogInput blogInput) {
        Author blogAuthor = authorRepository.findById(blogInput.authorId()).orElseThrow();

        Blog blogToSave = new Blog();
        blogToSave.setTitle(blogInput.title());
        blogToSave.setAuthor(blogAuthor);
        blogToSave.setContent(blogInput.content());

        return blogRepository.save(blogToSave);
    }

    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public Blog editBlog(@Argument BlogInput blogInput, @Argument Integer id){
        Blog existBlog = blogRepository.findById(id).orElseThrow();
        Author newBlogAuthor = authorRepository.findById(blogInput.authorId()).orElseThrow();

        existBlog.setAuthor(newBlogAuthor);
        existBlog.setContent(blogInput.content());
        existBlog.setTitle(blogInput.title());

        return blogRepository.save(existBlog);
    }

    @QueryMapping
    public List<Blog> allBlogs() {
        return blogRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
}
