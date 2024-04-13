package com.project.memo.controller;

import com.project.memo.common.ResponseDto;
import com.project.memo.domain.dto.MemberDTO;
import com.project.memo.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @PostMapping("/v1/auth/login")
    public ResponseDto<ResponseDto<Map<String, Object>>> login(HttpSession session, String memberId, String password) {
        try {
            MemberDTO loginUser = memberService.login(session, memberId, password);
            Map<String, Object> rtnMap = new HashMap<>();

            rtnMap.put("loginUser", loginUser);
            return ResponseDto.ofSuccess("성공", ResponseDto.of("201", "안녕하세요 "+loginUser.getMemberId()+"님", rtnMap));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.ofFail("아이디와 비밀번호를 확인하세요!");
        }
    }

    @PostMapping("/v1/auth/logout")
    public ResponseDto<ResponseDto<Map<String, Object>>> logout(HttpSession session) {
        try {
            session.invalidate();
            return ResponseDto.ofSuccess("성공", ResponseDto.of("201", "로그아웃이 완료되었습니다.", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.ofFail("아이디와 비밀번호를 확인하세요!");
        }
    }

}
