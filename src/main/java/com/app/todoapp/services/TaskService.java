package com.app.todoapp.services;

import com.app.todoapp.models.Task;
import com.app.todoapp.repositery.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer that encapsulates the business logic for managing tasks.
 * This class interacts with the {@link TaskRepo} to perform database operations.
 */
@Service
public class TaskService {
    private final TaskRepo taskRepo;

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
     * Creates and saves a new task with the given details.
     * The new task is initialized with a 'completed' status of false.
     * @param title The title of the new task.
     * @param description The description of the task.
     * @param category The category of the task.
     * @param priority The priority level of the task.
     */
    public void createTask(String title, String description, Task.TaskCategory category, Task.TaskPriority priority) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setCategory(category);
        task.setPriority(priority);
        task.setCompleted(false);
        taskRepo.save(task);
    }

    /**
     * Creates a simple task with just a title (for backward compatibility).
     * @param title The title of the new task.
     */
    public void createTask(String title) {
        createTask(title, null, Task.TaskCategory.OTHER, Task.TaskPriority.MEDIUM);
    }

    /**
     * Updates an existing task with new details.
     * @param id The ID of the task to update.
     * @param title The new title.
     * @param description The new description.
     * @param category The new category.
     * @param priority The new priority.
     * @throws IllegalArgumentException if no task with the given ID is found.
     */
    public void updateTask(Long id, String title, String description, Task.TaskCategory category, Task.TaskPriority priority) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));
        
        task.setTitle(title);
        task.setDescription(description);
        task.setCategory(category);
        task.setPriority(priority);
        taskRepo.save(task);
    }

    /**
     * Retrieves a task by its ID.
     * @param id The ID of the task to retrieve.
     * @return Optional containing the task if found.
     */
    public Optional<Task> getTaskById(Long id) {
        return taskRepo.findById(id);
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
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));
        task.setCompleted(!task.isCompleted());
        taskRepo.save(task);
    }

    /**
     * Searches tasks by title (case-insensitive).
     * @param searchTerm The search term to look for in task titles.
     * @return List of tasks matching the search term.
     */
    public List<Task> searchTasksByTitle(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllTasks();
        }
        return taskRepo.findByTitleContainingIgnoreCase(searchTerm.trim());
    }

    /**
     * Filters tasks by completion status.
     * @param completed The completion status to filter by.
     * @return List of tasks with the specified completion status.
     */
    public List<Task> filterTasksByStatus(Boolean completed) {
        if (completed == null) {
            return getAllTasks();
        }
        return taskRepo.findByCompleted(completed);
    }

    /**
     * Filters tasks by category.
     * @param category The category to filter by.
     * @return List of tasks in the specified category.
     */
    public List<Task> filterTasksByCategory(Task.TaskCategory category) {
        if (category == null) {
            return getAllTasks();
        }
        return taskRepo.findByCategory(category);
    }

    /**
     * Filters tasks by priority.
     * @param priority The priority to filter by.
     * @return List of tasks with the specified priority.
     */
    public List<Task> filterTasksByPriority(Task.TaskPriority priority) {
        if (priority == null) {
            return getAllTasks();
        }
        return taskRepo.findByPriority(priority);
    }

    /**
     * Advanced search with multiple filters.
     * @param searchTerm The search term for title.
     * @param completed The completion status filter.
     * @param category The category filter.
     * @param priority The priority filter.
     * @return List of tasks matching all specified criteria.
     */
    public List<Task> searchAndFilterTasks(String searchTerm, Boolean completed, Task.TaskCategory category, Task.TaskPriority priority) {
        if (searchTerm == null && completed == null && category == null && priority == null) {
            return getAllTasks();
        }
        return taskRepo.findBySearchAndFilters(searchTerm, completed, category, priority);
    }
}
