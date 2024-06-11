package com.project.chat.service;

import com.project.chat.domain.Member;
import com.project.chat.domain.dto.MemberDto;
import com.project.chat.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberJpaRepository memberRepository;

    public MemberDto login(String name) {
        Optional<Member> findMember = memberRepository.findByName(name);

        if (findMember.isPresent()) {
            return MemberDto.memberToDto(findMember.get());
        } else {
            Member member = new Member();
            member.setName(name);
            memberRepository.save(member);
            return MemberDto.memberToDto(member);
        }
    }
}
