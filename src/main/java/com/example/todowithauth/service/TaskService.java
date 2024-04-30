package com.example.todowithauth.service;

import com.example.todowithauth.dto.TaskDTO;
import com.example.todowithauth.model.Task;
import com.example.todowithauth.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  @Autowired
  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<TaskDTO> getAllTasks() {
    List<Task> tasks = taskRepository.findAll();
    return tasks.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  public TaskDTO saveTask(TaskDTO taskDTO) {
    Task task = convertToEntity(taskDTO);
    Task savedTask = taskRepository.save(task);
    return convertToDTO(savedTask);
  }

  public void deleteTask(Long id) {
    taskRepository.deleteById(id);
  }

  // Méthode pour convertir une entité Task en DTO
  private TaskDTO convertToDTO(Task task) {
    TaskDTO taskDTO = new TaskDTO();
    taskDTO.setId(task.getId());
    taskDTO.setName(task.getName());
    taskDTO.setCompleted(task.isCompleted());
    return taskDTO;
  }

  // Méthode pour convertir un DTO en entité Task
  private Task convertToEntity(TaskDTO taskDTO) {
    Task task = new Task();
    task.setId(taskDTO.getId());
    task.setName(taskDTO.getName());
    task.setCompleted(taskDTO.isCompleted());
    return task;
  }
}