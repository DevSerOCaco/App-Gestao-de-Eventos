package com.techchallenge.appgestaodeeventos.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record EventoDTO(
        @NotBlank(message = "Insira uma descrição")
        String descricao,
        @NotNull(message = "Campo de Lotação máxima é obrigatório")
        Integer lotacao,
        @NotNull(message = "Campo Data de Inicio é obrigatório")
        @Future // garante que a data inicio seja futura
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime dataInicio,
        @NotNull(message = "Campo Data de Término é obrigatório")
        @Future
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime dataTermino,
        Boolean eventoAberto,

        EnderecoDTO enderecoDTO
) {
        @AssertTrue(message = "A data de término não pode ser menor que a data de início")
        private boolean isDataTerminoValid() {
                if (dataInicio == null || dataTermino == null) {
                        return true;
                }
                return !dataTermino.isBefore(dataInicio);
        }
}
