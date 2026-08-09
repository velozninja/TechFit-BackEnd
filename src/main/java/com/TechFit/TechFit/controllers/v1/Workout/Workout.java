package com.TechFit.TechFit.controllers.v1.Workout;

import com.TechFit.TechFit.dto.UserRequestDto;
import com.TechFit.TechFit.dto.WorkoutDTO;
import com.TechFit.TechFit.dto.workoutCreateDto;
import com.TechFit.TechFit.service.WorkoutService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/workout")
@AllArgsConstructor
public class Workout {
    private final WorkoutService workoutService;
    @PostMapping("/create")
    public ResponseEntity<workoutCreateDto> addWorkout(@RequestBody workoutCreateDto workout) throws BadRequestException {
        workoutService.createWorkout(workout);
        return ResponseEntity.ok().body(workout);
    }
    @PatchMapping("/{OriginName}")
    public ResponseEntity<workoutCreateDto>  editWorkout(@RequestBody workoutCreateDto workout, @PathVariable String OriginName) throws BadRequestException {
        workoutService.EditWorkout(workout, OriginName);
        return ResponseEntity.ok().body(workout);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteWorkout(@RequestBody workoutCreateDto workout) throws BadRequestException {
        workoutService.DeleteWorkout(workout);
        return ResponseEntity.noContent().build();
    }

}
