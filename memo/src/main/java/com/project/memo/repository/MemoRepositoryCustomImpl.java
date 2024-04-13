package com.project.memo.repository;

import com.project.memo.domain.dto.MemoDto;
import com.project.memo.domain.dto.QMemoDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.project.memo.domain.entity.QMemo.memo;

@RequiredArgsConstructor
public class MemoRepositoryCustomImpl implements MemoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemoDto> selectMemoList() {
        return queryFactory
                .select(
                        new QMemoDto(memo.idx.as("memoIdx"), memo.content, memo.displayOrder, memo.lastModifiedDate)
                )
                .from(memo)
                .orderBy(memo.displayOrder.asc())
                .fetch();
    }

}
