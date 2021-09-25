package com.prac.page.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AllScheduleWithSliceResponseDto {  // temporary

    private final List<AllScheduleResponseDto> contents;

    private final boolean sliceHasNext;

    public static AllScheduleWithSliceResponseDto of(List<AllScheduleResponseDto> contents, boolean sliceHasNext) {
        return new AllScheduleWithSliceResponseDto(contents, sliceHasNext);
    }

}
