package me.alextodea.testioapplication.controller;

import me.alextodea.testioapplication.dto.InstructorRequestDto;
import me.alextodea.testioapplication.model.AppUser;
import me.alextodea.testioapplication.model.InstructorRequest;
import me.alextodea.testioapplication.repository.AppUserRepository;
import me.alextodea.testioapplication.repository.InstructorRequestRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class InstructorRequestController {

    private AppUserRepository appUserRepository;
    private InstructorRequestRepository instructorRequestRepository;

    public InstructorRequestController(AppUserRepository appUserRepository, InstructorRequestRepository instructorRequestRepository) {
        this.appUserRepository = appUserRepository;
        this.instructorRequestRepository = instructorRequestRepository;
    }

    @PostMapping("/api/requests")
    public InstructorRequest createRequest(@AuthenticationPrincipal OidcUser principal,
                                           @RequestBody InstructorRequestDto instructorRequestDto) {

        String sub = principal.getAttribute("sub");
        AppUser appUser = new AppUser(sub);
        String request = instructorRequestDto.getInstructorRequestText();
        InstructorRequest instructorRequest = new InstructorRequest(request, appUser);
        appUserRepository.save(appUser);
        instructorRequestRepository.save(instructorRequest);
        return instructorRequest;

    }

}
