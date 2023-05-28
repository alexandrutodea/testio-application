package me.alextodea.testioapplication.service;

import me.alextodea.testioapplication.exception.CannotModifyAdminException;
import me.alextodea.testioapplication.exception.NotAnAdminException;
import me.alextodea.testioapplication.exception.UserNotFoundException;
import me.alextodea.testioapplication.model.AppUser;
import me.alextodea.testioapplication.model.AppUserRole;
import me.alextodea.testioapplication.repository.AppUserRepository;
import me.alextodea.testioapplication.utils.Utils;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    private AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser accountInformation(OidcUser principal) {

        String sub = principal.getAttribute("sub");
        Optional<AppUser> appUserOptional = appUserRepository.findByAuthProviderId(sub);

        if (appUserOptional.isPresent()) {
            return appUserOptional.get();
        } else {
            AppUser appUser = new AppUser(sub);
            appUserRepository.save(appUser);
            return appUser;
        }

    }

    public AppUser resetProgress(OidcUser principal) {

        String sub = principal.getAttribute("sub");
        Optional<AppUser> appUserOptional = appUserRepository.findByAuthProviderId(sub);

        if (appUserOptional.isPresent()) {
            AppUser appUser = appUserOptional.get();
            appUser.resetNumberOfSolvedExercises();
            appUserRepository.save(appUser);
            return appUser;
        } else {
            AppUser appUser = new AppUser(sub);
            appUserRepository.save(appUser);
            return appUser;
        }

    }

    public List<AppUser> getAppUsers(OidcUser principal) {

        String sub = principal.getAttribute("sub");
        Optional<AppUser> appUserOptional = appUserRepository.findByAuthProviderId(sub);

        if (appUserOptional.isPresent() && appUserOptional.get().getRole() == AppUserRole.ADMIN) {
            return appUserRepository.findAll();
        } else {
            throw new NotAnAdminException("The currently logged-in user does not have the permission to view users in the system");
        }

    }

    public AppUser modifyUser(Long id, UserModification userModification, OidcUser principal) {

        Optional<AppUser> loggedInUserOptional = Utils.getAppUserOptionalBySub(principal, appUserRepository);

        if (loggedInUserOptional.isPresent() && loggedInUserOptional.get().getRole() == AppUserRole.ADMIN) {

            Optional<AppUser> targetUserOptional = appUserRepository.findById(id);

            if (targetUserOptional.isPresent()) {

                AppUser targetUser = targetUserOptional.get();

                if (targetUser.getRole() == AppUserRole.ADMIN) {
                    throw new CannotModifyAdminException(String.format("User with ID %d is an administrator and cannot be modified", id));
                } else {

                    switch (userModification) {
                        case DEMOTE -> {
                            targetUser.setRole(AppUserRole.USER);
                            appUserRepository.save(targetUser);
                            return targetUser;
                        }
                        case PROMOTE -> {
                            targetUser.setRole(AppUserRole.ADMIN);
                            appUserRepository.save(targetUser);
                            return targetUser;
                        }
                        case REMOVE -> {
                            appUserRepository.delete(targetUser);
                            return targetUser;

                        } default -> {
                            return targetUser;
                        }
                    }

                }

            } else {
                throw new UserNotFoundException(String.format("User with ID %d does not exist", id));
            }

        } else {
            throw new NotAnAdminException("The currently logged-in user does not have the permission to modify users from the system");
        }
    }

    public void removeUser(Long id, OidcUser principal) {
        modifyUser(id, UserModification.REMOVE, principal);
    }

    public AppUser demoteUser(Long id, OidcUser principal) {
        return modifyUser(id, UserModification.DEMOTE, principal);
    }

    public AppUser promoteUser(Long id, OidcUser principal) {
        return modifyUser(id, UserModification.PROMOTE, principal);
    }
}
