package com.Satisfyre.app.service;


import com.Satisfyre.app.dto.LoginRequest;
import com.Satisfyre.app.dto.RegistrationRequest;
import com.Satisfyre.app.dto.Response;
import com.Satisfyre.app.dto.UserDTO;
import com.Satisfyre.app.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Response registerUser(RegistrationRequest registrationRequest);
    Response loginUser(LoginRequest loginRequest);
    Response getAllUsers();
    Response getOwnAccountDetails();
    UserEntity getCurrentLoggedInUser();
    Response updateOwnAccount(UserDTO userDTO);
    Response deleteOwnAccount();
    Response verifyToken(String token);
    List<UserEntity> getDirectDownlines(String referralCode);
    List<UserEntity> getAllDownlines(String referralCode);
    Response getMyBookingHistory();
    Response getUserById (Long id);
    public String getUplineNameByReferralCode(String referralCode);


}
