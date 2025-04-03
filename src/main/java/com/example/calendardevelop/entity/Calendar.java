package com.example.calendardevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "calendar")
public class Calendar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB의 AUTO_INCREMENT 전략을 사용하여 ID를 자동 생성
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    @ManyToOne // FK, 1개의 user가 n개의 calendar를 가질 수 있음
    @JoinColumn(name = "userId") // FK 이름 설정, 다른컬럼을 FK로 따로 설정할수 있음, default 값은 PK
    private User user;

    public Calendar(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public Calendar() {
    }

    public void updateTitleAndContents(String title, String contents) {
        if(title != null) { this.title = title; }
        if(contents != null) { this.contents = contents; }
    }
}
