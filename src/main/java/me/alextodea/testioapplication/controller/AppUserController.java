package me.alextodea.testioapplication.controller;

import jakarta.validation.Valid;
import me.alextodea.testioapplication.dto.InstructorRequestDto;
import me.alextodea.testioapplication.exception.UserInstructorOrHigherException;
import me.alextodea.testioapplication.model.AppUser;
import me.alextodea.testioapplication.model.AppUserRole;
import me.alextodea.testioapplication.model.InstructorRequest;
import me.alextodea.testioapplication.repository.AppUserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AppUserController {

    private AppUserRepository appUserRepository;

    public AppUserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/api/requests")
    public InstructorRequest createRequest(@AuthenticationPrincipal OidcUser principal,
                                           @Valid @RequestBody InstructorRequestDto instructorRequestDto) {


        String sub = principal.getAttribute("sub");

        Optional<AppUser> appUserOptional = appUserRepository.findByAuthProviderId("sub");

        AppUser appUser;

        if (appUserOptional.isPresent()) {

            appUser = appUserOptional.get();
            if (appUser.getRole() == AppUserRole.ADMIN || appUser.getRole() == AppUserRole.INSTRUCTOR) {
                throw new UserInstructorOrHigherException("User is already an instructor or higher");
            }

        } else {

            appUser = new AppUser(sub);

        }

        String requestText = instructorRequestDto.getInstructorRequestText();
        InstructorRequest instructorRequest = new InstructorRequest(requestText, appUser);
        appUser.addInstructorRequest(instructorRequest);

        appUserRepository.save(appUser);

        return instructorRequest;

    }

}
