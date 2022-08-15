package com.sparta.hw03.service;

import com.sparta.hw03.entity.Post;
import com.sparta.hw03.repository.PostRepository;
import com.sparta.hw03.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository PostRepository;  //이걸 dto로 만들어서 controller로

    @Transactional
    public Long update(Long id, PostRequestDto requestDto) {
        Post post = PostRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        post.update(requestDto);
        return post.getId();
    }

}