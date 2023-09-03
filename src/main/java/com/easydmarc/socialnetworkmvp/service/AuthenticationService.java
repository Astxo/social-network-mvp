package com.easydmarc.socialnetworkmvp.service;

import com.easydmarc.socialnetworkmvp.dto.AuthDto;

public interface AuthenticationService {

    String login(AuthDto loginDto);

    void saveUserToken(Integer userId, String jwtToken);

}
