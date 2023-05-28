package me.alextodea.testioapplication.service;

import me.alextodea.testioapplication.dto.ExerciseDto;
import me.alextodea.testioapplication.dto.SubmissionDto;
import me.alextodea.testioapplication.exception.ExerciseNotFoundException;
import me.alextodea.testioapplication.exception.NotAnInstructorException;
import me.alextodea.testioapplication.model.AppUser;
import me.alextodea.testioapplication.model.AppUserRole;
import me.alextodea.testioapplication.model.Exercise;
import me.alextodea.testioapplication.model.Submission;
import me.alextodea.testioapplication.repository.AppUserRepository;
import me.alextodea.testioapplication.repository.ExerciseRepository;
import me.alextodea.testioapplication.repository.SubmissionRepository;
import me.alextodea.testioapplication.utils.Utils;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    private ExerciseRepository exerciseRepository;
    private AppUserRepository appUserRepository;
    private SubmissionRepository submissionRepository;

    public ExerciseService(ExerciseRepository exerciseRepository, AppUserRepository appUserRepository, SubmissionRepository submissionRepository) {
        this.exerciseRepository = exerciseRepository;
        this.appUserRepository = appUserRepository;
        this.submissionRepository = submissionRepository;
    }

    public Exercise createExercise(ExerciseDto exerciseDto, OidcUser principal) {

        String sub = principal.getAttribute("sub");
        Optional<AppUser> appUserOptional = appUserRepository.findByAuthProviderId(sub);
        AppUser appUser;

        if (appUserOptional.isPresent()) {
            appUser = appUserOptional.get();

            if (appUser.getRole() == AppUserRole.USER) {
                throw new NotAnInstructorException("You must be an instructor or higher to create an exercise");
            }


        } else {
            throw new NotAnInstructorException("You must be an instructor or higher to create an exercise");
        }

        String description = exerciseDto.getDescription();
        String mainClass = exerciseDto.getMainClass();
        String prefilledCode = exerciseDto.getPrefilledCode();
        String testClass = exerciseDto.getTestClass();

        Exercise exercise = new Exercise(appUser, mainClass, description, testClass, prefilledCode);
        appUser.addCreatedExercise(exercise);
        appUserRepository.save(appUser);

        return exerciseRepository.save(exercise);

    }

    public List<Exercise> listExercises(OidcUser principal) {

        Optional<AppUser> appUserOptional = Utils.getAppUserOptionalBySub(principal, appUserRepository);
        AppUser appUser;

        if (appUserOptional.isPresent()) {
            appUser = appUserOptional.get();

            if (appUser.getRole() == AppUserRole.USER) {
                throw new NotAnInstructorException("You must be an instructor or higher to view exercises in the system");
            } else if (appUser.getRole() == AppUserRole.ADMIN) {
                return exerciseRepository.findAll();
            } else {
                return exerciseRepository.findExerciseByAuthor(appUser);
            }

        } else {
            throw new NotAnInstructorException("You must be an instructor or higher to view exercises in the system");
        }

    }

    public Exercise listExerciseById(OidcUser principal, Long id) {

        String sub = principal.getAttribute("sub");
        Optional<AppUser> appUserOptional = appUserRepository.findByAuthProviderId(sub);

        if (appUserOptional.isEmpty()) {
            appUserRepository.save(new AppUser(sub));
        }

        Optional<Exercise> byId = exerciseRepository.findById(id);

        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new ExerciseNotFoundException(String.format("Exercise with ID %d does not exist", id));
        }

    }

    public void deleteExerciseById(Long id, OidcUser principal) {

        Optional<AppUser> appUserOptional = Utils.getAppUserOptionalBySub(principal, appUserRepository);
        AppUser appUser;

        if (appUserOptional.isPresent()) {
            appUser = appUserOptional.get();

            if (appUser.getRole() == AppUserRole.USER) {
                throw new NotAnInstructorException("You must be an instructor or higher to delete exercises from the system");
            } else if (appUser.getRole() == AppUserRole.ADMIN) {
                Optional<Exercise> byId = exerciseRepository.findById(id);
                if (byId.isPresent()) {
                    Exercise exercise = byId.get();
                    exerciseRepository.delete(exercise);
                } else {
                    throw new ExerciseNotFoundException(String.format("Exercise with ID %d does not exist", id));
                }
            } else {
                Optional<Exercise> byId = exerciseRepository.findById(id);
                if (byId.isPresent() && byId.get().getAuthor().equals(appUser)) {
                    Exercise exercise = byId.get();
                    exerciseRepository.delete(exercise);
                } else {
                    throw new ExerciseNotFoundException(String.format("Exercise with ID %d does not exist", id));
                }
            }

        } else {
            throw new NotAnInstructorException("You must be an instructor or higher to delete exercises from the system");
        }

    }

    public Exercise updateExerciseById(Long id, ExerciseDto exerciseDto, OidcUser principal) {

        Optional<AppUser> appUserOptional = Utils.getAppUserOptionalBySub(principal, appUserRepository);
        AppUser appUser;

        if (appUserOptional.isPresent()) {
            appUser = appUserOptional.get();

            if (appUser.getRole() == AppUserRole.USER) {
                throw new NotAnInstructorException("You must be an instructor or higher to update exercises in the system");
            } else if (appUser.getRole() == AppUserRole.ADMIN) {
                Optional<Exercise> byId = exerciseRepository.findById(id);
                if (byId.isPresent()) {
                    Exercise exercise = byId.get();
                    updateExercise(exercise, exerciseDto);
                    return exerciseRepository.save(exercise);
                } else {
                    throw new ExerciseNotFoundException(String.format("Exercise with ID %d does not exist", id));
                }
            } else {
                Optional<Exercise> byId = exerciseRepository.findById(id);
                if (byId.isPresent() && byId.get().getAuthor().equals(appUser)) {
                    Exercise exercise = byId.get();
                    updateExercise(exercise, exerciseDto);
                    return exerciseRepository.save(exercise);
                } else {
                    throw new ExerciseNotFoundException(String.format("Exercise with ID %d does not exist", id));
                }
            }

        } else {
            throw new NotAnInstructorException("You must be an instructor or higher to update exercises in the system");
        }

    }

    public static void updateExercise(Exercise exercise, ExerciseDto exerciseDto) {
        exercise.setDescription(exerciseDto.getDescription());
        exercise.setMainClass(exerciseDto.getMainClass());
        exercise.setTestClass(exerciseDto.getTestClass());
        exercise.setPrefilledCode(exerciseDto.getPrefilledCode());
    }

    public Exercise submitExerciseSolution(SubmissionDto submissionDto, Long id, OidcUser principal) {

        Optional<Exercise> byId = exerciseRepository.findById(id);

        if (byId.isPresent()) {
            Exercise exercise = byId.get();

            Optional<AppUser> appUserOptional = Utils.getAppUserOptionalBySub(principal, appUserRepository);
            AppUser appUser;

            if (appUserOptional.isPresent()) {
                appUser = appUserOptional.get();
            } else {
                String sub = principal.getAttribute("sub");
                appUser = new AppUser(sub);
                appUserRepository.save(appUser);
            }

            appUser.increaseNumberOfSolvedExercises();

            Submission submission = new Submission(exercise, appUser, submissionDto.getSubmission());
            exercise.addSubmission(submission);
            exerciseRepository.save(exercise);

            return exercise;

        } else {
            throw new ExerciseNotFoundException(String.format("Exercise with ID %d does not exist", id));
        }

    }

    public List<Exercise> findUnsolvedExercises(OidcUser principal) {

        String sub = principal.getAttribute("sub");
        Optional<AppUser> appUserOptional = appUserRepository.findByAuthProviderId(sub);
        List<Exercise> exercises = exerciseRepository.findAll();

        if (appUserOptional.isPresent()) {

            List<Submission> submissions = submissionRepository.findAll();
            AppUser appUser = appUserOptional.get();

            for (Submission submission : submissions) {

                if (submission.getSubmitter().equals(appUser)) {
                    Exercise exercise = submission.getExercise();
                    exercises.remove(exercise);
                }
            }

        } else {
            appUserRepository.save(new AppUser(sub));
        }

        return exercises;

    }

    public List<Submission> findSubmissions(Long id, OidcUser principal) {

        String sub = principal.getAttribute("sub");
        Optional<AppUser> appUserOptional = appUserRepository.findByAuthProviderId(sub);

        if (appUserOptional.isEmpty()) {
            AppUser appUser = new AppUser(sub);
            appUserRepository.save(appUser);
        }

        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);

        if (exerciseOptional.isPresent()) {
            Exercise exercise = exerciseOptional.get();
            return exercise.getSubmissions();
        } else {
            throw new ExerciseNotFoundException(String.format("Exercise with ID %d does not exist", id));
        }

    }

    public Submission findSubmissionById(Long id, OidcUser principal) {

        String sub = principal.getAttribute("sub");
        Optional<AppUser> appUserOptional = appUserRepository.findByAuthProviderId(sub);

        if (appUserOptional.isEmpty()) {
            AppUser appUser = new AppUser(sub);
            appUserRepository.save(appUser);
        }

        Optional<Submission> submissionOptional = submissionRepository.findById(id);

        if (submissionOptional.isPresent()) {
            return submissionOptional.get();
        } else {
            throw new ExerciseNotFoundException(String.format("Exercise with ID %d does not exist", id));
        }

    }
}
