package com.sbrati.rastibot.user.service.controller;

import com.sbrati.rastibot.user.service.exception.ResourceAlreadyExistsException;
import com.sbrati.rastibot.user.service.model.User;
import com.sbrati.rastibot.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping(value = "/chat/{chatId}/awareness")
    public void setAwareness(@PathVariable("chatId") Long chatId, @RequestParam("awareness") Integer awareness) {
        userService.setAwareness(chatId, awareness);
    }

    @GetMapping(value = "/count")
    public Long countAllUsers() {
        return userService.count();
    }

    @GetMapping(value = "/uninformed")
    public List<Long> findUninformedUserIds(@RequestParam("awareness") Integer awareness) {
        return userService.findByAwarenessLessThan(awareness);
    }

    @ExceptionHandler(value = ResourceAlreadyExistsException.class)
    public ResponseEntity<?> handleResourceAlreadyExistsException() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
