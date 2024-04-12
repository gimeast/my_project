package com.project.memo.controller;

import com.project.memo.common.ResponseDto;
import com.project.memo.domain.dto.MemoDto;
import com.project.memo.domain.dto.MemoFormDto;
import com.project.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
    
    /**
     * @Method         : getMemoList
     * @Description    : 메모 목록 조회
     * @Author         : gimeast
     * @Date           : 2024. 04. 13.
     * @params         : 
     * @return         : memoList
     */
    @GetMapping("/v1/memo")
    public ResponseDto<ResponseDto<Map<String, Object>>> getMemoList() {
        try {
            List<MemoDto> memoList = memoService.getMemoList();
            Map<String, Object> rtnMap = new HashMap<>();

            rtnMap.put("memoList", memoList);
            return ResponseDto.ofSuccess("성공", ResponseDto.of("200", "메모를 모두 조회하였습니다.", rtnMap));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.ofFail("메모 목록 조회 중 오류가 발생하였습니다.");
        }
    }

    /**
     * @Method         : saveMemo
     * @Description    : 메모 저장
     * @Author         : gimeast
     * @Date           : 2024. 04. 13.
     * @params         : memoFormDto - 메모 내용, 작성자 idx, id
     * @return         : memoIdx
     */
    @PostMapping("/v1/memo")
    public ResponseDto<ResponseDto<Map<String, Object>>> saveMemo(@RequestBody MemoFormDto memoFormDto) {
        try {
            Long memoIdx = memoService.saveMemo(memoFormDto);
            Map<String, Object> rtnMap = new HashMap<>();

            rtnMap.put("memoIdx", memoIdx);
            return ResponseDto.ofSuccess("성공", ResponseDto.of("201", "메모를 저장하였습니다.", rtnMap));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.ofFail("메모 저장 중 오류가 발생하였습니다.");
        }
    }

}
