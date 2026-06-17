package com.TechFit.TechFit.service;

import com.TechFit.TechFit.database.model.ExercisesEntity;
import com.TechFit.TechFit.database.repository.IExercisesRepository;
import com.TechFit.TechFit.dto.ExercisesDto;
import com.TechFit.TechFit.exeptions.AlreadyExist;
import com.TechFit.TechFit.exeptions.NotFound;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ExerciseService {
    private final IExercisesRepository exercisesRepository;
    public ExercisesDto CreateExercise(ExercisesDto exercisesDto) {
        if (exercisesRepository.findByName(exercisesDto.getName()).isPresent()) {
            throw new AlreadyExist("already exist this exercise");
        }
        ExercisesEntity exercisesEntity = new ExercisesEntity();
        exercisesEntity.setName(exercisesDto.getName());
        exercisesEntity.setMeters(exercisesDto.getMeters());
        exercisesEntity.setReps(exercisesDto.getReps());
        exercisesRepository.save(exercisesEntity);
        return exercisesDto;
    }


    public ExercisesDto GetExercise(String name) throws NotFound {
        Optional<ExercisesEntity> exercisesEntity = exercisesRepository.findByName(name);
        if (exercisesEntity.isPresent()) {
            ExercisesDto exercisesDto = new ExercisesDto();
            exercisesDto.setName(exercisesEntity.get().getName());
            exercisesDto.setMeters(exercisesEntity.get().getMeters());
            exercisesDto.setReps(exercisesEntity.get().getReps());

            return exercisesDto;

        }
        throw new NotFound("exercise not found");
    }

}
