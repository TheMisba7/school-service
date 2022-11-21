package com.mansar.schoolservice.Exceptions;

import java.util.function.Supplier;

public class NoUserFoundException extends RuntimeException {
    public NoUserFoundException(Long id){
        super("Could not find user with id : "+id);
    }
}
