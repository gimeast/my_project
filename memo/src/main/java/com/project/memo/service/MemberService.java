package com.project.memo.service;

import com.project.memo.domain.dto.MemberDTO;
import com.project.memo.domain.entity.Member;
import com.project.memo.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDTO login(HttpSession session, String memberId, String password) throws Exception {
        Member findMember = memberRepository.findByMemberIdAndPassword(memberId, password);
        if (findMember != null) {
            MemberDTO memberDto = new MemberDTO();
            memberDto.setMemberIdx(findMember.getIdx());
            memberDto.setMemberId(findMember.getMemberId());

            session.setAttribute("loginUser", memberDto);
            System.out.println("session.getId() = " + session.getId());
            return memberDto;

        } else {
            throw new Exception("아이디와 비밀번호를 확인하세요!");
        }
    }


}
