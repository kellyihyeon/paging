package com.prac.page.repository;

import com.prac.page.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulesRepository extends JpaRepository<Schedule, Long> {

    Page<Schedule> findAllByMemberNickname(String nickname, Pageable pageable);

    Slice<Schedule> findBy(Pageable pageable);


}
