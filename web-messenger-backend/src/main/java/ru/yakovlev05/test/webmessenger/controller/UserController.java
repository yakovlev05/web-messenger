package ru.yakovlev05.test.webmessenger.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.yakovlev05.test.webmessenger.dto.MessageDto;
import ru.yakovlev05.test.webmessenger.dto.user.UserDto;
import ru.yakovlev05.test.webmessenger.service.user.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public UserDto getUser(@PathVariable String username) {
        return userService.getUser(username);
    }

    /**
     * @param userDetails получаем информацию о пользователе, который прошл авторизацию
     *                    (наш фильтр). Ещё можно сделать так: Authentication jwt или получить
     *                    в коде SecurityContextHolder.getContext().getAuthentication();
     *                    + можно в аргументах метода указать Principal principal, это вернёт объект
     *                    UsernamePasswordAuthenticationToken
     */
    @GetMapping("/me")
    public UserDto getMe(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getUser(userDetails.getUsername());
    }

    @DeleteMapping("/me")
    public MessageDto deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteUser(userDetails.getUsername());
        return new MessageDto(String.format("User %s deleted", userDetails.getUsername()));
    }

    @PutMapping("/me")
    public UserDto updateUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UserDto userDto) {
        return userService.updateUser(userDto, userDetails.getUsername());
    }
}