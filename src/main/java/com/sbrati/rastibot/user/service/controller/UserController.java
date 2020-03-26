package com.sbrati.rastibot.user.service.controller;

import com.sbrati.rastibot.user.service.exception.ResourceAlreadyExistsException;
import com.sbrati.rastibot.user.service.model.User;
import com.sbrati.rastibot.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/chat/{chatId}")
    public User findById(@PathVariable("chatId") Long chatId) {
        return userService.findByChatId(chatId);
    }

    @PutMapping(value = "/chat/{chatId}")
    public void createOrUpdate(@RequestBody User user) {
        userService.createOrUpdate(user);
    }

    @GetMapping(value = "/count")
    public Long countAllUsers() {
        return userService.count();
    }

    @ExceptionHandler(value = ResourceAlreadyExistsException.class)
    public ResponseEntity<?> handleResourceAlreadyExistsException() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
