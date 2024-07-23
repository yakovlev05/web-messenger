package ru.yakovlev05.test.webmessenger.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yakovlev05.test.webmessenger.dto.MessageDto;
import ru.yakovlev05.test.webmessenger.dto.user.UserDto;
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
                .body(new MessageDto("User not found for username: " + username));

        var response = new UserDto(
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getEmail()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok("User " + username + " has been deleted");
    }
}