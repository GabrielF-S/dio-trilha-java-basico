package com.desafio_projeto.board.model.exceptions;

public class CollumnLimitException extends RuntimeException {
    public CollumnLimitException(String message) {
        super(message);
    }
}
