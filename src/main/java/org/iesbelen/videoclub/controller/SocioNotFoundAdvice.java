package org.iesbelen.videoclub.controller;

import org.iesbelen.videoclub.exception.SocioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SocioNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(SocioNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String socioNotFoundHandler(SocioNotFoundException socioNotFoundException) {
        return socioNotFoundException.getMessage();
    }
}
