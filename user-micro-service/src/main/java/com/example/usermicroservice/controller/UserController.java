package com.example.usermicroservice.controller;

import com.example.usermicroservice.dto.UserDto;
import com.example.usermicroservice.entity.UserEntity;
import com.example.usermicroservice.service.UserService;
import com.example.usermicroservice.vo.Greeting;
import com.example.usermicroservice.vo.RequestUser;
import com.example.usermicroservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final Greeting greeting;
    private final UserService userService;
    private final Environment env;

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        return new ResponseEntity<>(userService.createUser(user.toDto()), HttpStatus.CREATED);
    }

    @GetMapping("/health-check")
    public String status() {
        return "It's Working in User Service " +
                ", port(local.server.port) =" + env.getProperty("local.server.port") +
                ", port(server.port) =" + env.getProperty("server.port") +
                ", token secret = " + env.getProperty("token.secret") +
                ", token expiration ime =" + env.getProperty("token.expirationTime");
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<UserEntity> userList = userService.getUserByAll();
        List<ResponseUser> result = new ArrayList<>();

        UserDto userDto = new UserDto();
        userList.forEach(v -> {
            result.add(userDto.toResponse(v, new ArrayList<>()));
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUsers(@PathVariable("userId") UUID userId) {
        return new ResponseEntity<>(userService.getUserByUserId(userId), HttpStatus.OK);
    }


}
