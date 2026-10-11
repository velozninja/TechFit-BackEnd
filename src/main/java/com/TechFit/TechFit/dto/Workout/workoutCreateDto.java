package com.TechFit.TechFit.dto.Workout;

import com.TechFit.TechFit.database.model.workout.Enums.WorkoutDayWeek;
import com.TechFit.TechFit.dto.Exercise.exerciseDto;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class workoutCreateDto {
    public String name;
    public WorkoutDayWeek DayWeek;
    public String SharableTagUser;
    public String SharableTagPersonal;
    private String notes;
    public List<exerciseDto> exercises;


}

