package com.example.abhishek.SprintDesk_Project_Management.controllers;


import com.example.abhishek.SprintDesk_Project_Management.dto.request.CreateMemberRequest;
import com.example.abhishek.SprintDesk_Project_Management.dto.response.MemberResponseDto;
import com.example.abhishek.SprintDesk_Project_Management.mapper.MemberMapper;
import com.example.abhishek.SprintDesk_Project_Management.models.Member;
import com.example.abhishek.SprintDesk_Project_Management.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    MemberMapper memberMapper;

    @Autowired
    MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponseDto> addMember(@RequestBody @Valid CreateMemberRequest createMemberRequestDto, HttpServletRequest request) {
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
