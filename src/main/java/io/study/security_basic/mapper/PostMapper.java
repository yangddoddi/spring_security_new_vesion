package io.study.security_basic.mapper;

import io.study.security_basic.Entity.Post;
import io.study.security_basic.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post requestPostToPost(PostDto.RequestPost dto);
}
