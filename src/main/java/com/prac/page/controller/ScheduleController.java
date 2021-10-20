package com.prac.page.controller;

import com.prac.page.common.ApiResponse;
import com.prac.page.dto.AllContentsResponseDto;
import com.prac.page.dto.AllScheduleResponseDto;
import com.prac.page.dto.AllScheduleWithSliceResponseDto;
import com.prac.page.entity.Schedule;
import com.prac.page.service.SchedulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.SliceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class ScheduleController {

    private final SchedulesService schedulesService;

    // 페이징
    @GetMapping("/schedules/page")
    public ApiResponse<Page<Schedule>> getAllSchedulesUsingPaging() {
        Pageable pageable = PageRequest.of(0, 8);
        Page<Schedule> all = schedulesService.getAllSchedulesUsingPaging(pageable);
        return ApiResponse.ok(all);
    }

    // 쿼리 메서드 사용 - 닉네임으로 가져오기
    @GetMapping("/schedules/page/queryMethod")
    public ApiResponse<Page<AllScheduleResponseDto>> getScheduleByQueryMethod() {
        Pageable pageable = PageRequest.of(0, 3);
        final Page<AllScheduleResponseDto> responseDtoSet = schedulesService.getScheduleByQueryMethod(pageable);
        return ApiResponse.ok(responseDtoSet);
    }

    // 쿼리 파라미터로 정보 받기
    @GetMapping("/schedules/page/queryParameter")
    public ApiResponse<Page<Schedule>> getScheduleByQueryParameter(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Schedule> all = schedulesService.getAllSchedulesUsingPaging(pageable);
        return ApiResponse.ok(all);
    }

    // spring web mvc 로 더 간단하게

    // Slice 로 받기
    @GetMapping("/schedules/mvc/slice")
    public ApiResponse<AllScheduleWithSliceResponseDto> getAllSchedulesWithSlice(Pageable pageable) {
        final AllScheduleWithSliceResponseDto all = schedulesService.getAllSchedulesWithSlice(pageable);
        return ApiResponse.ok(all);
    }

    // Slice 로 받기 2
    @GetMapping("/schedules/page/slice")
    public ApiResponse<SliceImpl<AllScheduleResponseDto>> getAllScheduleAndSlice(Pageable pageable) {
        return ApiResponse.ok(schedulesService.getAllScheduleAndSlice(pageable));
    }

    // Page 로 받기
    @GetMapping("/schedules/mvc/page")
    public ApiResponse<Page<AllScheduleResponseDto>> getAllSchedulesWithPage(Pageable pageable) {
        return ApiResponse.ok(schedulesService.getAllSchedulesWithPage(pageable));
    }

    // 페이징 처리 하지 않은 메소드
    @GetMapping("/")
    public ApiResponse<List<AllScheduleResponseDto>> getAllSchedule() {
        return ApiResponse.ok(schedulesService.getAllSchedule());
    }

}
