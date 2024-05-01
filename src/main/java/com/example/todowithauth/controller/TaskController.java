package com.example.todowithauth.controller;

import com.example.todowithauth.dto.TaskDTO;
import com.example.todowithauth.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @GetMapping("/")
  public List<TaskDTO> getAllTasks() {
    return taskService.getAllTasks();
  }

  @PostMapping("/")
  public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
    return taskService.saveTask(taskDTO);
  }

  @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
  }

  /*
  *
  * Méthode d'exemple pour cas Admin
  *
  @Secured("ROLE_ADMIN")
  @GetMapping("/admin")
  public List<TaskDTO> getAllTasksForAdmin() {
    // Méthode accessible uniquement aux utilisateurs ayant le rôle "ADMIN"
    return taskService.getAllTasksForAdmin();
  }
   */
}