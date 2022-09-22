package com.example.usermicroservice.service;

import com.example.usermicroservice.dto.UserDto;
import com.example.usermicroservice.entity.UserEntity;
import com.example.usermicroservice.vo.ResponseUser;

import java.util.UUID;

public interface UserService {

    ResponseUser createUser(UserDto userDto);

    ResponseUser getUserByUserId(UUID userId);

    Iterable<UserEntity> getUserByAll();


}
