package com.prac.page.utils;

import com.prac.page.entity.Member;
import com.prac.page.entity.Schedule;
import com.prac.page.repository.MemberRepository;
import com.prac.page.repository.SchedulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ScheduleDummyData implements InitializingBean {

    private final MemberRepository memberRepository;
    private final SchedulesRepository schedulesRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        final Member member = Member.builder()
                .id(1L)
                .email("potato@email.com")
                .password("sweeeeeet_potato")
                .nickname("납작한감자")
                .build();
        memberRepository.save(member);

        final Member member2 = Member.builder()
                .id(2L)
                .email("snail@email.com")
                .password("oh_snail")
                .nickname("날으는달팽이")
                .build();
        memberRepository.save(member2);

        for (int i = 0; i < 20; i++) {
            Schedule schedule = Schedule.builder()
                    .title("북카페에서 모임 가지실 분? " + (i+1))
                    .member(member)
                    .details("북 스터디 진행합니다. " + (i+1))
                    .build();
            schedulesRepository.save(schedule);
        }

        for (int i = 0; i < 3; i++) {
            Schedule schedule = Schedule.builder()
                    .title("달팽이들 연합 모임 " + (i+1))
                    .member(member2)
                    .details("달팽이들을 위한 커피 모임입니다. 감자는 안받습니다. " + (i+1))
                    .build();
            schedulesRepository.save(schedule);
        }
    }
}
