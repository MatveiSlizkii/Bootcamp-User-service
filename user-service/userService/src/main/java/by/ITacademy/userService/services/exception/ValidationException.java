package by.ITacademy.bootcamp.services.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends IllegalArgumentException {

    private List<by.ITacademy.bootcamp.services.exception.ValidationError> errors = new ArrayList<>();

    public ValidationException(String s, List<by.ITacademy.bootcamp.services.exception.ValidationError> errors) {
        super(s);
        this.errors = errors;
    }

    public ValidationException() {
    }

    public ValidationException(String s) {
        super(s);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public int getCountErrors() {
        return errors.size();
    }

    public List<by.ITacademy.bootcamp.services.exception.ValidationError> getErrors() {
        return errors;
    }
}
