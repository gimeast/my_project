package com.project.memo.service;

import com.project.memo.domain.dto.MemoDto;
import com.project.memo.domain.dto.MemoFormDto;
import com.project.memo.domain.entity.Memo;
import com.project.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class MemoService {

    private final MemoRepository memoRepository;

    /**
     * @Method         : getMemoList
     * @Description    : 메모 목록 조회
     * @Author         : gimeast
     * @Date           : 2024. 04. 13.
     * @params         :
     * @return         : memoDtoList
     */
    public List<MemoDto> getMemoList() throws Exception {
        return memoRepository.selectMemoList();
    }

    /**
     * @Method         : saveMemo
     * @Description    : 메모 작성
     * @Author         : gimeast
     * @Date           : 2024. 04. 13.
     * @params         : memoFormDto
     * @return         : memoIdx
     */
    public Long saveMemo(MemoFormDto memoFormDto) throws Exception {
        Memo memo = Memo.createMemo(memoFormDto);
        memoRepository.save(memo);
        return memo.getIdx();
    }

}
