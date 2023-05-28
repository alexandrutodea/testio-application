package me.alextodea.testioapplication.exception;

public class InstructorRequestNotFoundException extends RuntimeException {
    public InstructorRequestNotFoundException(String message) {
        super(message);
    }
}
