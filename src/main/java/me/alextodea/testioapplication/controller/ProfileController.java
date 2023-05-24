package me.alextodea.testioapplication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for requests to the {@code /profile} resource. Populates the model with the claims from the
 * {@linkplain OidcUser} for use by the view.
 */
@RestController
public class ProfileController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/profile")
    public void profile(Model model, @AuthenticationPrincipal OidcUser oidcUser) {
        String sub = oidcUser.getAttribute("sub");
        System.out.println(sub);
        model.addAttribute("sub", sub);
    }

}
