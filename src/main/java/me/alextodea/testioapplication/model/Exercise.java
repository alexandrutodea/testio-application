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
public class Exercise {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    @JsonIgnore
    private AppUser author;
    private String description;
    @Column(columnDefinition = "TEXT")
    private String mainClass;
    @Column(columnDefinition = "TEXT")
    private String testClass;
    @Column(columnDefinition = "TEXT")
    private String prefilledCode;
    @JsonIgnore
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<Submission> submissions;

    public Exercise(AppUser author, String mainClass, String description, String testClass, String prefilledCode) {
        this.author = author;
        this.mainClass = mainClass;
        this.description = description;
        this.testClass = testClass;
        this.prefilledCode = prefilledCode;
        this.submissions = new ArrayList<>();
    }

    public void addSubmission(Submission submission) {
        submissions.add(submission);
    }
}
