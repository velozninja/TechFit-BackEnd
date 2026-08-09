package com.TechFit.TechFit.dto;

import com.TechFit.TechFit.database.model.workout.ExerciseEntity;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutDTO {

    private String name;
    private String WeekDays;
    private String notes;
    private List<ExerciseEntity> ExerciseEntities;

}
