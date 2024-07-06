package com.example.student.NoIdException;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
@Setter
public class NoStudentException extends RuntimeException {
    private String message;

    public NoStudentException() {}

    public NoStudentException(String message) {
        super(message);
        this.message = message;
    }
}
