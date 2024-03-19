package sit.int204.classicmodelsservice.exceptions;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.server.ResponseStatusException;
import sit.int204.classicmodelsservice.controller.CustomerController;
import sit.int204.classicmodelsservice.controller.ProductController;

import java.util.List;

@RestControllerAdvice(assignableTypes = {CustomerController.class, ProductController.class})
public class GlobalExceptionHandler {
    public static final String TRACE = "trace";

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleItemNotFoundException(
            ItemNotFoundException exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleItemNotFoundException(
            ResponseStatusException exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(PropertyValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handlePropertyValueException(
            PropertyValueException exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleOtherUncaughtException(
            Exception exception, WebRequest request) {
        return buildErrorResponse(
                exception, "Unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR, request
        );
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            Exception exception, HttpStatus httpStatus, WebRequest request) {
        return buildErrorResponse(exception, exception.getMessage(), httpStatus, request);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            Exception exception, String message, HttpStatus httpStatus, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(message, httpStatus.value(), exception.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

        @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            WebRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                "title template",
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error. Check 'errors' field for details.", request.getDescription(false)
        );
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(),
                    fieldError.getDefaultMessage());
        }
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorResponse> handleHandlerMethodValidationException(
            HandlerMethodValidationException exception, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse("Invalid parameter(s)", HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error. Check 'errors' field for details.", request.getDescription(false));
        List<ParameterValidationResult> paramNames = exception.getAllValidationResults();
        for (ParameterValidationResult param : paramNames) {
            errorResponse.addValidationError(
                    param.getMethodParameter().getParameterName(),
                    param.getResolvableErrors().get(0).getDefaultMessage() + " (" +
                            param.getArgument().toString() + ")");
        }
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }
}



