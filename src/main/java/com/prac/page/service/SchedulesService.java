package com.prac.page.service;

import com.prac.page.dto.AllContentsResponseDto;
import com.prac.page.entity.Schedule;
import com.prac.page.dto.AllScheduleResponseDto;
import com.prac.page.dto.AllScheduleWithSliceResponseDto;
import com.prac.page.repository.SchedulesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class SchedulesService {

    private final SchedulesRepository schedulesRepository;


    // 안좋은 사례 - entity 를 그대로 넘긴다.
    public Page<Schedule> getAllSchedulesUsingPaging(Pageable pageable) {
        return schedulesRepository.findAll(pageable);
    }

    // slice
    public AllScheduleWithSliceResponseDto getAllSchedulesWithSlice(Pageable pageable) {
        List<AllScheduleResponseDto> responseDtoList = new ArrayList<>();

        final Slice<Schedule> scheduleSlice = schedulesRepository.findBy(pageable);
        scheduleSlice.stream()
                .forEach(schedule -> responseDtoList.add(AllScheduleResponseDto.of(schedule)));

        return AllScheduleWithSliceResponseDto.of(responseDtoList, scheduleSlice);
    }


    public SliceImpl<AllScheduleResponseDto> getAllScheduleAndSlice(Pageable pageable) {
        final Slice<Schedule> schedules = schedulesRepository.findBy(pageable);

        List<AllScheduleResponseDto> dtoList = new ArrayList<>();
        schedules.forEach(schedule -> dtoList.add(AllScheduleResponseDto.of(schedule)));
        return new SliceImpl<>(dtoList, pageable, schedules.hasNext());

    }


    public Page<AllScheduleResponseDto> getScheduleByQueryMethod(Pageable pageable) {
        final Page<Schedule> schedules = schedulesRepository.findAllByMemberNickname("날으는달팽이", pageable);

        return new PageImpl<AllScheduleResponseDto>(schedules.getContent()
                .stream()
                .map(AllScheduleResponseDto::of)
                .collect(Collectors.toList()),pageable, schedules.getTotalElements());
    }


    // main
    @Transactional(readOnly = true)
    public List<AllScheduleResponseDto> getAllSchedule() {
        List<AllScheduleResponseDto> responseDtoList = new ArrayList<>();

        schedulesRepository.findAll()
                .forEach(schedule -> responseDtoList.add(AllScheduleResponseDto.of(schedule)));

        return responseDtoList;
    }

    public Page<AllScheduleResponseDto> getAllSchedulesWithPage(Pageable pageable) {
        final Page<Schedule> schedulePage = schedulesRepository.findAll(pageable);
        return new PageImpl<AllScheduleResponseDto>(schedulePage.stream()
                    .map(AllScheduleResponseDto::of)
                    .collect(Collectors.toList()),pageable, schedulePage.getTotalElements());
    }
}
