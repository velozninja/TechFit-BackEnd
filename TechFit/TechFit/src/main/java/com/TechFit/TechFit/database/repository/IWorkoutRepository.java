package com.TechFit.TechFit.database.repository;


import com.TechFit.TechFit.database.model.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWorkoutRepository extends JpaRepository<WorkoutEntity, Integer> {
}
