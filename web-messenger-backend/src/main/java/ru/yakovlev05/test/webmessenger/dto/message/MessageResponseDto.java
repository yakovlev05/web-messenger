package ru.yakovlev05.test.webmessenger.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yakovlev05.test.webmessenger.dto.user.UserDto;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseDto {
    private Long id;
    private String message;
    private Date published;
    private UserDto sender;
}