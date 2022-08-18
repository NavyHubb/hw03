package com.sparta.hw03.controller;

import com.sparta.hw03.dto.PostResponseDto;
import com.sparta.hw03.entity.Post;
import com.sparta.hw03.repository.PostRepository;
import com.sparta.hw03.dto.PostRequestDto;
import com.sparta.hw03.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;


//    @GetMapping("/api/posts")
//    public List<Post> readPost() {
//        return postRepository.findAllByOrderByIdDesc();
//    }

    @GetMapping("/api/posts")
    public List<PostResponseDto> readPost() {
        List<Post> postList = postRepository.findAllByOrderByIdDesc();
        List<PostResponseDto> responseDtoList = new ArrayList<>();
        for (Post temp : postList) {
            PostResponseDto responseDto = postService.response(temp);
            responseDtoList.add(responseDto);
        }
        return responseDtoList;
    }

    @GetMapping("/api/posts/{id}")
    public PostResponseDto readPostById(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow(  // 수정을 하려면 일단 찾아야겠지 findById
                () -> new IllegalArgumentException("보여달라고? 그런 아이디 없는디?")
        );
        return postService.response(post);
    }

    @PostMapping("/api/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return postService.response(post);
    }


    @PutMapping("/api/posts/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        postService.update(id, requestDto);
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("보여달라고? 그런 아이디 없는디?")
        );
        return postService.response(post);
    }


    @DeleteMapping("/api/posts/{id}")
    public boolean deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return true;  // 이렇게 하는 거 맞나..??
    }

}