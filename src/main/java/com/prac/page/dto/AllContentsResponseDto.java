package com.prac.page.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Builder
@Getter
public class AllContentsResponseDto<T> {    // T = schedule

    private final List<T> content;
    private final boolean hasNext;

    public AllContentsResponseDto(List<T> content, boolean hasNext) {
        this.content = content;
        this.hasNext = hasNext;
    }


}
