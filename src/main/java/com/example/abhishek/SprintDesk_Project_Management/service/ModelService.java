package com.example.abhishek.SprintDesk_Project_Management.service;

import com.example.abhishek.SprintDesk_Project_Management.exception.ResourceNotFoundException;
import com.example.abhishek.SprintDesk_Project_Management.models.Member;
import com.example.abhishek.SprintDesk_Project_Management.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    @Autowired
    MemberRepository memberRepository;

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    public Member getMemberById(Long id) {
        Member member=memberRepository.findById(id).orElse(null);

        if(member == null) {
        throw new ResourceNotFoundException("Member not found with id " + id);
        }

        return member;
    }
}
