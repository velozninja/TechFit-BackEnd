package com.TechFit.TechFit.controllers.v1.exercises;

import com.TechFit.TechFit.database.repository.IExercisesRepository;
import com.TechFit.TechFit.dto.ExercisesDto;
import com.TechFit.TechFit.service.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/exercise")
@AllArgsConstructor
public class ExerciseController {
    private final ExerciseService exerciseService;
    @PostMapping("/create")
    public ResponseEntity CreateExercises(@RequestBody ExercisesDto exercisesDto) {
        exerciseService.CreateExercise(exercisesDto);
        return ResponseEntity.ok().build();
    }

}
