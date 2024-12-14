package godevenner.divexbackend.exception;

public record ErrorDto(
        int status,
        String errorMessage
) {
}
