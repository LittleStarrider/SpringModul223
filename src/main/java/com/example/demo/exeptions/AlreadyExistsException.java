package com.example.demo.exeptions;

public class AlreadyExistsException extends IllegalArgumentException{

    public AlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }

}
