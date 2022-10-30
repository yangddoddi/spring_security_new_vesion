package io.study.security_basic.mapper;

import io.study.security_basic.Entity.Category;
import io.study.security_basic.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "name", source = "categoryName")
    Category convertStringToCategory(PostDto.RequestPost dto);
}
