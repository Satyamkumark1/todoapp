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
    @GetMapping("/")
    public String getTasks(Model model) {
        // FIX: Corrected typo from getAllTaks() to getAllTasks()
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    /**
     * Creates a new task based on the title submitted from the form.
     * After creation, it redirects back to the main task list page.
     * @param title The title of the task from the form input.
     * @return A redirect instruction to the root URL ("/").
     */
    @PostMapping("/")
    public String createTask(@RequestParam String title) {
        // IMPROVEMENT: Prevent creating tasks with empty titles.
        if (title != null && !title.trim().isEmpty()) {
            taskService.createTask(title);
        }
        return "redirect:/";
    }

    /**
     * Deletes a task by its ID.
     * After deletion, it redirects back to the main task list page.
     * @param id The ID of the task to delete, captured from the URL path.
     * @return A redirect instruction to the root URL ("/").
     */
    // FIX: Changed @RequestParam to @PathVariable to match the URL pattern.
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
}