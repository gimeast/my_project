package com.project.chat.domain.dto;

import com.project.chat.domain.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDto {
    private Long id;

    @NotNull(message = "이름은 필수입니다.")
    private String name;

    public static MemberDto memberToDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(memberDto.getId());
        memberDto.setName(member.getName());
        return memberDto;
    }
}
