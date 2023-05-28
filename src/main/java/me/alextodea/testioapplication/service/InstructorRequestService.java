package me.alextodea.testioapplication.service;

import me.alextodea.testioapplication.dto.InstructorRequestDto;
import me.alextodea.testioapplication.exception.InstructorRequestAlreadyApprovedException;
import me.alextodea.testioapplication.exception.InstructorRequestNotFoundException;
import me.alextodea.testioapplication.exception.NotAnAdminException;
import me.alextodea.testioapplication.exception.UserInstructorOrHigherException;
import me.alextodea.testioapplication.model.AppUser;
import me.alextodea.testioapplication.model.AppUserRole;
import me.alextodea.testioapplication.model.InstructorRequest;
import me.alextodea.testioapplication.repository.AppUserRepository;
import me.alextodea.testioapplication.repository.InstructorRequestRepository;
import me.alextodea.testioapplication.utils.Utils;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorRequestService {

    private AppUserRepository appUserRepository;
    private InstructorRequestRepository instructorRequestRepository;

    public InstructorRequestService(AppUserRepository appUserRepository, InstructorRequestRepository instructorRequestRepository) {
        this.appUserRepository = appUserRepository;
        this.instructorRequestRepository = instructorRequestRepository;
    }

    public List<InstructorRequest> getInstructorRequests(OidcUser principal) {

        Optional<AppUser> appUserOptional = Utils.getAppUserOptionalBySub(principal, appUserRepository);

        if (appUserOptional.isPresent() && appUserOptional.get().getRole() == AppUserRole.ADMIN) {
            return instructorRequestRepository.findAll();
        } else {
            throw new NotAnAdminException("The currently logged-in user does not have permission to view instructor requests");
        }
    }


    public InstructorRequest createRequest(OidcUser principal, InstructorRequestDto instructorRequestDto) {

        String sub = principal.getAttribute("sub");
        Optional<AppUser> appUserOptional = appUserRepository.findByAuthProviderId(sub);

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

        List<InstructorRequest> requests = appUser.getInstructorRequests();

        return requests.get(requests.size() - 1);
    }


    public InstructorRequest approveInstructorRegistrationRequest(OidcUser principal, Long requestId) {

        Optional<AppUser> appUserOptional = Utils.getAppUserOptionalBySub(principal, appUserRepository);

        if (appUserOptional.isPresent() && appUserOptional.get().getRole() == AppUserRole.ADMIN) {
            Optional<InstructorRequest> instructorRequestOptional = instructorRequestRepository.findById(requestId);

            if (instructorRequestOptional.isPresent()) {
                InstructorRequest instructorRequest = instructorRequestOptional.get();

                if (instructorRequest.isApproved()) {
                    throw new InstructorRequestAlreadyApprovedException(String.format("Instructor request with ID %d has already been approved", requestId));
                } else {
                    instructorRequest.setApproved(true);
                    AppUser submitter = instructorRequest.getSubmitter();
                    submitter.setRole(AppUserRole.INSTRUCTOR);
                    appUserRepository.save(submitter);
                    return instructorRequest;
                }

            } else {
                throw new InstructorRequestNotFoundException(String.format("Instructor request with ID %d does not exist", requestId));
            }

        } else {
            throw new NotAnAdminException("The currently logged-in user does not have permission to view instructor requests");
        }

    }


    public void declineInstructorRequest(Long requestId, OidcUser oidcUser) {

        Optional<AppUser> appUserOptional = Utils.getAppUserOptionalBySub(oidcUser, appUserRepository);

        if (appUserOptional.isPresent() && appUserOptional.get().getRole() == AppUserRole.ADMIN) {
            Optional<InstructorRequest> instructorRequestOptional = instructorRequestRepository.findById(requestId);
            if (instructorRequestOptional.isPresent()) {
                InstructorRequest instructorRequest = instructorRequestOptional.get();
                instructorRequestRepository.delete(instructorRequest);
            } else {
                throw new InstructorRequestNotFoundException(String.format("Instructor request with ID %d does not exist", requestId));
            }
        } else {
            throw new NotAnAdminException("The currently logged-in user does not have permission to decline instructor registration requests");
        }
    }

}
