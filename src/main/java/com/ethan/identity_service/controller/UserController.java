package com.ethan.identity_service.controller;

import com.ethan.identity_service.dto.request.ApiResponse;
import com.ethan.identity_service.dto.request.UserCreationRequest;
import com.ethan.identity_service.dto.request.UserUpdateRequest;
import com.ethan.identity_service.entity.UserEntity;
import com.ethan.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<UserEntity> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserEntity> response = new ApiResponse<>();
        response.setResult(userService.createUser(request));
        return response;
//        return userService.createUser(request);
    };

    @GetMapping
    List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    UserEntity getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    UserEntity updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }

}
