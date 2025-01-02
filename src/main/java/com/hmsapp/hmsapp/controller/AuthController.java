package com.hmsapp.hmsapp.controller;

import com.hmsapp.hmsapp.Entity.User;
import com.hmsapp.hmsapp.payload.JwtToken;
import com.hmsapp.hmsapp.payload.LoginDto;
import com.hmsapp.hmsapp.repository.UserRepository;
import com.hmsapp.hmsapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
 private UserRepository userRepository;
 private UserService userService;

    public AuthController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }
    //USED FOR USERS ONLY
    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(
            @RequestBody User user
    ){
        Optional<User> opUser = userRepository.findByUsername(user.getUsername());
        if(opUser.isPresent())
        {
            return new ResponseEntity("Username already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmail(user.getEmail());
        if(opEmail.isPresent())
        {
            return new ResponseEntity("Email already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opMobile = userRepository.findByMobile(user.getMobile());
        if(opMobile.isPresent())
        {
            return new ResponseEntity("Mobile already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10)));
        user.setRole("ROLE_USER");
        User saved = userRepository.save(user);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }
    @PostMapping("/property/sign-up")
    public ResponseEntity<?> createPropertyOwner(
            @RequestBody User user
    ){
        Optional<User> opUser = userRepository.findByUsername(user.getUsername());
        if(opUser.isPresent())
        {
            return new ResponseEntity("Username already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmail(user.getEmail());
        if(opEmail.isPresent())
        {
            return new ResponseEntity("Email already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opMobile = userRepository.findByMobile(user.getMobile());
        if(opMobile.isPresent())
        {
            return new ResponseEntity("Mobile already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10)));
        user.setRole("ROLE_PROPERTYOWNER");
        User saved = userRepository.save(user);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }
    @PostMapping("/blog/sign-up")
    public ResponseEntity<?> createblogaccount(
            @RequestBody User user
    ){
        Optional<User> opUser = userRepository.findByUsername(user.getUsername());
        if(opUser.isPresent())
        {
            return new ResponseEntity("Username already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmail(user.getEmail());
        if(opEmail.isPresent())
        {
            return new ResponseEntity("Email already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opMobile = userRepository.findByMobile(user.getMobile());
        if(opMobile.isPresent())
        {
            return new ResponseEntity("Mobile already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10)));
        user.setRole("ROLE_BLOGMANAGER");
        User saved = userRepository.save(user);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(
          @RequestBody LoginDto loginDto
    ){
        String token = userService.verifyLogin(loginDto);
        JwtToken jwttoken = new JwtToken();
        jwttoken.setToken(token);
        jwttoken.setType("JWT");
        if(jwttoken!= null){
            return new ResponseEntity<>(jwttoken,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("invalid", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
