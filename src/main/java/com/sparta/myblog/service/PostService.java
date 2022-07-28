package com.sparta.myblog.service;

import com.sparta.myblog.dto.PostRequestDto;
import com.sparta.myblog.entity.Post;
import com.sparta.myblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor // final로 선언된 곳 필드에 대해서 필요한 생성자를 알아서 만들어줌
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional//DB에 진짜 반영되야 함을 알려줌
    public Long update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        post.update(requestDto);
        return post.getId();
    }
}