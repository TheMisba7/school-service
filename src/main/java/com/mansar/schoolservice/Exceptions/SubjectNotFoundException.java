package com.mansar.schoolservice.Exceptions;

public class SubjectNotFoundException extends RuntimeException{

    public SubjectNotFoundException(Long id)
    {
        super("Could Not find subject : "+id);
    }
}
