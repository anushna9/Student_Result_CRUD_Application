package com.example.student.NoIdException;

@RestControllerAdvice
public class StudentExceptionHandler {
    @ExceptionHandler(NoStudentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNoStudentException(NoStudentException e) {
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), e.getMessage());
    }
}
