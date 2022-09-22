package com.example.usermicroservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    private String userId;

    private String email;

    private String name;

    private List<ResponseOrder> orders;

}
