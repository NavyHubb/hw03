package com.sparta.hw03.controller;

import com.sparta.hw03.entity.Post;
import com.sparta.hw03.repository.PostRepository;
import com.sparta.hw03.dto.PostRequestDto;
import com.sparta.hw03.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @PostMapping("/api/post")
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    @GetMapping("/api/post")
    public List<Post> readPost() {
        return postRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/api/post/{id}")
    public Optional<Post> readPostById(@PathVariable Long id) {
        return postRepository.findById(id);
    }

    @DeleteMapping("/api/post/{id}")
    public Long deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }

}