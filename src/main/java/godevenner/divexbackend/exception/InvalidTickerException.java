package godevenner.divexbackend.exception;

public class InvalidTickerException extends RuntimeException {
    public InvalidTickerException(String message) {
        super(message);
    }
}
