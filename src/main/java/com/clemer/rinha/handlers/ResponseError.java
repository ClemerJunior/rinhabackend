package com.clemer.rinha.handlers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseError {
    private int code;
    private String message;
}
