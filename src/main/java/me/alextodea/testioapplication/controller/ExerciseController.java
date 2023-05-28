package me.alextodea.testioapplication.controller;

import jakarta.validation.Valid;
import me.alextodea.testioapplication.dto.ExerciseDto;
import me.alextodea.testioapplication.dto.SubmissionDto;
import me.alextodea.testioapplication.model.Exercise;
import me.alextodea.testioapplication.model.Submission;
import me.alextodea.testioapplication.service.ExerciseService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExerciseController {

    ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping("/api/exercises")
    public Exercise createExercise(@Valid @RequestBody ExerciseDto exerciseDto,
                                   @AuthenticationPrincipal OidcUser principal) {

        return exerciseService.createExercise(exerciseDto, principal);

    }

    @PostMapping("/api/exercises/{id}/submit")
    public Exercise submitExerciseSolution(@Valid @RequestBody SubmissionDto submissionDto,
                                           @PathVariable("id") Long id,
                                           @AuthenticationPrincipal OidcUser principal) {

        return exerciseService.submitExerciseSolution(submissionDto, id, principal);

    }


    @GetMapping("/api/exercises")
    public List<Exercise> listExerciseById(@AuthenticationPrincipal OidcUser principal) {

        return exerciseService.listExercises(principal);

    }

    @GetMapping("/api/exercises/{id}")
    public Exercise listExerciseById(@PathVariable("id") Long id,
                                     @AuthenticationPrincipal OidcUser principal) {

        return exerciseService.listExerciseById(principal, id);

    }

    @PutMapping("/api/exercises/{id}")
    public Exercise updateExerciseById(
            @RequestBody ExerciseDto exerciseDto,
            @PathVariable("id") Long id,
            @AuthenticationPrincipal OidcUser principal) {

        return exerciseService.updateExerciseById(id, exerciseDto, principal);

    }

    @DeleteMapping("/api/exercises/{id}")
    public void deleteExerciseById(@PathVariable("id") Long id,
                                     @AuthenticationPrincipal OidcUser principal) {

        exerciseService.deleteExerciseById(id, principal);

    }

    @GetMapping("/api/exercises/solve")
    public List<Exercise> findUnsolvedExercises(@AuthenticationPrincipal OidcUser oidcUser) {

        return exerciseService.findUnsolvedExercises(oidcUser);

    }

    @GetMapping("/api/exercises/{id}/submissions")
    public List<Submission> getSubmissions(@PathVariable("id") Long id,
                                           @AuthenticationPrincipal OidcUser oidcUser) {

        return exerciseService.findSubmissions(id, oidcUser);

    }

    @GetMapping("/api/submissions/{id}")
    public Submission getSubmissionById(@PathVariable("id") Long submissionId,
                                           @AuthenticationPrincipal OidcUser oidcUser) {

        return exerciseService.findSubmissionById(submissionId, oidcUser);

    }

}
