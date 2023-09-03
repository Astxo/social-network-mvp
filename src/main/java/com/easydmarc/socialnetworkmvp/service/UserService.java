package com.easydmarc.socialnetworkmvp.service;

import com.easydmarc.socialnetworkmvp.dto.CriteriaDto;
import com.easydmarc.socialnetworkmvp.dto.UserCreationDto;
import com.easydmarc.socialnetworkmvp.model.User;

import java.util.List;

public interface UserService {

    void register(UserCreationDto userCreationDto);

    void update(User user);

    User getUser(Integer id);

    List<User> loadAllByCriteria(CriteriaDto criteriaDto);

}
