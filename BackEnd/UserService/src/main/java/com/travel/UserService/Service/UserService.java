package com.travel.UserService.Service;

import com.travel.UserService.DTO.LoginRequest;
import com.travel.UserService.DTO.UserDTO;
import com.travel.UserService.Entity.User;

public interface UserService {
    User registerUser(UserDTO userDTO);


    User login(LoginRequest loginRequest);
}
