package com.project.memo.repository;

import com.project.memo.domain.dto.MemberDTO;
import com.project.memo.domain.dto.MemoDto;
import com.project.memo.domain.dto.QMemoDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.project.memo.domain.entity.QMemo.memo;

@RequiredArgsConstructor
public class MemoRepositoryCustomImpl implements MemoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemoDto> selectMemoList(MemberDTO loginUser) {
        return queryFactory
                .select(
                        new QMemoDto(
                                memo.idx.as("memoIdx"),
                                memo.content,
                                memo.displayOrder,
                                memo.lastModifiedDate
                        )
                )
                .from(memo)
                .where(
                        userIdxEq(loginUser.getMemberIdx()),
                        userIdEq(loginUser.getMemberId())
                )
                .orderBy(memo.displayOrder.asc())
                .fetch();
    }

    public BooleanExpression userIdxEq(Long userIdx) {
        return userIdx != null ? memo.member.idx.eq(userIdx) : null;
    }

    public BooleanExpression userIdEq(String userId) {
        return StringUtils.hasText(userId) ? memo.member.memberId.eq(userId) : null;
    }

}
