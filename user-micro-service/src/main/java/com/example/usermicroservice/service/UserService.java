package com.example.usermicroservice.service;

import com.example.usermicroservice.dto.UserDto;
import com.example.usermicroservice.vo.ResponseUser;

public interface UserService {

    ResponseUser createUser(UserDto userDto);
}
