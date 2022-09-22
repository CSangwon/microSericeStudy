package com.example.usermicroservice.dto;

import com.example.usermicroservice.entity.UserEntity;
import com.example.usermicroservice.vo.ResponseOrder;
import com.example.usermicroservice.vo.ResponseUser;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public ResponseUser toResponse(UserEntity userEntity) {
        return ResponseUser.builder()
                .userId(String.valueOf(userEntity.getUserId()))
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .build();
    }

    public ResponseUser toResponse(UserEntity userEntity, List<ResponseOrder> orders){
        return ResponseUser.builder()
                .userId(String.valueOf(userEntity.getUserId()))
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .orders(orders)
                .build();
    }



}
