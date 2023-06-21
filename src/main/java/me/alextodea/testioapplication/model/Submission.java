package me.alextodea.testioapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Submission {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    private Exercise exercise;
    @Column(columnDefinition = "TEXT")
    private String solution;
    @OneToOne
    private AppUser submitter;

    public Submission(Exercise exercise, AppUser appUser, String solution) {
        this.submitter = appUser;
        this.exercise = exercise;
        this.solution = solution;
    }

    public CompilationUnit getSolutionCompilationUnit() {
        return StaticJavaParser.parse(solution);
    }

}
