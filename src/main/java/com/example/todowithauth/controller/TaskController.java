package com.example.todowithauth.controller;

import com.example.todowithauth.model.Task;
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
  public List<Task> getAllTasks() {
    return taskService.getAllTasks();
  }

  @PostMapping("/")
  public Task createTask(@RequestBody Task task) {
    return taskService.saveTask(task);
  }

  @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
  }
}
