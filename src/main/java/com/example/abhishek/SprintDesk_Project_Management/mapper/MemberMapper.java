package com.example.abhishek.SprintDesk_Project_Management.mapper;

import com.example.abhishek.SprintDesk_Project_Management.dto.request.CreateMemberRequest;
import com.example.abhishek.SprintDesk_Project_Management.models.Member;

public class MemberMapper {

    public Member dtoToModel(CreateMemberRequest createMemberRequestDto) {
        Member member = new Member();
        member.setName(createMemberRequestDto.getName());
        member.setEmail(createMemberRequestDto.getEmail());
        return member;
    }


}
