package com.easydmarc.socialnetworkmvp.service.impl;

import com.easydmarc.socialnetworkmvp.dto.AuthDto;
import com.easydmarc.socialnetworkmvp.model.Token;
import com.easydmarc.socialnetworkmvp.repository.TokenRepository;
import com.easydmarc.socialnetworkmvp.repository.UserRepository;
import com.easydmarc.socialnetworkmvp.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserRepository userRepository,
                                     TokenRepository tokenRepository,
                                     JwtService jwtService,
                                     AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(AuthDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password())
        );

        var user = userRepository.findByEmail(loginDto.email()).orElseThrow();

        var token = jwtService.generateToken(user);
        saveUserToken(user.getId(), token);
        return token;
    }

    @Override
    public void saveUserToken(Integer userId, String jwtToken) {
        var token = new Token();
        token.setUserId(userId);
        token.setToken(jwtToken);
        tokenRepository.save(token);
    }

}
