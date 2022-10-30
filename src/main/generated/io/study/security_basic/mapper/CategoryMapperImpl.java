package io.study.security_basic.mapper;

import io.study.security_basic.Entity.Category;
import io.study.security_basic.dto.PostDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-30T23:10:55+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.15.1 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category convertStringToCategory(PostDto.RequestPost dto) {
        if ( dto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( dto.getCategoryName() );

        return category.build();
    }
}
