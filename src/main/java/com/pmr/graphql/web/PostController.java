package com.pmr.graphql.web;

import com.pmr.graphql.model.Author;
import com.pmr.graphql.model.Post;
import com.pmr.graphql.repository.AuthorRepository;
import com.pmr.graphql.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class PostController {
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public PostController(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    public List<Post> allPosts() {
        System.out.println("******************");
        return postRepository.getAllPosts();
    }

    @QueryMapping
    public List<Post> recentPosts(@Argument int count, @Argument int offset) {
        return postRepository.getRecentPosts(count, offset);
    }

    @QueryMapping
    public Post postById(@Argument String id) {
        return postRepository.getPostById(id);
    }

    @SchemaMapping(typeName="Post", field="first_author")
    public Author getFirstAuthor(Post post) {
        log.info("Post  : {}", post);
        return authorRepository.getAuthor(post.getAuthorId());
    }

    @SchemaMapping(typeName="Post", field="author")
    public Author getAuthor(Post post) {
        log.info("Author --  : {} ", post);
        return authorRepository.getAuthor(post.getAuthorId());
    }

    @MutationMapping
    public Post createPost(@Argument String title, @Argument String text,
                           @Argument String category, @Argument String authorId) {
        Post post = new Post();
        post.setId(UUID.randomUUID().toString());
        post.setTitle(title);
        post.setText(text);
        post.setCategory(category);
        post.setAuthorId(authorId);
        postRepository.save(post);
        return post;
    }
}
