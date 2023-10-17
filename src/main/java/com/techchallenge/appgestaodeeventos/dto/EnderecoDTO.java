package com.techchallenge.appgestaodeeventos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(

        String logradouro,

        String bairro,

        String cep,

        String cidade,

        String uf,
        String complemento,
        String numero) {

}
