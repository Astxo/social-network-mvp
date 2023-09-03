package com.easydmarc.socialnetworkmvp.repository;

import com.easydmarc.socialnetworkmvp.dto.CriteriaDto;
import com.easydmarc.socialnetworkmvp.model.User;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> findAllUsers(CriteriaDto criteriaDto);
}
