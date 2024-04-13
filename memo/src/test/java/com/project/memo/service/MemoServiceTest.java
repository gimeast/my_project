package com.project.memo.service;

import com.project.memo.domain.dto.MemberDTO;
import com.project.memo.domain.dto.MemoDto;
import com.project.memo.domain.dto.MemoFormDto;
import com.project.memo.domain.entity.Member;
import com.project.memo.domain.entity.Memo;
import com.project.memo.repository.MemberRepository;
import com.project.memo.repository.MemoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemoServiceTest {

    @Autowired
    private MemoRepository memoRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemoService memoService;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void setUp() {
        Member member = memberRepository.save(new Member("tester", "123"));
        em.flush();
        em.clear();
        for (int i = 0; i < 10; i++) {
            Memo memo = Memo.createMemo("메모"+i, i+1, member.getIdx());
            memoRepository.save(memo);
            System.out.println("메모 더미 데이터 10개 생성");
        }
    }

    @Test
    @DisplayName("더미 데이터 + 메모 목록 조회 테스트")
    void getMemoList() {
        try {
            List<MemoDto> memoList = memoService.getMemoList(new MemberDTO(1L, "tester"));
            assertEquals(10, memoList.size());
            System.out.println("memoList = " + memoList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("메모 수정")
    void updateMemo() {
        Optional<Memo> findMemo = memoRepository.findById(1L);
        findMemo.ifPresent(memo -> memo.updateMemo("바뀐 내용", 1L));
        List<Memo> memoList = memoRepository.findAll();
        System.out.println("memoList = " + memoList);

    }
}