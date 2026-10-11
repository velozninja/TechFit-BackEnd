package com.TechFit.TechFit.controllers.v1.Workout;

import com.TechFit.TechFit.dto.Workout.workoutCreateDto;
import com.TechFit.TechFit.dto.Workout.workoutEditDto;
import com.TechFit.TechFit.service.WorkoutService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PatchMapping("/Edit")
    public ResponseEntity<workoutEditDto>  editWorkout(@RequestBody workoutEditDto workout) throws BadRequestException {
        workoutService.EditWorkout(workout);
        return ResponseEntity.ok().body(workout);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteWorkout(@RequestBody workoutEditDto workout) throws BadRequestException {
        workoutService.DeleteWorkout(workout);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/start/{workoutSharableTag}")
    public  ResponseEntity<Void> startWorkout(@PathVariable String WorkoutSharableTag) throws BadRequestException {
        workoutService.startWorkout(WorkoutSharableTag);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/finish/{workoutSharableTag}")
    public  ResponseEntity<Void> finishWorkout(@PathVariable String WorkoutSharableTag) throws BadRequestException {
        workoutService.finishWorkout(WorkoutSharableTag);
        return ResponseEntity.noContent().build();
    }


}
