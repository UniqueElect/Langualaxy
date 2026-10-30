package com.langualaxy.Exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
    public NotFoundException(String entity, long id){
        super(entity + " with id " + id + " not found.");
    }

}
