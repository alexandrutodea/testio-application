package me.alextodea.testioapplication.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { UserInstructorOrHigherException.class,
            InstructorRequestAlreadyApprovedException.class})
    protected ResponseEntity<Object> handleConflict (
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request); //409
    }

    @ExceptionHandler(value
            = { NotAnAdminException.class,
            NotAnInstructorException.class,
            CannotModifyAdminException.class,
            CannotModifyAdminException.class})
    protected ResponseEntity<Object> handleUnauthorized (
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.FORBIDDEN, request); //403
    }

    @ExceptionHandler(value
            = { ConstraintViolationException.class})
    protected ResponseEntity<Object> handleBadRequest(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request); //400
    }

    @ExceptionHandler(value
            = { InstructorRequestNotFoundException.class, UserNotFoundException.class, ExerciseNotFoundException.class, NoExercisesFoundException.class})
    protected ResponseEntity<Object> handleNotFound (
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request); //404
    }
}
