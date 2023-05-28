package me.alextodea.testioapplication.exception;

public class CannotModifyAdminException extends RuntimeException {
    public CannotModifyAdminException(String message) {
        super(message);
    }
}
