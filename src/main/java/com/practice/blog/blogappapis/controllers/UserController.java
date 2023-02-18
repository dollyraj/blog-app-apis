package com.practice.blog.blogappapis.controllers;

import com.practice.blog.blogappapis.payloads.ApiResponse;
import com.practice.blog.blogappapis.payloads.UserDTO;
import com.practice.blog.blogappapis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //POST - create user
   @PostMapping("/")
    private ResponseEntity<UserDTO> createUser(@RequestBody @Validated UserDTO userDTO){
       return new ResponseEntity<>(this.userService.createUser(userDTO), HttpStatus.CREATED);
    }

    //GET - get allUsers
    @GetMapping("/")
    private ResponseEntity<List<UserDTO>> getAllUsers(){
       return ResponseEntity.ok(this.userService.getAllUsers());
        //return this.userService.getAllUsers();
    }

    //GET - get user
    @GetMapping("/{userId}")
    private ResponseEntity<UserDTO> getUserById(@PathVariable("userId") int userId){
       return ResponseEntity.ok(this.userService.getUserById(userId));
       //return this.userService.getUserById(userId);
    }


    //PUT - update user
    @PutMapping("/{userId}")
    private ResponseEntity<UserDTO> updateUser(@RequestBody @Validated UserDTO userDTO,@PathVariable("userId") int userId){
       return ResponseEntity.ok(this.userService.updateUser(userDTO,userId));
       //return this.userService.updateUser(userDTO,userId);
    }

    //DELETE - delete user
    @DeleteMapping("/{userId}")
    private ResponseEntity<ApiResponse> deleteUserById(@PathVariable("userId") int userId){
       this.userService.deleteUserById(userId);

       //return new ResponseEntity<>(Map.of("message","user deleted successfully"),HttpStatus.OK);

        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
    }


}
