package com.TechFit.TechFit.controllers.v1.exercises;

import com.TechFit.TechFit.dto.ExercisesDto;
import com.TechFit.TechFit.service.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{name}")
    public ExercisesDto GetExercise(@PathVariable String name) {
        return exerciseService.GetExercise(name);
    }

}
