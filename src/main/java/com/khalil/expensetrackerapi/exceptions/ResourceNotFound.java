package com.khalil.expensetrackerapi.exceptions;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String message){
        super(message);
    }
}
