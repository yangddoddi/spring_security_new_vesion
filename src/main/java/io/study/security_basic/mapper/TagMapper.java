package io.study.security_basic.mapper;

import io.study.security_basic.Entity.Tag;
import io.study.security_basic.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    default List<Tag> toLists(PostDto.RequestPost dto) {
        return dto.getTags()
                .stream().map(
                        e -> Tag.builder()
                                .tagname(e)
                                .build()
                ).collect(Collectors.toList());
    }
}
