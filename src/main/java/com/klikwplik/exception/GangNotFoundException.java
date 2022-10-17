package com.klikwplik.exception;

public class GangNotFoundException extends RuntimeException {
    public GangNotFoundException(Long id) {
        super("Could not find gang " + id);
    }

}
