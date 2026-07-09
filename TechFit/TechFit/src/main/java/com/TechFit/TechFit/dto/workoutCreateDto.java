package com.TechFit.TechFit.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class workoutCreateDto {
    public String name;
    public String dayWeek;
    public String EmailUser;
    public String EmailPersonal;
    private String notes;
    public List<exerciseDto> exercises;
}

