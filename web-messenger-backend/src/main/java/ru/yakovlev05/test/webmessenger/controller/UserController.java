package ru.yakovlev05.test.webmessenger.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.yakovlev05.test.webmessenger.dto.MessageDto;
import ru.yakovlev05.test.webmessenger.dto.user.UserDto;
import ru.yakovlev05.test.webmessenger.service.user.UserService;

@Tag(name = "User", description = "Изменение, получение информации о пользователе")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Получить информацию о пользователе по username")
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
    @Operation(summary = "Получить информацию об аккаунте, которому принадлежит access token")
    @GetMapping("/me")
    public UserDto getMe(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getUser(userDetails.getUsername());
    }

    @Operation(summary = "Удалить пользователя")
    @DeleteMapping("/me")
    public MessageDto deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteUser(userDetails.getUsername());
        return new MessageDto(String.format("User %s deleted", userDetails.getUsername()));
    }

    @Operation(summary = "Обновить данные пользователя")
    @PutMapping("/me")
    public UserDto updateUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UserDto userDto) {
        return userService.updateUser(userDto, userDetails.getUsername());
    }
}