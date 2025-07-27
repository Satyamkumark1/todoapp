package com.app.todoapp.services;

import com.app.todoapp.models.Task;
import com.app.todoapp.repositery.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer that encapsulates the business logic for managing tasks.
 * This class interacts with the {@link TaskRepo} to perform database operations.
 */
@Service
public class TaskService {
    private  final TaskRepo taskRepo;

    /**
     * Constructs a new TaskService with the given TaskRepo.
     * @param taskRepo The repository for task data access, injected by Spring.
     */
    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    /**
     * Retrieves all tasks from the database.
     * @return a list of all {@link Task} objects.
     */
    public List<Task> getAllTasks() {
         return taskRepo.findAll();
    }

    /**
     * Creates and saves a new task with the given title.
     * The new task is initialized with a 'completed' status of false.
     * @param title The title of the new task.
     */
    public void createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepo.save(task);
    }

    /**
     * Deletes a task by its unique identifier.
     * @param id The ID of the task to delete.
     */
    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
    /**
     * Toggles the completion status of a specific task.
     * If the task is completed, it will be marked as incomplete, and vice versa.
     * @param id The ID of the task to toggle.
     * @throws IllegalArgumentException if no task with the given ID is found.
     */

    public void toggleTask(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid"));

        task.setCompleted(!task.isCompleted());
        taskRepo.save(task);


    }


}
