package com.example.usermicroservice.dto;

import com.example.usermicroservice.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class UserDto {

    private String userId;

    private String email;

    private String name;

    private String passwd;

    private Date createdAt;

    private String encryptedPasswd;


    public UserEntity toEntity(){
        return UserEntity.builder()
                .email(email)
                .name(name)
                .encryptedPasswd(encryptedPasswd)
                .build();
    }



}
