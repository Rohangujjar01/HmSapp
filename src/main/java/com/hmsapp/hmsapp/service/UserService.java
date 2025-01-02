package com.hmsapp.hmsapp.service;

import com.hmsapp.hmsapp.Entity.User;
import com.hmsapp.hmsapp.payload.LoginDto;
import com.hmsapp.hmsapp.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {
    private UserRepository userRepository;
    private JWTService jwtService;

    public UserService(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public String verifyLogin(
            LoginDto loginDto
    ) {
        Optional<User> opUser = userRepository.findByUsername(loginDto.getUserName());
        if (opUser.isPresent()) {
            User user = opUser.get();
            if (BCrypt.checkpw(loginDto.getPassword(), user.getPassword())) {
                String token = jwtService.generateToken(user.getUsername());
                return token;
            } else {
                return "username is wrong";
            }
        } return null;
    }
}
