package io.study.security_basic.controller;

import io.study.security_basic.service.PostService;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class PostControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Mock
    PostService postService;


}