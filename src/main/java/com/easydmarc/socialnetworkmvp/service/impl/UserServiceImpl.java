package com.easydmarc.socialnetworkmvp.service.impl;

import com.easydmarc.socialnetworkmvp.dto.CriteriaDto;
import com.easydmarc.socialnetworkmvp.dto.UserCreationDto;
import com.easydmarc.socialnetworkmvp.model.User;
import com.easydmarc.socialnetworkmvp.repository.UserRepository;
import com.easydmarc.socialnetworkmvp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> loadAllByCriteria(CriteriaDto criteriaDto) {
        return userRepository.findAllUsers(criteriaDto);
    }

    @Override
    public void register(UserCreationDto userCreationDto) {

        User user = new User();
        user.setFirstName(userCreationDto.firstName());
        user.setLastName(userCreationDto.lastName());
        user.setEmail(userCreationDto.email());
        user.setPassword(passwordEncoder.encode(userCreationDto.password()));

        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}
