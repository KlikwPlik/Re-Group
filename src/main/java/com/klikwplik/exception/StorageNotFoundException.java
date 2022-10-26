package com.klikwplik.exception;

public class StorageNotFoundException extends RuntimeException {

    public StorageNotFoundException(long gangId) {
        super("Could not find storage of gang id: " + gangId);
    }
}
