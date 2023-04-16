package net.cvergara.springboot.controller;

import lombok.AllArgsConstructor;
import net.cvergara.springboot.dto.UserDto;
import net.cvergara.springboot.entity.User;
import net.cvergara.springboot.exception.ErrorDetails;
import net.cvergara.springboot.exception.ResourceNotFoundException;
import net.cvergara.springboot.repository.UserRepository;
import net.cvergara.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private UserService userService ;
    private final UserRepository userRepository;

    //build create User REST API
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid  UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser , HttpStatus.CREATED) ;
    }

    //http:localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userService.getUserById(userId) ;
        return new ResponseEntity<>(user , HttpStatus.OK ) ;
    }

    //get all users http://localhost:8080/api/users
    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers() ;
        return new ResponseEntity<>(users , HttpStatus.OK) ;
    }

    //build updateUser Rest Api
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId , @RequestBody @Valid UserDto user){
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user) ;
        return new ResponseEntity<>(updatedUser , HttpStatus.OK) ;
    }

    //build delete user RestAPI

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted" , HttpStatus.OK) ;
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handlerResourceNotFoundException(ResourceNotFoundException exception,
//                                                                            WebRequest webRequest){
//
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now() ,
//                exception.getMessage() ,
//                webRequest.getDescription(false) ,
//                "USER_NOT_FOUND"
//        ) ;
//
//        return new ResponseEntity<>(errorDetails , HttpStatus.NOT_FOUND) ;
//
//    }
}
