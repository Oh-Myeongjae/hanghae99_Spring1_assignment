package com.sparta.myblog.contorller;

import com.sparta.myblog.dto.PostRequestDto;
import com.sparta.myblog.entity.Post;
import com.sparta.myblog.repository.PostRepository;
import com.sparta.myblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

//    @GetMapping("/api/post")
//    public Map<String,Object> getPosts() {
//        return new returnInfo().Info(true,postRepository.findAll(),null);
//    }
    @GetMapping("/api/post")
    public  returnInfo getPosts() {
        return new returnInfo(true,postRepository.findAll(),"null");
    }

    @GetMapping("/api/post/{id}")
    public Map<String,Object> getPost(@PathVariable long id) {
            return new returnInfo().Info(true,postRepository.findById(id),null);
    }


    @PostMapping("/api/post")
    public Map<String,Object> createPost(@RequestBody PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return new returnInfo().Info(true,post,null);
    }

    @PostMapping("/api/post/{id}")
    public Map<String,Object> checkPassword(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        boolean check = post.getPassword().equals(requestDto.getPassword());
        return new returnInfo().Info(true,check,null);
    }

    @PutMapping("/api/post/{id}")
    public Map<String,Object> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        Long idx = postService.update(id,requestDto);
        return  new returnInfo().Info(true,postRepository.findById(idx),null);
    }

    @DeleteMapping("/api/post/{id}")
    public Map<String,Object>deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return new returnInfo().Info(true,true,null);
    }
}