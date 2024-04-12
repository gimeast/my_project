package com.project.memo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_idx")
    private Long idx; //member key 값

    @Column(unique = true)
    private String memberId;

    private String password;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy; //생성자

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate; //생성일시

    @LastModifiedBy
    private String lastModifiedBy; //수정자

    @LastModifiedDate
    private LocalDateTime lastModifiedDate; //수정일시
}
