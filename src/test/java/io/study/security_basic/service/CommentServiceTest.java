package io.study.security_basic.service;

import io.study.security_basic.Entity.*;
import io.study.security_basic.repository.CommentRepository;
import io.study.security_basic.repository.PostRepository;
import io.study.security_basic.repository.PostTagRepository;
import io.study.security_basic.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @InjectMocks
    PostService postService;

    @Mock
    PostRepository postRepository;

    @Mock
    PostTagRepository postTagRepository;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("d")
    public void test1() {
        //given
        User user = User.builder()
                .id(1L)
                .build();

        Post post = Post.builder().id(1L).build();
        List<Tag> tags = List.of();
        Category category = Category.builder().build();

        List<PostTag> postTags = new ArrayList<>();
        PostTag postTag = PostTag
                .builder()
                .tag(Tag.builder().build())
                .post(Post.builder().build())
                .build();

        postTags.add(postTag);

        given(userRepository.findById(1L)).willReturn(Optional.of(user));
//        given(postTagRepository.saveAll(postTags)).willReturn(postTags);
        given(postRepository.save(post)).willReturn(post);

        //when
        Long savedId = postService.savePost(post, tags, category);

        //then
        Assertions.assertThat(savedId).isEqualTo(1L);
    }
}