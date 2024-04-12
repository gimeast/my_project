package com.project.memo.service;

import com.project.memo.domain.dto.MemoDto;
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

    public List<MemoDto> getMemoList() throws Exception {
        return memoRepository.selectMemoList();
    }

}
