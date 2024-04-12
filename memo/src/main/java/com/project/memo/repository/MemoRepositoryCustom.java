package com.project.memo.repository;

import com.project.memo.domain.dto.MemoDto;

import java.util.List;

public interface MemoRepositoryCustom {
    List<MemoDto> selectMemoList();
}
