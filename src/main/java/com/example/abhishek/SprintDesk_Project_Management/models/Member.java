package com.example.abhishek.SprintDesk_Project_Management.models;

import com.example.abhishek.SprintDesk_Project_Management.dto.response.MemberResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    String name;
    String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MemberResponseDto modelToDto(Member createdMember) {
        MemberResponseDto memberResponseDto=new MemberResponseDto();
        memberResponseDto.setId(createdMember.getId());
        memberResponseDto.setEmail(createdMember.getEmail());
        memberResponseDto.s
    }
}
