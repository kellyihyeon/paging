package com.prac.page.service;

import com.prac.page.entity.Schedule;
import com.prac.page.dto.AllScheduleResponseDto;
import com.prac.page.dto.AllScheduleWithSliceResponseDto;
import com.prac.page.repository.SchedulesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class SchedulesService {

    private final SchedulesRepository schedulesRepository;


    public Page<Schedule> getAllSchedulesUsingPaging(Pageable pageable) {
        return schedulesRepository.findAll(pageable);
    }

    public AllScheduleWithSliceResponseDto getAllSchedulesWithSlice(Pageable pageable) {
        final Slice<Schedule> schedulesWithSlice = schedulesRepository.findBy(pageable);

        List<AllScheduleResponseDto> responseDtoList = new ArrayList<>();

        for (Schedule schedule : schedulesWithSlice) {
            responseDtoList.add(
                    AllScheduleResponseDto.builder()
                            .scheduleId(schedule.getId())
                            .title(schedule.getTitle())
                            .nickname(schedule.getMember().getNickname())
                            .build());
        }


        return AllScheduleWithSliceResponseDto.of(responseDtoList, schedulesWithSlice.hasNext());

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
