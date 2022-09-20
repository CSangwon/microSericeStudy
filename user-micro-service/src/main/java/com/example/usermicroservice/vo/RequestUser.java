package com.example.usermicroservice.vo;

import com.example.usermicroservice.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RequestUser {

    @NotNull(message = "Email cannot be Null")
    @Size(min=2, message = "Email not be less than two char")
    @Email
    private String email;

    @NotNull(message = "Name cannot be Null")
    @Size(min=2, message = "Name not be less than two char")
    private String name;

    @NotNull(message = "Passwd cannot be Null")
    @Size(min=8, message = "Passwd not be less than 8 char")
    private String passwd;

    public UserDto toDto(){
        return UserDto.builder()
                .email(email)
                .name(name)
                .passwd(passwd)
                .build();
    }


}
