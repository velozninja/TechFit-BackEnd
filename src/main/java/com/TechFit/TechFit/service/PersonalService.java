package com.TechFit.TechFit.service;

import com.TechFit.TechFit.database.model.UserEntity;
import com.TechFit.TechFit.database.model.workout.WorkoutEntity;
import com.TechFit.TechFit.database.repository.IUserRepository;
import com.TechFit.TechFit.database.repository.IWorkoutRepository;
import com.TechFit.TechFit.dto.AlunoResponseDto;
import com.TechFit.TechFit.dto.UserRequestDto;
import com.TechFit.TechFit.dto.WorkoutDTO;
import com.TechFit.TechFit.exeptions.Exceptions;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonalService {
    private final IUserRepository UserRepository;
    private final IWorkoutRepository WorkoutRepository;
    public String AddAluno(String SharableTag, UserRequestDto userPL) throws BadRequestException {
        UserEntity userQuery = UserRepository.findBysharableTag(SharableTag)
                .orElseThrow(() -> new Exceptions.NotFound("Student not found"));
        UserEntity Personal = UserRepository.findByEmail(userPL.getEmail())
                        .orElseThrow(() -> new Exceptions.NotFound("User not found"));
        userQuery.setPersonal(Personal);

        UserRepository.save(userQuery);

        if (userQuery.getRoles().getName().equals("ROLE_PERSONAL")) {
            throw new Exceptions.BadRequest("invalid role");
        }

        System.out.println("salvou aluno");




        return SharableTag;
    }
    @Cacheable(value = "alunos", key = "#SharableTag")
    public List<AlunoResponseDto> GetAlunos(String SharableTag) throws BadRequestException {
        List<UserEntity> alunos = UserRepository.findByPersonal_sharableTag(SharableTag);
        if (alunos.isEmpty()) {
            throw new Exceptions.NotFound("User not found");
        }
        List<AlunoResponseDto> alunosResponse = new ArrayList<>();
        for (UserEntity user : alunos) {
            Optional<WorkoutEntity> workout = WorkoutRepository.findByAluno(user);
            WorkoutDTO workoutDTO = new WorkoutDTO();
            if (!workout.isPresent()) {

                AlunoResponseDto Aluno =  new AlunoResponseDto();
                Aluno.setName(user.getUsername());
                Aluno.setSharableTag(user.getSharableTag());
                Aluno.setWorkout(null);

                alunosResponse.add(Aluno);
            }
            else {

                workoutDTO.setExerciseEntities(workout.get().getExerciseEntities());
                workoutDTO.setNotes(workout.get().getNotes());
                workoutDTO.setName(workout.get().getName());
                workoutDTO.setWeekDays(workout.get().getWeekDays());
                AlunoResponseDto Aluno =  new AlunoResponseDto();
                Aluno.setName(user.getUsername());
                Aluno.setSharableTag(user.getSharableTag());
                Aluno.setWorkout(null);

                alunosResponse.add(Aluno);
            }



        }
        return alunosResponse;










    }

}
