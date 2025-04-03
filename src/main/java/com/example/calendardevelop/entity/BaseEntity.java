package com.example.calendardevelop.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
// 실제 테이블에는 생성되지 않지만 상속받는 클래스에 컬럼으로 포함
@MappedSuperclass
// JPA Auditing 기능을 활성화해서 Entity의 생성, 수정 시각을 자동으로 처리
@EntityListeners(AuditingEntityListener.class)
// 공통 엔티티 속성(작성일, 수정일), 공통 필드를 상속만 제공하고 직접 인스턴스로 생성하지 않기 위해 추상클래스로 선언
public abstract class BaseEntity {
    @CreatedDate // Entity 가 저장될 때 자동으로 현재시간 저장
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // Entity 가 수정될 떄 자동으로 현재시간 저장
    private LocalDateTime modifiedAt;

}
