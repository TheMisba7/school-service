package com.mansar.schoolservice.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class SubjectNotFoundAdvice {



    @ResponseBody
    @ExceptionHandler(SubjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(SubjectNotFoundException ex) {
        return ex.getMessage();
    }
}
