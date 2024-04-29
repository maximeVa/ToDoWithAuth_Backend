package com.example.todowithauth.service;

import com.example.todowithauth.model.Task;
import com.example.todowithauth.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  @Autowired
  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  // Add methods to interact with TaskRepository as needed, such as:
  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  // Other methods like saveTask(), updateTask(), deleteTask(), etc.
}
