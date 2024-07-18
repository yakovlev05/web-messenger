package ru.yakovlev05.test.webmessenger.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yakovlev05.test.webmessenger.model.MessageModel;
import ru.yakovlev05.test.webmessenger.model.user.UserModel;
import ru.yakovlev05.test.webmessenger.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        var user = userService.getUser(username);
        if (user == null) return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new MessageModel("User not found for username: " + username));

        var response = new UserModel(
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getEmail()
        );
        return ResponseEntity.ok(response);
    }

//    @DeleteMapping("/{username}")
//    public ResponseEntity<String> deleteUser(@PathVariable String username) {
//
//    }
//
//    @PutMapping("/me")
//    public ResponseEntity<MessageModel> updateUser(@RequestBody UserModel userModel) {
//
//    }
}