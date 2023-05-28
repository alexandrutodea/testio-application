package me.alextodea.testioapplication.utils;

import me.alextodea.testioapplication.model.AppUser;
import me.alextodea.testioapplication.repository.AppUserRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Optional;

public class Utils {

    public static Optional<AppUser> getAppUserOptionalBySub(OidcUser principal, AppUserRepository appUserRepository) {
        String sub = principal.getAttribute("sub");
        return appUserRepository.findByAuthProviderId(sub);
    }

}
