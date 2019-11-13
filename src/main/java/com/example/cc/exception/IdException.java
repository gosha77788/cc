package com.example.cc.exception;

public class IdException extends Exception {

    private Long number;

    public Long getNumber() {
        return number;
    }

    public IdException(String message, Long num) {
        super(message);
        number = num;
    }
}
