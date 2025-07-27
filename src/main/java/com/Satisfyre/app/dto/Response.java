package com.Satisfyre.app.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.Satisfyre.app.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    //generic
    private int status;
    private String message;

    //for login
    private String token;
    private UserRole role;
    private Boolean active;
    private String expirationTime;
    private String referredBy;

    //user data
    private UserDTO user;
    private List<UserDTO> users;

    //downlines
    private String referralCode;
    private List<UserDTO> downlines;



    private final LocalDateTime timestamp = LocalDateTime.now();


}
