package com.pmr.graphql.repository;

import com.pmr.graphql.model.Author;
import graphql.com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class AuthorRepository {
    private final List<Author> authors = new ArrayList<>();

    public AuthorRepository() {
        authors.add(new Author("1", "A1", "thumb1"));
        authors.add(new Author("2", "A2", "thumb2"));
    }

    public Author getAuthor(String id) {
        log.info("Author Id: {} ", id);
        return authors.stream()
                .filter(author -> id.equals(author.getId()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
