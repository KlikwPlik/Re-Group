package com.klikwplik.exception;

public class GangAlreadyExists extends Throwable {
    public GangAlreadyExists(String name) {
        super(String.format("Gang %s already exists", name));
    }
}
