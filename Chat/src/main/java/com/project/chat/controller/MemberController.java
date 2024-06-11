package com.project.chat.controller;

import com.project.chat.domain.dto.MemberDto;
import com.project.chat.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("member", new MemberDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, @Valid @ModelAttribute MemberDto member, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        MemberDto memberDto = memberService.login(member.getName());
        session.setAttribute("member", memberDto);
        model.addAttribute("member", memberDto);
        return "redirect:/chat-rooms";
    }
}
