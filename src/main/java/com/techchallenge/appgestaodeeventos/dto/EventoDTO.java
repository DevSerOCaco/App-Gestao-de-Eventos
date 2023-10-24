package com.techchallenge.appgestaodeeventos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record EventoDTO(
        @NotBlank(message = "Insira uma descrição")
        String descricao,
        @NotEmpty(message = "Campo de Lotação máxima é obrigatório")
        Integer lotacao,
        @NotEmpty(message = "Campo Data de Inicio é obrigatório")
        LocalDateTime dataInicio,
        LocalDateTime dataTermino,
        Boolean eventoAberto,

        EnderecoDTO enderecoDTO
) {
}
