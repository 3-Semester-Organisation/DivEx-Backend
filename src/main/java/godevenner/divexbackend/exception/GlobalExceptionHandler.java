package godevenner.divexbackend.exception;

import jakarta.persistence.ElementCollection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidTickerException.class)
    public ResponseEntity<ErrorDto> handleInvalidTickerException(InvalidTickerException exception) {
        ErrorDto error = new ErrorDto(404, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> handleBadCredentialsException(BadCredentialsException exception) {
        ErrorDto error = new ErrorDto((HttpStatus.UNAUTHORIZED.value()), exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDto> handleAuthenticationException(AuthenticationException exception) {
        ErrorDto error = new ErrorDto((HttpStatus.UNAUTHORIZED.value()), exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    //handle if username is already taken
    @ExceptionHandler(UsernameTakenException.class)
    public ResponseEntity<ErrorDto> handleUsernameAlreadyTakenException(UsernameTakenException exception) {
        ErrorDto error = new ErrorDto((HttpStatus.BAD_REQUEST.value()), exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(EmailTakenException.class)
    public ResponseEntity<ErrorDto> handleEmailAlreadyTakenException(EmailTakenException exception) {
        ErrorDto error = new ErrorDto((HttpStatus.BAD_REQUEST.value()), exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
