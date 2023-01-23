package com.pmr.graphql.repository;

import com.pmr.graphql.model.Post;
import graphql.com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class PostRepository {

    private List<Post> posts = new ArrayList<>();

    public PostRepository() {
        posts.add(new Post("1", "title1", "text1", "cat1", "1"));
        posts.add(new Post("2", "title2", "text2", "cat2", "2"));
    }

    public Post save(Post post) {
        posts.add(post);
        return post;
    }

    public List<Post> getAllPosts() {
        return posts;
    }

    public Post getPostById(String id) {
        log.info("Get Post By Id {}", id);
        return posts.stream().filter(post -> id.equals(post.getId())).findFirst().orElse(null);
    }

    public List<Post> getRecentPosts(int count, int offset) {
        return posts.stream()
                .skip(offset)
                .limit(count)
                .collect(Collectors.toList());
    }

    public List<Post> getAuthorPosts(String author) {
        return posts.stream()
                .filter(post -> author.equals(post.getAuthorId()))
                .collect(Collectors.toList());
    }
}
