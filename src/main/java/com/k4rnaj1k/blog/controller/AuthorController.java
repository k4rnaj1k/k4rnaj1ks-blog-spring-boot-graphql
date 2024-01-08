package com.k4rnaj1k.blog.controller;

import com.k4rnaj1k.blog.dto.AuthorInput;
import com.k4rnaj1k.blog.model.Author;
import com.k4rnaj1k.blog.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorRepository authorRepository;

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public Author newAuthor(@Argument AuthorInput author) {
        Author creatingAuthor = new Author();

        creatingAuthor.setUsername(author.username());
        creatingAuthor.setFirstName(author.firstName());

        return authorRepository.save(creatingAuthor);
    }

    @QueryMapping
    public List<Author> allAuthors() {
        return authorRepository.findAll();
    }

}
