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

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Displays the main page with all tasks.
     */
    @GetMapping("/")
    public String getTasks(Model model) {
        // FIX: Corrected typo from getAllTaks() to getAllTasks()
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    /**
     * Creates a new task from the form submission.
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
     */
    // FIX: Changed @RequestParam to @PathVariable to match the URL pattern.
    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }

    /**
     * Toggles the completion status of a task.
     */
    // FIX: Corrected the duplicate mapping and changed to @PathVariable.
    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        taskService.toggleTask(id);
        return "redirect:/";
    }
}