package me.alextodea.testioapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class InstructorRequest {

    @Id @GeneratedValue
    private Long id;
    private String instructorRequestText;
    @ManyToOne
    private AppUser submitter;

    public InstructorRequest(String instructorRequestText, AppUser submitter) {
        this.instructorRequestText = instructorRequestText;
        this.submitter = submitter;
    }
}
