package com.example.abhishek.SprintDesk_Project_Management.mapper;

import com.example.abhishek.SprintDesk_Project_Management.dto.response.PageResponseDto;
import com.example.abhishek.SprintDesk_Project_Management.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
public class PageMapper {
    public PageResponseDto toPageResponseDto(Page<Task> page){
        PageResponseDto pageResponseDto=new PageResponseDto();
        pageResponseDto.setContent(page.getContent());
        pageResponseDto.setPage(page.getNumber());
        pageResponseDto.setSize(page.getSize());
        pageResponseDto.setTotalElements(page.getNumberOfElements());
        pageResponseDto.setTotalPages(page.getTotalPages());
        return pageResponseDto;
    }
}
