package com.libraryapp.entity.exceptions;

public class DuplicateUserException extends Exception{
    public DuplicateUserException(String message) {
        super(message);
    }
}
