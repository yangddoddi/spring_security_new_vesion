package io.study.security_basic.mapper;

import io.study.security_basic.Entity.Tag;
import io.study.security_basic.dto.PostDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TagMapperTest {

    @Test
    @DisplayName("PostDto.RequestPost to List<Tag> test")
    public void test1() {
        // given
        PostDto.RequestPost dto = PostDto.RequestPost
                .builder()
                .title("제목")
                .content("내용")
                .tags(List.of("1", "2", "3"))
                .build();

        // when
        List<Tag> tags = TagMapper.INSTANCE.toLists(dto);

        // then
        Assertions.assertThat(tags.get(0).getTagname()).isEqualTo(dto.getTags().get(0));
        Assertions.assertThat(tags.get(1).getTagname()).isEqualTo(dto.getTags().get(1));
        Assertions.assertThat(tags.get(2).getTagname()).isEqualTo(dto.getTags().get(2));
    }
}