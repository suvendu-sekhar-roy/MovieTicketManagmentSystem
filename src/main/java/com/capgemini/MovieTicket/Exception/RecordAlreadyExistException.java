package com.capgemini.MovieTicket.Exception;

public class RecordAlreadyExistException extends RuntimeException {
    public RecordAlreadyExistException(String s) {
        super(s);
    }
}
