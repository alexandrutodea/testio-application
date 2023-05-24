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
    @OneToMany(mappedBy = "submitter", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<InstructorRequest> instructorRequests;

    public AppUser(String authProviderId) {
        this.authProviderId = authProviderId;
        this.instructorRequests = new ArrayList<>();
    }

    public void addInstructorRequest(InstructorRequest request) {
        if (!instructorRequests.contains(request)) {
            this.instructorRequests.add(request);
            request.setSubmitter(this);
        }
    }

}
