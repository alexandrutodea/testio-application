package me.alextodea.testioapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @OneToMany
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
