package com.techchallenge.appgestaodeeventos.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateMessage {

    private String campo;
    private String mensagem;

}
