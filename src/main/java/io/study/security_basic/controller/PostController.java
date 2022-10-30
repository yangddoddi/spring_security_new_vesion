package io.study.security_basic.controller;

import io.study.security_basic.dto.PostDto;
import io.study.security_basic.mapper.CategoryMapper;
import io.study.security_basic.mapper.PostMapper;
import io.study.security_basic.mapper.TagMapper;
import io.study.security_basic.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long postPost(@RequestBody PostDto.RequestPost request) {
        return postService.savePost(PostMapper.INSTANCE.requestPostToPost(request), TagMapper.INSTANCE.toLists(request), CategoryMapper.INSTANCE.convertStringToCategory(request));
    }
}
