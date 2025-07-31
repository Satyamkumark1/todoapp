package com.app.todoapp.repositery;

import com.app.todoapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the {@link Task} entity.
 * This interface provides standard CRUD (Create, Read, Update, Delete) operations
 * for tasks without requiring any implementation.
 */
public interface TaskRepo extends JpaRepository<Task, Long> {
    
    /**
     * Find tasks by title containing the search term (case-insensitive).
     * @param searchTerm The search term to look for in task titles.
     * @return List of tasks matching the search term.
     */
    List<Task> findByTitleContainingIgnoreCase(String searchTerm);
    
    /**
     * Find tasks by completion status.
     * @param completed The completion status to filter by.
     * @return List of tasks with the specified completion status.
     */
    List<Task> findByCompleted(Boolean completed);
    
    /**
     * Find tasks by category.
     * @param category The category to filter by.
     * @return List of tasks in the specified category.
     */
    List<Task> findByCategory(Task.TaskCategory category);
    
    /**
     * Find tasks by priority.
     * @param priority The priority to filter by.
     * @return List of tasks with the specified priority.
     */
    List<Task> findByPriority(Task.TaskPriority priority);
    
    /**
     * Advanced search with multiple filters using custom query.
     * @param searchTerm The search term for title (can be null).
     * @param completed The completion status filter (can be null).
     * @param category The category filter (can be null).
     * @param priority The priority filter (can be null).
     * @return List of tasks matching all specified criteria.
     */
    @Query("SELECT t FROM Task t WHERE " +
           "(:searchTerm IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) AND " +
           "(:completed IS NULL OR t.completed = :completed) AND " +
           "(:category IS NULL OR t.category = :category) AND " +
           "(:priority IS NULL OR t.priority = :priority) " +
           "ORDER BY t.createdAt DESC")
    List<Task> findBySearchAndFilters(@Param("searchTerm") String searchTerm,
                                     @Param("completed") Boolean completed,
                                     @Param("category") Task.TaskCategory category,
                                     @Param("priority") Task.TaskPriority priority);
}
