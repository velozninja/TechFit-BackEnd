package com.TechFit.TechFit.database.repository;

import com.TechFit.TechFit.database.model.ExercisesEntity;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IExercisesRepository extends JpaRepository<ExercisesEntity, Integer> {
    Optional<ExercisesEntity> findByName(String name);
}
