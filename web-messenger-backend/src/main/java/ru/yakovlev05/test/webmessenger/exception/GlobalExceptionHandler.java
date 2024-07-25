package ru.yakovlev05.test.webmessenger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.yakovlev05.test.webmessenger.dto.MessageDto;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<MessageDto> handleException(CustomException exception) {
        System.out.println("Обработка исключения: " + exception.getMessage());
        return new ResponseEntity<>(new MessageDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
