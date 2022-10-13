package com.klikwplik.exception;

public class MemberAlreadyExists extends RuntimeException {

    public MemberAlreadyExists(String firstName, String lastName) {
        super(String.format("Member %s %s already exists", firstName, lastName));
    }
}
