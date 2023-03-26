package by.ITacademy.bootcamp.controllers.advice;

import by.ITacademy.bootcamp.services.exception.ValidationException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> validationHandler(ValidationException e) {
        if (e.getErrors().isEmpty()) {
            return new ResponseEntity<>(new by.ITacademy.bootcamp.controllers.advice.SingleResponseError("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new by.ITacademy.bootcamp.controllers.advice.MultipleResponseError("structured_error", e.getErrors()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<?> invalidFormatHandler(InvalidFormatException e) {
        return new ResponseEntity<>(new by.ITacademy.bootcamp.controllers.advice.SingleResponseError("error",
                "Запрос содержит некорретные данные. Измените запрос и отправьте его ещё раз"),
                HttpStatus.BAD_REQUEST);
    }
}
