package com.TechFit.TechFit.dto.Exercise;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class exerciseDto {

    private String name;
    private int reps;
    private String targetTime;
    private String restTime;
    private String notes;
}
