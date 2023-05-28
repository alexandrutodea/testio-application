package me.alextodea.testioapplication.service;

import me.alextodea.testioapplication.exception.UserNotFoundException;
import me.alextodea.testioapplication.model.AppUser;
import me.alextodea.testioapplication.model.Submission;
import me.alextodea.testioapplication.repository.AppUserRepository;
import me.alextodea.testioapplication.repository.SubmissionRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    SubmissionRepository submissionRepository;
    AppUserRepository appUserRepository;

    public SubmissionService(SubmissionRepository submissionRepository, AppUserRepository appUserRepository) {
        this.submissionRepository = submissionRepository;
        this.appUserRepository = appUserRepository;
    }

    public List<Submission> getSubmissions(OidcUser oidcUser) {

        String sub = oidcUser.getAttribute("sub");
        Optional<AppUser> appUserOptional = appUserRepository.findByAuthProviderId(sub);

        if (appUserOptional.isPresent()) {
            AppUser submitter = appUserOptional.get();
            return submissionRepository.findBySubmitter(submitter);
        } else {
            AppUser appUser = new AppUser(sub);
            appUserRepository.save(appUser);
            return new ArrayList<>();
        }


    }
}
