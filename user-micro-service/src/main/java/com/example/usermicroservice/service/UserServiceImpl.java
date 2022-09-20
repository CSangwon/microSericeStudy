package com.example.usermicroservice.service;

import com.example.usermicroservice.dto.UserDto;
import com.example.usermicroservice.entity.UserEntity;
import com.example.usermicroservice.repository.UserRepository;
import com.example.usermicroservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public ResponseUser createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        UserEntity userEntity = userDto.toEntity();
        userEntity.setEncryptedPasswd("encryptedPasswd");

        UserEntity createdUser = userRepository.save(userEntity);
        return userDto.toResponse(createdUser);
    }
}
