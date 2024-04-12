package com.project.memo.service;

import com.project.memo.domain.dto.MemoDto;
import com.project.memo.domain.dto.MemoFormDto;
import com.project.memo.domain.entity.Memo;
import com.project.memo.repository.MemoRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemoServiceTest {

    @Autowired
    private MemoRepository memoRepository;

    @Autowired
    private MemoService memoService;

    @BeforeEach
    void setUp() {
        MemoFormDto memoFormDto = new MemoFormDto();

        for (int i = 0; i < 10; i++) {
            memoFormDto.setContent("메모"+i);
            Memo memo = Memo.createMemo(memoFormDto);
            memoRepository.save(memo);
        }
    }

    @Test
    @DisplayName("더미 데이터 + 메모 목록 조회 테스트")
    void getMemoList() {
        try {
            List<MemoDto> memoList = memoService.getMemoList();
            Assertions.assertEquals(10, memoList.size());
            System.out.println("memoList = " + memoList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}