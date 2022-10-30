package io.study.security_basic.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.study.security_basic.Entity.Category;
import io.study.security_basic.Entity.Post;
import io.study.security_basic.Entity.Tag;
import io.study.security_basic.SecurityTestConfig;
import io.study.security_basic.config.SecurityConfig;
import io.study.security_basic.controller.PostController;
import io.study.security_basic.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@WebMvcTest(PostController.class)
@MockBean(JpaMetamodelMappingContext.class)
@Import(SecurityTestConfig.class)
class PostDtoTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PostService postService;

    @Test
    @DisplayName(" d ")
    public void test() throws Exception {
        //given
        PostDto.RequestPost request = PostDto.RequestPost
                .builder()
                .title("title")
                .content("content")
                .tags(List.of("dd", "dd", "aa"))
                .categoryName("das")
                .build();

        String json = objectMapper.writeValueAsString(request);

        given(postService.savePost(any(Post.class), any(List.class), any(Category.class))).willReturn(1L);

        //when
        ResultActions perform = mockMvc
                .perform(post("/posts")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON));

        //then
        perform
                .andExpect(status().isCreated());
    }
}