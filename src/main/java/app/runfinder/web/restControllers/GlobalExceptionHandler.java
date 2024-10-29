package app.runfinder.web.restControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice // Handles exceptions in the entire application
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class) // Handles all ResponseStatusExceptions
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException exception) {
        return new ResponseEntity<>(exception.getReason(), exception.getStatusCode());
    }
}