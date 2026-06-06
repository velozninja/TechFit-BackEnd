package com.TechFit.TechFit.database.repository;

import com.TechFit.TechFit.database.model.ExercisesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExercisesRepository extends JpaRepository<ExercisesEntity, Integer> {
}
