package com.hmsapp.hmsapp.repository;

import com.hmsapp.hmsapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername (String username);
    Optional<User> findByEmail (String email);
    Optional<User> findByMobile (String mobile);

}