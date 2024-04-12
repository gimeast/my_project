package com.project.memo.controller;

import com.project.memo.common.ResponseDto;
import com.project.memo.domain.dto.MemoDto;
import com.project.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
    
    @GetMapping("/v1/memo")
    public ResponseDto<ResponseDto<Map<String, Object>>> getMemoList() {

        try {
            List<MemoDto> memoList = memoService.getMemoList();
            Map<String, Object> rtnMap = new HashMap<>();

            rtnMap.put("memoList", memoList);
            return ResponseDto.ofSuccess("성공", ResponseDto.of("200", "메모를 모두 조회하였습니다.", rtnMap));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.ofFail("실패!!");
        }

    }

}
