package com.TechFit.TechFit.dto;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.Duration;


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
