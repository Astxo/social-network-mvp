package com.easydmarc.socialnetworkmvp.helper;

import com.easydmarc.socialnetworkmvp.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserProvider {

    public Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return ((User) authentication.getPrincipal()).getId();
        } else {
            return null;
        }
    }
}
