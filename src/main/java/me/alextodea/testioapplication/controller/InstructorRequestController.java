package me.alextodea.testioapplication.controller;

import jakarta.validation.Valid;
import me.alextodea.testioapplication.dto.InstructorRequestDto;
import me.alextodea.testioapplication.model.InstructorRequest;
import me.alextodea.testioapplication.service.InstructorRequestService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InstructorRequestController {

    private InstructorRequestService instructorRequestService;

    public InstructorRequestController(InstructorRequestService instructorRequestService) {
        this.instructorRequestService = instructorRequestService;
    }

    @GetMapping("/api/requests")
    public List<InstructorRequest> getInstructorRequests(@AuthenticationPrincipal OidcUser principal) {
        return instructorRequestService.getInstructorRequests(principal);
    }

    @PostMapping("/api/requests")
    public InstructorRequest createRequest(@AuthenticationPrincipal OidcUser principal,
                                           @Valid @RequestBody InstructorRequestDto instructorRequestDto) {
        return instructorRequestService.createRequest(principal, instructorRequestDto);
    }

    @PutMapping("/api/requests/{requestId}/approve")
    public InstructorRequest approveInstructorRegistrationRequest(@AuthenticationPrincipal OidcUser principal, @PathVariable Long requestId) {
        return instructorRequestService.approveInstructorRegistrationRequest(principal, requestId);
    }

    @DeleteMapping("/api/requests/{id}/decline")
    public void removeInstructorRequest(@PathVariable Long id, @AuthenticationPrincipal OidcUser oidcUser) {
        instructorRequestService.declineInstructorRequest(id, oidcUser);
    }

}
