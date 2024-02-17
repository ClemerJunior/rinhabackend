package com.clemer.rinha.handlers;

import com.clemer.rinha.exceptions.ClienteInexistenteException;
import com.clemer.rinha.exceptions.LimiteExcedidoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(ClienteInexistenteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleException(ClienteInexistenteException ex) {
        return new ResponseError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(LimiteExcedidoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseError handleException(LimiteExcedidoException ex) {
        return new ResponseError(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
    }
}
