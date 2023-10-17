package com.techchallenge.appgestaodeeventos.dto;

import jakarta.validation.Valid;

import java.util.Date;

public record UsuarioDTO (
        Long idUsuario,
        String nome,
        String email,
        String cpf,
        Date dataNascimento,
        String login,
        String senha,
        EnderecoDTO enderecoDTO
){ }
