package com.techchallenge.appgestaodeeventos.dto;

import java.time.LocalDateTime;

public record EventoDTO(
        String descricao,
        Integer lotacao,
        LocalDateTime dataInicio,
        LocalDateTime dataTermino,
        Boolean eventoAberto,

        EnderecoDTO enderecoDTO
) {
}
