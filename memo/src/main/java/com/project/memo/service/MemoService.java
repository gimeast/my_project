package com.project.memo.service;

import com.project.memo.domain.dto.MemoDto;
import com.project.memo.domain.dto.MemoFormDto;
import com.project.memo.domain.entity.Memo;
import com.project.memo.exception.NoSuchMemoException;
import com.project.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


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
    @Transactional
    public Long saveMemo(MemoFormDto memoFormDto) throws Exception {
        Long memoIdx = memoFormDto.getMemoIdx();
        if(memoIdx != null) {
            Optional<Memo> findMemo = memoRepository.findById(memoIdx);
            if (findMemo.isPresent()) {
                findMemo.get().updateMemo(memoFormDto);

                return memoIdx;
            } else {
                throw new NoSuchMemoException("수정하려는 메모의 정보가 없습니다.");
            }
        } else {
            Memo memo = Memo.createMemo(memoFormDto);
            memoRepository.save(memo);
            return memo.getIdx();
        }
    }

}
