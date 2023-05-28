package me.alextodea.testioapplication.controller;

import me.alextodea.testioapplication.model.Submission;
import me.alextodea.testioapplication.repository.AppUserRepository;
import me.alextodea.testioapplication.repository.SubmissionRepository;
import me.alextodea.testioapplication.service.SubmissionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubmissionController {

    SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @GetMapping("/api/account/submissions")
    public List<Submission> getSubmissions(@AuthenticationPrincipal OidcUser oidcUser) {
        return submissionService.getSubmissions(oidcUser);
    }

}
