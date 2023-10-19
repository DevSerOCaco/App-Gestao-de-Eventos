package com.techchallenge.appgestaodeeventos.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "tb_usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private Date dataNascimento;
    private String login;
    private String senha;
    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public Usuario(Long idUsuario, String nome, String email, String cpf, Date dataNascimento, String login, String senha, Endereco endereco) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.login = login;
        this.senha = senha;
        this.endereco = endereco;
    }

    public Usuario() {
    }
}