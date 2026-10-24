package com.example.abhishek.SprintDesk_Project_Management.service;

import com.example.abhishek.SprintDesk_Project_Management.exception.BusinessConflictException;
import com.example.abhishek.SprintDesk_Project_Management.exception.ResourceNotFoundException;
import com.example.abhishek.SprintDesk_Project_Management.models.*;
import com.example.abhishek.SprintDesk_Project_Management.repository.MemberRepository;
import com.example.abhishek.SprintDesk_Project_Management.repository.ProjectRepository;
import com.example.abhishek.SprintDesk_Project_Management.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.InvalidParameterException;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.abhishek.SprintDesk_Project_Management.models.TaskStatus.*;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    MemberRepository memberRepository;

    public Task addTask(Task task, long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if(project == null) {
            throw new ResourceNotFoundException("Project with id " + projectId + " not found");
        }
        task.setProject(project);
        task.setStatus(TaskStatus.TODO);
        task.setCreatedAt(Instant.now());
        task.setUpdatedAt(Instant.now());
        taskRepository.save(task);
        return task;
    }

    public List<Task> getTasksByProjectId(long projectId, TaskPriority taskPriority,TaskStatus taskStatus,String sortBy,String sortDirection) {

        Set<String> validSortParameters= Set.of("createdAt","updatedAt","title","dueDate");
        Set<String> validSortDirection= Set.of("ASC","DESC");
        Sort sort=Sort.by(Sort.Direction.ASC,"title");

        if(!projectRepository.findById(projectId).isPresent()) {
            throw new ResourceNotFoundException("Projects not found with project id"+projectId);
        }
        else
        {
            if(sortBy!=null && sortDirection!=null) {
                if (!validSortParameters.contains(sortBy) || !validSortDirection.contains(sortDirection)) {
                    throw new InvalidParameterException("sortBy or sortDirection are invalid");
                } else {
                    if (sortDirection.equals("ASC"))
                        sort = Sort.by(Sort.Direction.ASC, sortBy);
                    else {
                        sort = Sort.by(Sort.Direction.DESC, sortBy);
                    }
                }
            }

            if(taskStatus!=null && taskPriority!=null) {
                return taskRepository.findFilteredTasks(projectId,taskStatus,taskPriority,sort);
            }
            if(taskStatus!=null && taskPriority==null) {
                return taskRepository.findByProjectIdAndStatus(projectId,taskStatus,sort);
            }
            if(taskStatus==null &&  taskPriority!=null) {
                return taskRepository.findByProjectIdAndPriority(projectId,taskPriority,sort);
            }
            return taskRepository.findAllByProjectId(projectId,sort);
        }

    }

    public Task getTaskById(long taskId) {
        return taskRepository.findById(taskId).orElseThrow(()->new ResourceNotFoundException("Task not found with id -"+taskId));
    }

    public Task updateTask(long taskId, Task updatedTask) {
        // 1. Fetch existing task
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Task not found with ID: " + taskId
                        )
                );

        if(updatedTask.getAssigneeId()!=null)
        {
            Member member = memberRepository.findById(updatedTask.getAssigneeId()).orElse(null);
            if(member != null) {
                existingTask.setAssigneeId(updatedTask.getAssigneeId());
                existingTask.setMember(member);
            }
            else
                throw new ResourceNotFoundException("Assignee with id- "+updatedTask.getAssigneeId()+ "not found");

        }

        // 2. Update only allowed fields
        if (updatedTask.getTitle() != null) {
            existingTask.setTitle(updatedTask.getTitle());
        }

        if (updatedTask.getDescription() != null) {
            existingTask.setDescription(updatedTask.getDescription());
        }

        if (updatedTask.getPriority() != null) {
            existingTask.setPriority(updatedTask.getPriority());
        }

        if (updatedTask.getDueDate() != null) {
            existingTask.setDueDate(updatedTask.getDueDate());
        }

        // 3. Update timestamp
        existingTask.setUpdatedAt(Instant.now());

        // 4. Save existing task
        return taskRepository.save(existingTask);
    }

    public void deleteTaskById(long taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if(taskRepository.findById(taskId).isPresent()) {
            if(task.getStatus()== DONE)
                taskRepository.delete(task);
            else
                throw new BusinessConflictException("Task with TaskId- "+taskId+" cant be deleted as the status is still in progress");
        }
        else
        {
            throw new ResourceNotFoundException("Task not found with id -"+taskId);
        }
    }


    public Task updateTaskStatus(long taskId, TaskStatus updatedTaskStatus) {

        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Task not found with id - " + taskId
                ));

        TaskStatus currentStatus = existingTask.getStatus();

        if (currentStatus == updatedTaskStatus) {
            return existingTask;
        }

        if (!isValidTransition(currentStatus, updatedTaskStatus)) {
            throw new BusinessConflictException(
                    "Cannot change taskStatus from " +
                            currentStatus + " to " + updatedTaskStatus
            );
        }

        if (updatedTaskStatus == IN_PROGRESS) {

            Long assigneeId = existingTask.getAssigneeId();

            // Validation 1: Assignee must be assigned
            if (assigneeId == null) {
                throw new BusinessConflictException(
                        "Cannot move Task to IN_PROGRESS " +
                                "because no Member is assigned"
                );
            }

            // Validation 2: Assigned member must exist
            if (!memberRepository.existsById(assigneeId)) {
                throw new BusinessConflictException(
                        "Cannot move Task to IN_PROGRESS " +
                                "because the assigned Member does not exist"
                );
            }
        }

        existingTask.setStatus(updatedTaskStatus);
        existingTask.setUpdatedAt(Instant.now());

        return taskRepository.save(existingTask);
    }



    private boolean isValidTransition(TaskStatus current, TaskStatus next) {

        if (current == null || next == null) {
            return false;
        }

        return switch (current) {
            case TODO ->  next == IN_PROGRESS || next == BLOCKED;

            case IN_PROGRESS -> next == BLOCKED || next == DONE;

            case BLOCKED , DONE -> next == IN_PROGRESS;
        };
    }

    }
