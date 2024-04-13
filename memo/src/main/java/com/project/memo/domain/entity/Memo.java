package com.project.memo.domain.entity;

import com.project.memo.domain.dto.MemoFormDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

import static java.time.LocalDateTime.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "memo")
@ToString
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memo_idx")
    private Long idx; //memo key 값

    private String content; //memo 내용

    @Enumerated(EnumType.STRING)
    private MemberSyncStatus memberSyncStatus; //회원 동기화 상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx")
    private Member member;

    @CreatedBy
    @Column(updatable = false)
    private Long createdBy; //생성자

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate; //생성일시

    @LastModifiedBy
    private Long lastModifiedBy; //수정자

    @LastModifiedDate
    private LocalDateTime lastModifiedDate; //수정일시

    /**
     * @Method         : createMemo
     * @Description    : 메모 생성 메서드
     * @Author         : gimeast
     * @Date           : 2024. 04. 13.
     * @params         : content, member
     * @return         : Memo
     */
    public static Memo createMemo(MemoFormDto memoFormDto) {
        Memo memo = new Memo();
        memo.setContent(memoFormDto.getContent());
        memo.setCreatedDate(now());
        memo.setLastModifiedDate(now());
        memo.setMemberSyncStatus(MemberSyncStatus.NOT_SYNC);

        Member member = new Member();
        if(memoFormDto.getMemberIdx() != null && memoFormDto.getMemberId() != null) {
            member.setIdx(memoFormDto.getMemberIdx());
            member.setMemberId(memoFormDto.getMemberId());

            memo.setMember(member);

            memo.setCreatedBy(member.getIdx());
            memo.setLastModifiedBy(member.getIdx());
        }

        return memo;
    }

    public void updateMemo(MemoFormDto memoFormDto) {
        this.setContent(memoFormDto.getContent());
        this.setLastModifiedDate(now());

        if(memoFormDto.getMemberIdx() != null && memoFormDto.getMemberId() != null) {
            this.setLastModifiedBy(member.getIdx());
        }
    }

}
