package com.TechFit.TechFit.startup;

import com.TechFit.TechFit.database.model.workout.Enums.WorkoutStatus;
import com.TechFit.TechFit.database.model.workout.WorkoutEntity;
import com.TechFit.TechFit.database.repository.IWorkoutRepository;
import com.TechFit.TechFit.service.WorkoutService;
import com.TechFit.TechFit.utils.GenerationRandomTag;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkoutInitializer implements ApplicationRunner {
    private final IWorkoutRepository repository;
    private final GenerationRandomTag generator;
    @Override
    public void run(ApplicationArguments args) throws Exception {
       List<WorkoutEntity> workoutsNoTag = repository.findAllByTAGIsNull();

        for (WorkoutEntity workout : workoutsNoTag){
            workout.setTAG(generator.generateTag());
            repository.save(workout);
        }

        List<WorkoutEntity> workoutsNoStatus = repository.findAllByStatusIsNull();
        for (WorkoutEntity workout : workoutsNoStatus){
            workout.setStatus(WorkoutStatus.NOT_STARTED);
            repository.save(workout);
        }





    }
}
