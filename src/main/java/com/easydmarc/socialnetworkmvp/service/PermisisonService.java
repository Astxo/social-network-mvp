package com.easydmarc.socialnetworkmvp.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class PermisisonService {

    public boolean canPost(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken, Integer ownerId, Integer friendId) {
        return true;
    }

}
