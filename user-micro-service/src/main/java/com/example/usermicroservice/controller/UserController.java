package com.example.usermicroservice.controller;

import com.example.usermicroservice.service.UserService;
import com.example.usermicroservice.vo.Greeting;
import com.example.usermicroservice.vo.RequestUser;
import com.example.usermicroservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-micro-service")
@RequiredArgsConstructor
public class UserController {

    private final Greeting greeting;
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        return new ResponseEntity<>(userService.createUser(user.toDto()), HttpStatus.CREATED);
    }

    @GetMapping("/health-check")
    public String status() {
        return "It's Working in User Service on Port " + greeting.getServerPort();
    }

    @GetMapping("/welcome")
    public String welcome(){
        return greeting.getMessage();
    }
}
