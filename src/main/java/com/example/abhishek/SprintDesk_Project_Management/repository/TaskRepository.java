package com.example.abhishek.SprintDesk_Project_Management.repository;

import com.example.abhishek.SprintDesk_Project_Management.models.Project;
import com.example.abhishek.SprintDesk_Project_Management.models.Task;
import com.example.abhishek.SprintDesk_Project_Management.models.TaskPriority;
import com.example.abhishek.SprintDesk_Project_Management.models.TaskStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {


    List<Task> findAllByProject(Project project);


    //Filter to find tasks by project
    List<Task> findAllByProjectId(Long projectId, Sort sort);

    //Filter to find tasks of project with particular status

    List<Task> findByProjectIdAndStatus(Long projectId, TaskStatus taskStatus, Sort sort);


    //Filter to retrieve tasks using projectId and priority

    List<Task> findByProjectIdAndPriority(Long projectId, TaskPriority taskPriority, Sort sort);

    //Filter to retrieve tasks using projectId and priority and taskStatus

    @Query("select t from Task t where t.project.id=:projectId AND t.status=:taskStatus AND t.priority=:taskPriority")
    List<Task> findFilteredTasks(@Param("projectId") Long projectId, @Param("taskStatus") TaskStatus taskStatus, @Param("taskPriority") TaskPriority taskPriority, Sort sort);




//    List<Task> findFilteredTasks(@Param("projectId") Long ProjectId,@Param("taskPriority") TaskPriority  taskPriority,@Param("taskStatus") TaskStatus taskStatus);

}
