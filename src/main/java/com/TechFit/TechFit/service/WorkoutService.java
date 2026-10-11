package com.TechFit.TechFit.service;

import com.TechFit.TechFit.database.model.UserEntity;
import com.TechFit.TechFit.database.model.workout.Enums.WorkoutDayWeek;
import com.TechFit.TechFit.database.model.workout.Enums.WorkoutStatus;
import com.TechFit.TechFit.database.model.workout.ExerciseEntity;
import com.TechFit.TechFit.database.model.workout.WorkoutEntity;
import com.TechFit.TechFit.database.repository.IUserRepository;
import com.TechFit.TechFit.database.repository.IWorkoutRepository;
import com.TechFit.TechFit.dto.Exercise.exerciseDto;
import com.TechFit.TechFit.dto.Workout.WorkoutDTO;
import com.TechFit.TechFit.dto.Workout.workoutCreateDto;
import com.TechFit.TechFit.dto.Workout.workoutEditDto;
import com.TechFit.TechFit.exeptions.Exceptions;
import com.TechFit.TechFit.utils.DurationUtils;
import com.TechFit.TechFit.utils.GenerationRandomTag;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.valueOf;

@Service
@AllArgsConstructor
public class WorkoutService {
    private final IWorkoutRepository workoutRepository;
    private final IUserRepository userRepository;
    private final GenerationRandomTag generationRandomTag;


    public WorkoutDTO createWorkout(workoutCreateDto workoutCreateDto) throws Exceptions, BadRequestException {
        Optional<UserEntity> Aluno = Optional.ofNullable(userRepository.findBysharableTag(workoutCreateDto.getSharableTagUser())
                .orElseThrow(() -> new Exceptions.NotFound("User not found")));

        Optional<UserEntity> Personal = Optional.ofNullable(userRepository.findBysharableTag(workoutCreateDto.getSharableTagPersonal())
                .orElseThrow(() -> new Exceptions.NotFound("User not found")));

        if (Aluno.get().getPersonal().getId() != Personal.get().getId()) {
            throw new Exceptions.BadRequest("Not is your aluno");
        }




        WorkoutEntity workout = new WorkoutEntity();
        WorkoutDTO workoutDTO = new WorkoutDTO();

        workout.setName(workoutCreateDto.getName());

        workoutDTO.setName(workoutCreateDto.getName());

        workout.setNotes(workoutCreateDto.getNotes());
        workoutDTO.setNotes(workoutCreateDto.getNotes());

        workout.setWeekDays(WorkoutDayWeek.valueOf(valueOf(workoutCreateDto.getDayWeek())));
        workoutDTO.setWeekDays(valueOf(workoutCreateDto.getDayWeek()));

        workout.setAluno(Aluno.get());

        workout.setPersonal(Personal.get());
        List<ExerciseEntity> exercises = new ArrayList<>();
        String tag = generationRandomTag.generateTag();
        workout.setTAG(tag);
        workoutDTO.setTag(tag);


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
            exerciseET.setFinished(false);
            exercises.add(exerciseET);


        }
        workout.setExerciseEntities(exercises);
        workoutDTO.setExerciseEntities(exercises);

        workout.setStatus(WorkoutStatus.NOT_STARTED);


        workoutRepository.save(workout);
        return workoutDTO;
    }

    public WorkoutDTO EditWorkout(workoutEditDto workoutEditDto) throws Exceptions {

        Optional<UserEntity> Aluno = Optional.ofNullable(userRepository.findBysharableTag(workoutEditDto.getSharableTagUser())
                .orElseThrow(() -> new Exceptions.NotFound("User not found")));


        Optional<UserEntity> Personal = Optional.ofNullable(userRepository.findBysharableTag(workoutEditDto.getSharableTagPersonal()))
                .orElseThrow(() -> new Exceptions.NotFound("User not found"));

        if (Aluno.get().getPersonal().getId() != Personal.get().getId()) {
            throw new Exceptions.BadRequest("Not is your aluno");
        }

        Optional<WorkoutEntity> wk = Optional.ofNullable(workoutRepository.findByTAG(workoutEditDto.getWorkoutTag())
                .orElseThrow(() -> new Exceptions.NotFound("Workout not found")));
        workoutRepository.delete(wk.get());







        WorkoutDTO workoutDTO = new WorkoutDTO();
        WorkoutEntity workout = new WorkoutEntity();

        workout.setName(workoutEditDto.getName());
        workoutDTO.setName(workoutEditDto.getName());
        workout.setNotes(workoutEditDto.getNotes());
        workoutDTO.setNotes(workoutEditDto.getNotes());
        workout.setWeekDays(WorkoutDayWeek.valueOf(String.valueOf(workoutEditDto.getDayWeek())));
        workoutDTO.setWeekDays(String.valueOf(String.valueOf(workoutEditDto.getDayWeek())));
        workout.setAluno(Aluno.get());
        workout.setPersonal(Personal.get());

        List<ExerciseEntity> exercises = new ArrayList<>();

        for (exerciseDto exercise : workoutEditDto.getExercises()) {
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
            exerciseET.setFinished(false);
            exercises.add(exerciseET);


        }
        workout.setExerciseEntities(exercises);
        workoutDTO.setExerciseEntities(exercises);
        workoutRepository.save(workout);
        return workoutDTO;



    }

    public void DeleteWorkout(workoutEditDto workoutEditDto) throws Exceptions {
        Optional<UserEntity> Aluno = Optional.ofNullable(userRepository.findByEmail(workoutEditDto.getSharableTagUser())
                .orElseThrow(() -> new Exceptions.NotFound("User not found")));

        Optional<UserEntity> Personal = Optional.ofNullable(userRepository.findByEmail(workoutEditDto.getSharableTagPersonal())
                .orElseThrow(() -> new Exceptions.NotFound("User not found")));

        if (Aluno.get().getPersonal().getId() != Personal.get().getId()) {
            throw new Exceptions.BadRequest("Not is your aluno");
        }
        Optional<WorkoutEntity> wk = Optional.ofNullable(workoutRepository.findByTAG(workoutEditDto.getWorkoutTag())
                .orElseThrow(() -> new Exceptions.NotFound("not found workout")));




        workoutRepository.delete(wk.get());

    }
    public void startWorkout(String WorkoutSharableTag) throws Exceptions {
        WorkoutEntity workout = workoutRepository.findByTAG(WorkoutSharableTag)
                .orElseThrow(() -> new Exceptions.NotFound("Workout not found"));

        workout.setStatus(WorkoutStatus.STARTED);
        workoutRepository.save(workout);


    }
    public void finishWorkout(String WorkoutSharableTag) throws Exceptions {
        WorkoutEntity workout = workoutRepository.findByTAG(WorkoutSharableTag)
                .orElseThrow(() -> new Exceptions.NotFound("Workout not found"));

        workout.setStatus(WorkoutStatus.FINISHED);
        workoutRepository.save(workout);
    }


}