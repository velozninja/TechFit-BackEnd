package com.TechFit.TechFit.database.model.workout;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.Duration;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Embeddable
public class ExerciseEntity {

    private String name;
    private int reps;
    private Duration targetTime;
    private Duration restTime;
    private String notes;

}
