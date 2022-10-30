package io.study.security_basic.mapper;

import io.study.security_basic.Entity.Category;
import io.study.security_basic.dto.PostDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    @Test
    @DisplayName("PostDto.RequestPost to Category 테스트")
    public void test() {
        //given
        PostDto.RequestPost dto = PostDto.RequestPost
                .builder()
                .title("title")
                .content("content")
                .categoryName("categoty")
                .build();

        //when
        Category category = CategoryMapper.INSTANCE.convertStringToCategory(dto);

        //then
        Assertions.assertThat(category.getName()).isEqualTo(dto.getCategoryName());
    }
}