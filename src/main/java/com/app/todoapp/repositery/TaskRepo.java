package com.app.todoapp.repositery;

import com.app.todoapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {

}
