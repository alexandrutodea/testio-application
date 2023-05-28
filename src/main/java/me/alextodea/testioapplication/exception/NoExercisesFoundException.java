package me.alextodea.testioapplication.exception;

public class NoExercisesFoundException extends RuntimeException {
    public NoExercisesFoundException(String message) {
        super(message);
    }
}
