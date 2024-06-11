package com.project.memo.controller;

import com.project.memo.domain.dto.MemberDTO;
import com.project.memo.domain.dto.MemoDto;
import com.project.memo.domain.dto.MemoFormDto;
import com.project.memo.service.MemoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemoController2 {

    private final MemoService memoService;

    @GetMapping("/memo")
    public String memo(HttpSession session, Model model) {
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

        List<MemoDto> memoList = memoService.getMemoList(loginUser);
        model.addAttribute("memoList", memoList);
        return "/memo";
    }

    @PostMapping("/memo/write")
    public String saveMemo(HttpSession session, @RequestBody MemoFormDto memoFormDto) {

        return "redirect:/memo";
    }
}
