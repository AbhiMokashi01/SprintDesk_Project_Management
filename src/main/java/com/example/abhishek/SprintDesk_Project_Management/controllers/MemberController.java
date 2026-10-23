package com.example.abhishek.SprintDesk_Project_Management.controllers;


import com.example.abhishek.SprintDesk_Project_Management.dto.request.CreateMemberRequest;
import com.example.abhishek.SprintDesk_Project_Management.dto.response.MemberResponseDto;
import com.example.abhishek.SprintDesk_Project_Management.mapper.MemberMapper;
import com.example.abhishek.SprintDesk_Project_Management.models.Member;
import com.example.abhishek.SprintDesk_Project_Management.repository.MemberRepository;
import com.example.abhishek.SprintDesk_Project_Management.service.ModelService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController("/api/members")
public class MemberController {
    @Autowired
    MemberMapper memberMapper;

    @Autowired
    ModelService memberService;

    @PostMapping
    public ResponseEntity<MemberResponseDto> addMember(CreateMemberRequest createMemberRequestDto, HttpServletRequest request) {
        Member member=memberMapper.dtoToModel(createMemberRequestDto);

        Member createdMember=memberService.addMember(member);

        MemberResponseDto memberResponseDto=member.modelToDto(createdMember);

        return ResponseEntity.created(URI.create(request.getRequestURI()))
                .body(memberResponseDto);


    }

    @GetMapping("{id}")
    public ResponseEntity<MemberResponseDto> getMemberById(@PathVariable Long id) {
        Member member=memberService.getMemberById(id);

        MemberResponseDto memberResponseDto=member.modelToDto(member);

        return ResponseEntity.ok(memberResponseDto);
    }
}
