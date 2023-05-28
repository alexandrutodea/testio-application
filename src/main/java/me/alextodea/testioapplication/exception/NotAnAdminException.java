package me.alextodea.testioapplication.exception;

public class NotAnAdminException extends RuntimeException {
    public NotAnAdminException(String message) {
        super(message);
    }
}
