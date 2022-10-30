package io.study.security_basic.dto;

import lombok.*;

@Getter
@Builder
public final class PageInfo {
    private final int page;
    private final int size;
    private final long totalElements;
    private final int totalPages;

    public PageInfo(int page, int size, long totalElements, int totalPages) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}
