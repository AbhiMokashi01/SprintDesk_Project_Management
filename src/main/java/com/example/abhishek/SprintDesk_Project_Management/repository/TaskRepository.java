package com.example.abhishek.SprintDesk_Project_Management.repository;

import com.example.abhishek.SprintDesk_Project_Management.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
