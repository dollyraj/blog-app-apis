package com.practice.blog.blogappapis.services;

import com.practice.blog.blogappapis.entities.User;
import com.practice.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.practice.blog.blogappapis.payloads.UserDTO;
import com.practice.blog.blogappapis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO){
        User user=this.DTOtoUser(userDTO);
        User savedUser=this.userRepository.save(user);
        return this.userToDTO(savedUser);
    }

    public List<UserDTO> getAllUsers(){
        List<User> userList= (List<User>) this.userRepository.findAll();
        List<UserDTO> userDTOList=new ArrayList<>();

        for(User user:userList){
            userDTOList.add(this.userToDTO(user));
        }

        return userDTOList;
    }

    public UserDTO getUserById(int userId){

        if(!this.userRepository.findById(userId).isPresent()){
            throw new ResourceNotFoundException("User","userId",userId);
        }

        User user=this.userRepository.findById(userId).get();

        return this.userToDTO(user);
    }

    public UserDTO updateUser(UserDTO userDTO,int userId){
        if(!this.userRepository.findById(userId).isPresent()){
            throw new ResourceNotFoundException("User","userId",userId);
        }

        User user=this.userRepository.findById(userId).get();

        user.setName(userDTO.getName());
        user.setEmailId(userDTO.getEmailId());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
       User updatedUser=this.userRepository.save(user);

        return this.userToDTO(updatedUser);
    }

    public void deleteUserById(int userId){
        if(!this.userRepository.findById(userId).isPresent()){
            throw new ResourceNotFoundException("User","userId",userId);
        }

        this.userRepository.deleteById(userId);
    }




    private UserDTO userToDTO(User user){
        UserDTO userDTO=new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmailId(user.getEmailId());
        userDTO.setPassword(user.getPassword());
        userDTO.setAbout(user.getAbout());
        return userDTO;
    }

    private User DTOtoUser(UserDTO userDTO){
        User user=new User();

        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmailId(userDTO.getEmailId());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
        return user;
    }


}
