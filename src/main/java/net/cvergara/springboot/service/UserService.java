package net.cvergara.springboot.service;

import net.cvergara.springboot.dto.UserDto;
import net.cvergara.springboot.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUserById(Long userId) ;
    List<UserDto> getAllUsers() ;
    UserDto updateUser(UserDto user) ;
    void deleteUser(Long userId) ;
}
