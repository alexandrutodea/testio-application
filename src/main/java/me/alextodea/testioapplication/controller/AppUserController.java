package me.alextodea.testioapplication.controller;

import me.alextodea.testioapplication.model.AppUser;
import me.alextodea.testioapplication.service.AppUserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppUserController {

    private AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/api/account")
    public AppUser accountInformation(@AuthenticationPrincipal OidcUser principal) {
        return appUserService.accountInformation(principal);
    }

    @PutMapping("/api/account/reset")
    public AppUser resetProgress(@AuthenticationPrincipal OidcUser principal) {
        return appUserService.resetProgress(principal);
    }

    @GetMapping("/api/users")
    public List<AppUser> getAppUsers(@AuthenticationPrincipal OidcUser principal) {
        return appUserService.getAppUsers(principal);
    }

    @DeleteMapping("/api/users/{id}/remove")
    public void removeAppUser(@PathVariable Long id, @AuthenticationPrincipal OidcUser oidcUser) {
        appUserService.removeUser(id, oidcUser);
    }

    @PutMapping("/api/users/{id}/demote")
    public AppUser demoteAppUser(@PathVariable Long id, @AuthenticationPrincipal OidcUser oidcUser) {
        return appUserService.demoteUser(id, oidcUser);
    }

    @PutMapping("/api/users/{id}/promote")
    public AppUser promoteAppUser(@PathVariable Long id, @AuthenticationPrincipal OidcUser oidcUser) {
        return appUserService.promoteUser(id, oidcUser);
    }
}
