package com.TechFit.TechFit.dto;



import com.TechFit.TechFit.database.model.UserEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlunoResponseDto {
    public String name;
    public String SharableTag;
    public WorkoutDTO workout;

    public AlunoResponseDto(UserEntity user) {
        this.name = user.getUsername();
        this.SharableTag = user.getEmail();
    }

}
