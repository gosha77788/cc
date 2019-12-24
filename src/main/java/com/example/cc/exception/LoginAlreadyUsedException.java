package com.example.cc.exception;

public class LoginAlreadyUsedException extends Exception {

    public LoginAlreadyUsedException(String msg) {
        super(msg);
    }

    public LoginAlreadyUsedException(String msg, Throwable t) {
        super(msg, t);
    }
}
