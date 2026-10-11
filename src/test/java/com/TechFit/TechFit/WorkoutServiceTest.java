package com.TechFit.TechFit;

import com.TechFit.TechFit.controllers.v1.Workout.Workout;
import com.TechFit.TechFit.database.model.workout.WorkoutEntity;
import com.TechFit.TechFit.database.repository.IWorkoutRepository;
import com.TechFit.TechFit.dto.Workout.WorkoutDTO;
import com.TechFit.TechFit.service.WorkoutService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.mockito.Mockito.when;


@Transactional

@ExtendWith(MockitoExtension.class)
@Rollback
public class WorkoutServiceTest {
    @InjectMocks
    private WorkoutService workoutService;
    @Mock
    private IWorkoutRepository repository;



    @Test
    void startWorkoutTeste(){
        WorkoutEntity workout = new WorkoutEntity();
        when(repository.findByTAG("URGJQRWR"))
                .thenReturn(Optional.of(workout));
        workoutService.startWorkout("URGJQRWR");

    }
    @Test
    void finishWorkoutTeste(){
        WorkoutEntity workout = new WorkoutEntity();
        when(repository.findByTAG("URGJQRWR"))
                .thenReturn(Optional.of(workout));
        workoutService.finishWorkout("URGJQRWR");

    }


}
