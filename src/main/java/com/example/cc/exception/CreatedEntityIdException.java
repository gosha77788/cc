package com.example.cc.exception;

public class CreatedEntityIdException extends Exception {

    private static String message = "Создаваемый объект не может иметь заполненное поле Id";

    public CreatedEntityIdException() {
        super(message);
    }
}
