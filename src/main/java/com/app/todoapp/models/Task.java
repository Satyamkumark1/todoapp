package com.app.todoapp.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @Column
    private  String title;
    private boolean completed;

}
