package me.alextodea.testioapplication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import me.alextodea.testioapplication.model.Submission;
import me.alextodea.testioapplication.service.SubmissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class SubmissionController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @GetMapping("/api/account/submissions")
    public List<Submission> getSubmissions(@AuthenticationPrincipal OidcUser oidcUser) {
        return submissionService.getSubmissions(oidcUser);
    }

    @GetMapping("/api/exercises/{id}/submissions/report")
    public String getSubmissionSimilarityReport(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal OidcUser principal, Model model) {

        if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
            model.addAttribute("profileJson", claimsToJson(principal.getClaims()));
            model.addAttribute("plagiarismReports",
                    submissionService.getPlagiarismReports(principal));
        }

        return "submissions";
    }

    private String claimsToJson(Map<String, Object> claims) {
        try {
            return objectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(claims);
        } catch (JsonProcessingException jpe) {
            log.error("Error parsing claims to JSON", jpe);
        }
        return "Error parsing claims to JSON.";
    }

    @Bean
    public ObjectMapper objectMapper() {
        JavaTimeModule module = new JavaTimeModule();
        return new ObjectMapper()
                .registerModule(module);
    }

}
