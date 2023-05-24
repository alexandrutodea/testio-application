package me.alextodea.testioapplication.exception;

public class UserInstructorOrHigherException extends RuntimeException {
    public UserInstructorOrHigherException(String message) {
        super(message);
    }
}
