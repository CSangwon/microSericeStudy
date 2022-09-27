package com.example.usermicroservice.service;

import com.example.usermicroservice.dto.UserDto;
import com.example.usermicroservice.entity.UserEntity;
import com.example.usermicroservice.repository.UserRepository;
import com.example.usermicroservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseUser createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        UserEntity userEntity = userDto.toEntity();
        userEntity.setEncryptedPasswd(passwordEncoder.encode(userDto.getPasswd()));

        UserEntity createdUser = userRepository.save(userEntity);
        return userDto.toResponse(createdUser);
    }

    @Override
    public ResponseUser getUserByUserId(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("User not Found")
        );
        UserDto userDto = new UserDto();
        return userDto.toResponse(userEntity, new ArrayList<>());
    }

    @Override
    public Iterable<UserEntity> getUserByAll() {
        return userRepository.findAll();
    }

//    @Override
//    public UserDto getUserDetailsByEmail(String email) {
//        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(
//                () -> new UsernameNotFoundException(email));
//        log.info("12321");
//        log.info(userEntity.getEmail());
//        return UserDto.builder().
//                userId(String.valueOf(userEntity.getUserId()))
//                .email(userEntity.getEmail())
//                .name(userEntity.getName())
//                .encryptedPasswd(userEntity.getEncryptedPasswd())
//                .build();
//    }
}
