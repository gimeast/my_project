package com.project.memo.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MemoDto {

    private Long memoIdx;
    private String content; //memo 내용

    private LocalDateTime lastModifiedDate; //수정일시

    @QueryProjection
    public MemoDto(Long memoIdx, String content, LocalDateTime lastModifiedDate) {
        this.memoIdx = memoIdx;
        this.content = content;
        this.lastModifiedDate = lastModifiedDate;
    }
}
