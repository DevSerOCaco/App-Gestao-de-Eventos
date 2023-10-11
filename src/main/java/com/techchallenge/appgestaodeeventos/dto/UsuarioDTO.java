package com.techchallenge.appgestaodeeventos.dto;

import java.util.Date;

public record UsuarioDTO (
        Long idUsuario,
        String nome,
        String email,
        String cpf,

        Date dataNascimento,
        String login,
        String senha
){ }
