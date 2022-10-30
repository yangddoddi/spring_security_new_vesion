package io.study.security_basic.service;

import io.study.security_basic.Entity.*;
import io.study.security_basic.repository.PostRepository;
import io.study.security_basic.repository.PostTagRepository;
import io.study.security_basic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;
    private final UserRepository userRepository;

    public Long savePost(Post post, List<Tag> tags, Category category) {
        User user = userRepository.findById(1L).get();
        post.associateWithUser(user);

        List<PostTag> postTags = tags.stream()
                .map(e ->
                        PostTag
                                .builder()
                                .post(post)
                                .tag(e)
                                .build()
                )
                .collect(Collectors.toList());

        if (!postTags.isEmpty()) {
            List<PostTag> saved = postTagRepository.saveAll(postTags);
            return saved.get(0)
                    .getPost()
                    .getId();
        } else {
            Post saved = postRepository.save(post);
            return saved.getId();
        }
    }
}
