package com.TechFit.TechFit.database.repository;


import com.TechFit.TechFit.database.model.UserEntity;
import com.TechFit.TechFit.database.model.workout.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IWorkoutRepository extends JpaRepository<WorkoutEntity, Integer> {
    Optional<WorkoutEntity> findByNameAndAluno(String name, UserEntity Aluno);
    Optional<WorkoutEntity> findById(Integer id);
    Optional<WorkoutEntity> findByName(String name);
    void delete(WorkoutEntity workoutEntity);

}
