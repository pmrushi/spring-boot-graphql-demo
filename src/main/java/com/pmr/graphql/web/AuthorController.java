package com.pmr.graphql.web;

import com.pmr.graphql.model.Author;
import com.pmr.graphql.model.Post;
import com.pmr.graphql.repository.PostRepository;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController {

    private final PostRepository postRepository;

    public AuthorController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @SchemaMapping
    public List<Post> posts(Author author) {
        return postRepository.getAuthorPosts(author.getId());
    }

}
