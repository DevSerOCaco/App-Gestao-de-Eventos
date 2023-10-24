package com.techchallenge.appgestaodeeventos.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_evento")
@Data
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private Integer lotacao;

    private LocalDateTime dataInicio;

    private LocalDateTime dataTermino;

    private Boolean eventoAberto;

    @Embedded
    private Endereco endereco;

    public Evento(String descricao, Integer lotacao, LocalDateTime dataInicio, LocalDateTime dataTermino, Boolean eventoAberto , Endereco endereco) {
        this.id = id;
        this.descricao = descricao;
        this.lotacao = lotacao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.eventoAberto = eventoAberto;
        this.endereco = endereco;
    }

}
