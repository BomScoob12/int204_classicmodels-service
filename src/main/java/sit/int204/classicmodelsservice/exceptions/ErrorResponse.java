package sit.int204.classicmodelsservice.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final String title;
    private final int status;
    private final String message;
    private final String instance;
    private String stackTrace;
    private List<ValidationError> errors;
    private final LocalDateTime timeStamp = LocalDateTime.now();

    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class ValidationError {
        private final String field;
        private final String message;
    }

    public void addValidationError(String field, String message) {
        if (Objects.isNull(errors)) errors = new ArrayList<>();
        errors.add(new ValidationError(field, message));
    }
}
