package io.study.security_basic.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
public class MultipleResponseDto<T> {
    private List<T> data;
    private PageInfo pageInfo;
}
