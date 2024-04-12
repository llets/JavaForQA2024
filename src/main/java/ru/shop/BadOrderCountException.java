package ru.shop;

public class BadOrderCountException extends Exception {
    public BadOrderCountException(String message) {
        super(message);
    }
}
