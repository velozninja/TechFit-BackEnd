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
public class workoutEditDto {
    private String name;
    private WorkoutDayWeek DayWeek;
    private String SharableTagUser;
    private String SharableTagPersonal;
    private String notes;
    private  List<exerciseDto> exercises;
    private String workoutTag;


}

