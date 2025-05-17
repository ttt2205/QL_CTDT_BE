package com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.UserService;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.UserReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.UserResDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<UserResDto> createUser(@RequestBody UserReqDto userReqDto) {
        UserResDto createdUser = userService.createUser(userReqDto);
        return ResponseEntity.ok(createdUser);
    }
    @GetMapping
    public List<UserResDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResDto> getUserById(@PathVariable Long id) {
        UserResDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResDto> updateUser(@PathVariable Long id, @RequestBody UserReqDto userReqDto) {
        UserResDto updatedUser = userService.updateUser(id, userReqDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
