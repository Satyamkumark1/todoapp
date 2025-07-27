package com.app.todoapp.repositery;

import com.app.todoapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Task} entity.
 * This interface provides standard CRUD (Create, Read, Update, Delete) operations
 * for tasks without requiring any implementation.
 */
public interface TaskRepo extends JpaRepository<Task, Long> {

}
