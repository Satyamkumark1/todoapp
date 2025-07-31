package com.app.todoapp.controller;

import com.app.todoapp.models.Task;
import com.app.todoapp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller for handling web requests related to Tasks.
 * This class maps user actions to service layer operations and determines
 * which view to display.
 */
@Controller
public class TaskController {
    private final TaskService taskService;

    /**
     * Constructs a new TaskController with the given TaskService.
     * @param taskService The service for task business logic, injected by Spring.
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Displays the main page with a list of all tasks.
     * @param model The model to which the task list is added for rendering in the view.
     * @return The name of the Thymeleaf template to render ("tasks").
     */
    @GetMapping("/task")
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("categories", Task.TaskCategory.values());
        model.addAttribute("priorities", Task.TaskPriority.values());
        return "tasks";
    }

    /**
     * Creates a new task based on the form data submitted.
     * After creation, it redirects back to the main task list page.
     * @param title The title of the task from the form input.
     * @param description The description of the task.
     * @param category The category of the task.
     * @param priority The priority level of the task.
     * @return A redirect instruction to the root URL ("/").
     */
    @PostMapping("/")
    public String createTask(@RequestParam String title,
                           @RequestParam(required = false) String description,
                           @RequestParam(required = false) Task.TaskCategory category,
                           @RequestParam(required = false) Task.TaskPriority priority) {
        if (title != null && !title.trim().isEmpty()) {
            taskService.createTask(title.trim(), description, category, priority);
        }
        return "redirect:/";
    }

    /**
     * Displays the edit form for a specific task.
     * @param id The ID of the task to edit.
     * @param model The model to which the task data is added.
     * @return The name of the Thymeleaf template to render ("edit-task").
     */
    @GetMapping("/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));
        
        model.addAttribute("task", task);
        model.addAttribute("categories", Task.TaskCategory.values());
        model.addAttribute("priorities", Task.TaskPriority.values());
        return "edit-task";
    }

    /**
     * Updates an existing task with new data.
     * After updating, it redirects back to the main task list page.
     * @param id The ID of the task to update.
     * @param title The new title of the task.
     * @param description The new description of the task.
     * @param category The new category of the task.
     * @param priority The new priority level of the task.
     * @return A redirect instruction to the root URL ("/").
     */
    @PostMapping("/{id}/edit")
    public String updateTask(@PathVariable Long id,
                           @RequestParam String title,
                           @RequestParam(required = false) String description,
                           @RequestParam(required = false) Task.TaskCategory category,
                           @RequestParam(required = false) Task.TaskPriority priority) {
        if (title != null && !title.trim().isEmpty()) {
            taskService.updateTask(id, title.trim(), description, category, priority);
        }
        return "redirect:/";
    }

    /**
     * Deletes a task by its ID.
     * After deletion, it redirects back to the main task list page.
     * @param id The ID of the task to delete, captured from the URL path.
     * @return A redirect instruction to the root URL ("/").
     */
    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }

    /**
     * Toggles the completion status of a task by its ID.
     * After toggling, it redirects back to the main task list page.
     * @param id The ID of the task to toggle, captured from the URL path.
     * @return A redirect instruction to the root URL ("/").
     */
    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        taskService.toggleTask(id);
        return "redirect:/";
    }

    /**
     * Searches and filters tasks based on various criteria.
     * @param search The search term for task titles.
     * @param status The completion status filter.
     * @param category The category filter.
     * @param priority The priority filter.
     * @param model The model to which the filtered task list is added.
     * @return The name of the Thymeleaf template to render ("tasks").
     */
    @GetMapping("/search")
    public String searchTasks(@RequestParam(required = false) String search,
                             @RequestParam(required = false) Boolean status,
                             @RequestParam(required = false) Task.TaskCategory category,
                             @RequestParam(required = false) Task.TaskPriority priority,
                             Model model) {
        List<Task> tasks = taskService.searchAndFilterTasks(search, status, category, priority);
        
        model.addAttribute("tasks", tasks);
        model.addAttribute("categories", Task.TaskCategory.values());
        model.addAttribute("priorities", Task.TaskPriority.values());
        model.addAttribute("searchTerm", search);
        model.addAttribute("selectedStatus", status);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedPriority", priority);
        
        return "tasks";
    }
}