package com.klikwplik.controller;

import com.klikwplik.exception.GangAlreadyExists;
import com.klikwplik.exception.GangNotFoundException;
import com.klikwplik.exception.MemberAlreadyExists;
import com.klikwplik.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandler {

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String memberNotFoundHandler(MemberNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(MemberAlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String memberAlreadyExistsHandler(MemberAlreadyExists ex) { return ex.getMessage();}

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(GangNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String gangNotFoundHandler(GangNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(GangAlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String gangAlreadyExistsHandler(GangAlreadyExists ex) { return ex.getMessage();}
}
