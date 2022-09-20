package com.example.usermicroservice.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseUser {
    private String userId;

    private String email;

    private String name;

}
