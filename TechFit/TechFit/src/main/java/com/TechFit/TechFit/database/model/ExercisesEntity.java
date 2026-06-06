package com.TechFit.TechFit.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "exercises")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExercisesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer Metters;
    private String description;
    private Integer reps;

    @ManyToMany
    @JoinTable(
            name = "Workout_exercises",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")

    )
    private List<ExercisesEntity> Exercises = new ArrayList<>();

}
