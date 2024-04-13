package com.project.memo.controller;

import com.project.memo.common.ResponseDto;
import com.project.memo.domain.dto.MemberDTO;
import com.project.memo.domain.dto.MemoDto;
import com.project.memo.domain.dto.MemoFormDto;
import com.project.memo.service.MemoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    public ResponseDto<ResponseDto<Map<String, Object>>> getMemoList(HttpSession session) {
        try {
            MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
            if(loginUser == null) return ResponseDto.ofSuccess("성공", ResponseDto.of("200", "메모장을 사용하려면 로그인을 해주세요.", null));

            List<MemoDto> memoList = memoService.getMemoList(loginUser);
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
    public ResponseDto<ResponseDto<Map<String, Object>>> saveMemo(HttpSession session, @RequestBody MemoFormDto memoFormDto) {
        try {
            MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
            if(loginUser == null) return ResponseDto.ofSuccess("성공", ResponseDto.of("200", "메모장을 사용하려면 로그인을 해주세요.", null));

            memoFormDto.setMemberIdx(loginUser.getMemberIdx());
            memoFormDto.setMemberId(loginUser.getMemberId());
            Long memoIdx = memoService.saveMemo(memoFormDto);
            Map<String, Object> rtnMap = new HashMap<>();

            rtnMap.put("memoIdx", memoIdx);
            return ResponseDto.ofSuccess("성공", ResponseDto.of("201", "메모를 저장하였습니다.", rtnMap));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.ofFail("메모 저장 중 오류가 발생하였습니다.");
        }
    }

    /**
     * @Method         : memoReorder
     * @Description    : 메모 순서 변경
     * @Author         : gimeast
     * @Date           : 2024. 04. 14.
     * @params         : memoFormDtoList
     * @return         :
     */
    @PatchMapping("/v1/memo/reorder")
    public ResponseDto<ResponseDto<Map<String, Object>>> memoReorder(HttpSession session, @RequestBody List<MemoFormDto> memoFormDtoList) {
        try {
            MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
            if(loginUser == null) return ResponseDto.ofSuccess("성공", ResponseDto.of("201", "메모장을 사용하려면 로그인을 해주세요.", null));

            memoService.memoReorder(memoFormDtoList);

            return ResponseDto.ofSuccess("성공", ResponseDto.of("201", "메모 정렬 순서를 변경하였습니다.", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.ofFail("메모 정렬 순서 변경 중 오류가 발생하였습니다.");
        }
    }
}
