package com.techchallenge.appgestaodeeventos.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private Integer lotacao;

    private LocalDateTime dataInicio;

    private LocalDateTime dataTermino;

    private Boolean eventoAberto;

    public Evento() {
    }

    public Evento(String descricao, Integer lotacao,
                  LocalDateTime dataInicio, LocalDateTime dataTermino,
                  Boolean eventoAberto) {
        this.id = id;
        this.descricao = descricao;
        this.lotacao = lotacao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.eventoAberto = eventoAberto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getLotacao() {
        return lotacao;
    }

    public void setLotacao(Integer lotacao) {
        this.lotacao = lotacao;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDateTime dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Boolean getEventoAberto() {
        return eventoAberto;
    }

    public void setEventoAberto(Boolean eventoAberto) {
        this.eventoAberto = eventoAberto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(id, evento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", lotacao=" + lotacao +
                ", dataInicio=" + dataInicio +
                ", dataTermino=" + dataTermino +
                ", eventoAberto=" + eventoAberto +
                '}';
    }
}
