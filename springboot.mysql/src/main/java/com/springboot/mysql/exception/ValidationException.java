package com.springboot.mysql.exception;

public class ValidationException extends Exception
{
    public ValidationException(String message)
    {
        super(message);
    }
}
