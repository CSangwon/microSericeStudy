package com.example.usermicroservice.vo;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RequestLogin {

    @NotNull(message = "Email Can Not Be Null")
    @Size(min = 2, message = "Email not be less than Two Characters")
    @Email
    private String email;

    @NotNull(message = "Password Can Not Be Null")
    @Size(min = 8, message = "Password must be equals or greater than 8 Characters")
    private String passwd;
}
