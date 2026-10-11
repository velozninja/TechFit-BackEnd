package com.TechFit.TechFit.database.model.workout;

import com.TechFit.TechFit.database.model.UserEntity;
import com.TechFit.TechFit.database.model.workout.Enums.WorkoutDayWeek;
import com.TechFit.TechFit.database.model.workout.Enums.WorkoutStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Workout")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_id")
    private Integer id;

    private String name;
    @Column(name = "week_days", nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkoutDayWeek weekDays;
    @ManyToOne
    private UserEntity aluno;
    @ManyToOne
    private UserEntity personal;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkoutStatus status;
    @Column(unique = true)
    private String TAG;


    private String notes;
    @ElementCollection
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<ExerciseEntity> ExerciseEntities = new ArrayList<>();


}
