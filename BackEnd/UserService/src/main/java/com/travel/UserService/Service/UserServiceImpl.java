package com.travel.UserService.Service;

import com.travel.UserService.DTO.LoginRequest;
import com.travel.UserService.DTO.UserDTO;
import com.travel.UserService.Entity.ROLE;
import com.travel.UserService.Entity.User;
import com.travel.UserService.Exception.EmailAlreadyInUseException;
import com.travel.UserService.Exception.MobileNoAlreadyInUseException;
import com.travel.UserService.Exception.PasswordAlreadyExistsException;
import com.travel.UserService.Exception.InvalidLoginException;
import com.travel.UserService.Repository.UserRepository;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private Validator validator;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(UserDTO userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new EmailAlreadyInUseException("Email is already in use");
        }

        if (userRepository.existsByMobileNo(userDTO.getMobileNo())) {
            throw new MobileNoAlreadyInUseException("Mobile number is already in use");
        }

        if (userRepository.existsByPassword(userDTO.getPassword())) {
            throw new PasswordAlreadyExistsException("Password is already in use");
        }

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setAge(userDTO.getAge());
        user.setMobileNo(userDTO.getMobileNo());

        user.setPassword(userDTO.getPassword());

        switch (userDTO.getRole().toUpperCase()) {
            case "ADMIN":
                user.setRole(ROLE.ADMIN);
                break;
            case "TOUR_GUIDE":
                user.setRole(ROLE.TOUR_GUIDE);
                break;
            default:
                user.setRole(ROLE.USER);
                break;
        }

        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User login(LoginRequest loginRequest) {
        User user = userRepository.findByFirstName(loginRequest.getFirstName());
        if (user == null || !loginRequest.getPassword().equals(user.getPassword())) {
            throw new InvalidLoginException("Invalid credentials");
        }
        return user;
    }
}
