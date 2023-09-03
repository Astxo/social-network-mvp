package com.easydmarc.socialnetworkmvp.repository;

import com.easydmarc.socialnetworkmvp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {

    Optional<User> findByEmail(String email);

}
