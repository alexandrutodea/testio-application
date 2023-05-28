package me.alextodea.testioapplication.exception;

public class InstructorRequestAlreadyApprovedException extends RuntimeException {
    public InstructorRequestAlreadyApprovedException(String message) {
        super(message);
    }
}
