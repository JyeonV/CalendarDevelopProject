package com.example.calendardevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB의 AUTO_INCREMENT 전략을 사용하여 ID를 자동 생성
    private Long id;

    private String contents;

    @ManyToOne // FK, 1개의 user가 n개의 comment를 가질 수 있음
    @JoinColumn(name = "userId") // FK 이름 설정, 다른컬럼을 FK로 따로 설정할수 있음, default 값은 PK
    private User user;

    @ManyToOne // FK, 1개의 calendar가 n개의 comment를 가질 수 있음
    @JoinColumn(name = "calendarId") // FK 이름 설정, 다른컬럼을 FK로 따로 설정할수 있음, default 값은 PK
    private Calendar calendar;

    public Comment(String contents, User user, Calendar calendar) {
        this.contents = contents;
        this.user = user;
        this.calendar = calendar;
    }

    public Comment() {
    }

    public void updateCommentContents(String contents) {
        this.contents = contents;
    }
}
