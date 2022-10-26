package com.klikwplik.exception;

import com.klikwplik.entity.Coordinates;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Coordinates coordinates) {
        super("could not find resource at lon: " + coordinates.getLongitude() + ", lat: " + coordinates.getLatitude());
    }
}
