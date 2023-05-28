package me.alextodea.testioapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class AppUser {
    @Id @GeneratedValue
    private Long id;
    private String authProviderId;
    private AppUserRole role;
    private int numberOfSolvedExercises;
    @OneToMany(mappedBy = "submitter", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<InstructorRequest> instructorRequests;
    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Exercise> createdExercises;

    public AppUser(String authProviderId) {
        this.numberOfSolvedExercises = 0;
        this.authProviderId = authProviderId;
        this.instructorRequests = new ArrayList<>();
        this.createdExercises = new ArrayList<>();
        this.role = AppUserRole.USER;
    }

    public void addInstructorRequest(InstructorRequest request) {
        if (!instructorRequests.contains(request)) {
            this.instructorRequests.add(request);
            request.setSubmitter(this);
        }
    }

    public void addCreatedExercise(Exercise exercise) {
        if (!createdExercises.contains(exercise)) {
            this.createdExercises.add(exercise);
        }
    }

    public void resetNumberOfSolvedExercises() {
        this.numberOfSolvedExercises = 0;
    }

    public void increaseNumberOfSolvedExercises() {
        this.numberOfSolvedExercises += 1;
    }
}
