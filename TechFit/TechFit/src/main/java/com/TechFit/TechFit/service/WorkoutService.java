package com.TechFit.TechFit.service;

import com.TechFit.TechFit.database.model.UserEntity;
import com.TechFit.TechFit.database.model.workout.ExerciseEntity;
import com.TechFit.TechFit.database.model.workout.WorkoutEntity;
import com.TechFit.TechFit.database.repository.IUserRepository;
import com.TechFit.TechFit.database.repository.IWorkoutRepository;
import com.TechFit.TechFit.dto.WorkoutDTO;
import com.TechFit.TechFit.dto.exerciseDto;
import com.TechFit.TechFit.dto.workoutCreateDto;
import com.TechFit.TechFit.exeptions.Exceptions;
import com.TechFit.TechFit.utils.DurationUtils;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkoutService {
    private final IWorkoutRepository workoutRepository;
    private final IUserRepository userRepository;
 

    public WorkoutDTO createWorkout(workoutCreateDto workoutCreateDto) throws Exceptions, BadRequestException {
        Optional<UserEntity> Aluno = Optional.ofNullable(userRepository.findByEmail(workoutCreateDto.getEmailUser())
                .orElseThrow(() -> new Exceptions.NotFound("User not found")));

        Optional<UserEntity> Personal = Optional.ofNullable(userRepository.findByEmail(workoutCreateDto.getEmailPersonal())
                .orElseThrow(() -> new Exceptions.NotFound("User not found")));

        if(Aluno.get().getPersonal().getId() != Personal.get().getId()){
            throw new Exceptions.BadRequest("Not is your aluno");
        }


        WorkoutEntity workout = new WorkoutEntity();
        WorkoutDTO workoutDTO = new WorkoutDTO();

        workout.setName(workoutCreateDto.getName());
        workoutDTO.setName(workoutCreateDto.getName());

        workout.setNotes(workoutCreateDto.getNotes());
        workoutDTO.setNotes(workoutCreateDto.getNotes());

        workout.setWeekDays(workoutCreateDto.getDayWeek());
        workoutDTO.setWeekDays(workoutCreateDto.getDayWeek());

        workout.setAluno(Aluno.get());

        workout.setPersonal(Personal.get());
        List<ExerciseEntity> exercises = new ArrayList<>();


        for (exerciseDto exercise : workoutCreateDto.getExercises()) {
            String RestString = exercise.getRestTime();
            Duration Rest = DurationUtils.ParseMmSs(RestString);
            String TargeString = exercise.getTargetTime();
            Duration Target = DurationUtils.ParseMmSs(TargeString);
            ExerciseEntity exerciseET = new ExerciseEntity();
            exerciseET.setName(exercise.getName());
            exerciseET.setRestTime(Rest);
            exerciseET.setTargetTime(Target);
            exerciseET.setReps(exercise.getReps());
            exerciseET.setNotes(exercise.getNotes());
            exercises.add(exerciseET);






        }
        workout.setExerciseEntities(exercises);
        workoutDTO.setExerciseEntities(exercises);






        workoutRepository.save(workout);
        return workoutDTO;
    }
}