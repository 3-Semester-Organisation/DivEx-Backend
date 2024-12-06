package godevenner.divexbackend.exception;

public class MaximumPortfolioEntriesReachException extends RuntimeException {
    public MaximumPortfolioEntriesReachException(String message) {
        super(message);
    }
}
