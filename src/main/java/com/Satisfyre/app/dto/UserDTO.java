package com.Satisfyre.app.dto;


import com.Satisfyre.app.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.Satisfyre.app.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;

    private String email;

    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;

    private String phoneNumber;

    private UserRole role;

    private String referredBy;

    private boolean active;

    //user data
    private UserDTO upline;
    private List<UserDTO> users;

    //downlines
    private String referralCode;
    private List<UserDTO> downlines;


    private LocalDate createdAt;

    public static UserDTO fromEntity(UserEntity user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .active(user.isActive())
                .referralCode(user.getReferralCode())
                .referredBy(user.getReferredBy())
                .build();
    }

}


