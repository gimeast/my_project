package com.project.memo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoFormDto {

    private String content;
    private Long memberIdx;
    private String memberId;

}
