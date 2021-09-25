package com.prac.page.dto;

import com.prac.page.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class AllScheduleWithSliceResponseDto {  // temporary

    private final List<AllScheduleResponseDto> content;

    private final boolean sliceHasNext;

    private final boolean sliceIsLast;

    public static AllScheduleWithSliceResponseDto of(List<AllScheduleResponseDto> content, Slice<Schedule> scheduleSlice) {
        return AllScheduleWithSliceResponseDto.builder()
                .content(content)
                .sliceHasNext(scheduleSlice.hasNext())
                .sliceIsLast(scheduleSlice.isLast())
                .build();
    }

}
