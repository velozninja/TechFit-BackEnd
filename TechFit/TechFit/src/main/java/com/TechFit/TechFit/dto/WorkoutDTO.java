package com.TechFit.TechFit.dto;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutDTO {

    private String name;
    private String description;
    private String category;
    private String WeekDays;






}
