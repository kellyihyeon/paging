package com.prac.page.entity;

import com.prac.page.entity.Member;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
public class Schedule{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @NotNull
    @Column(name= "title", length = 100)
    private String title;


    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @NotNull
    @Column(name = "details", length = 300)
    private String details;


    @Builder
    public Schedule(String title, Member member, String details) {
        this.title = title;
        this.member = member;
        this.details = details;
    }

}
