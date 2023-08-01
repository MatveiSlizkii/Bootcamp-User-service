package by.ITacademy.core.controllers.advice;


import by.ITacademy.core.services.exception.ValidationError;

import java.util.List;

public class MultipleResponseError {
    private String logref;
    private List<ValidationError> errors;

    public MultipleResponseError(String logref, List<ValidationError> errors) {
        this.logref = logref;
        this.errors = errors;
    }

    public String getLogref() {
        return logref;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}
