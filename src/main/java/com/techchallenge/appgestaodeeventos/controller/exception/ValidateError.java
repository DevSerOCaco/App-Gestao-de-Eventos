package com.techchallenge.appgestaodeeventos.controller.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidateError extends StandardError {

    private final List<ValidateMessage> messages = new ArrayList<>();

    public void addMessages(String campo, String mensagem) {
        messages.add(new ValidateMessage(campo, mensagem));
    }
}
