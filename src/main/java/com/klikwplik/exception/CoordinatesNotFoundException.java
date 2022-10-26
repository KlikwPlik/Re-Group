package com.klikwplik.exception;

public class CoordinatesNotFoundException extends RuntimeException {

    public CoordinatesNotFoundException(long id) {
        super("Could not find coordinates for id: " + id);
    }
}
