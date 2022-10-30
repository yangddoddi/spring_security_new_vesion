package io.study.security_basic.mapper;

import io.study.security_basic.Entity.Post;
import io.study.security_basic.dto.PostDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-30T23:10:55+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.15.1 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post requestPostToPost(PostDto.RequestPost dto) {
        if ( dto == null ) {
            return null;
        }

        Post.PostBuilder post = Post.builder();

        post.title( dto.getTitle() );
        post.content( dto.getContent() );

        return post.build();
    }
}
