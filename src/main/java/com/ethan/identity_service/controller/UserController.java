package com.ethan.identity_service.controller;

import com.ethan.identity_service.dto.request.ApiResponse;
import com.ethan.identity_service.dto.request.UserCreationRequest;
import com.ethan.identity_service.dto.response.UserResponse;
import com.ethan.identity_service.dto.request.UserUpdateRequest;
import com.ethan.identity_service.entity.UserEntity;
import com.ethan.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
   UserService userService;

    @PostMapping
    ApiResponse<UserEntity> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserEntity> response = new ApiResponse<>();
        response.setResult(userService.createUser(request));
        return response;
    };

    @GetMapping
    List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    UserResponse getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    UserResponse updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}
