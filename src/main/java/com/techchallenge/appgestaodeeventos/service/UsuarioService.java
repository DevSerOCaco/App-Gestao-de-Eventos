package com.techchallenge.appgestaodeeventos.service;

import com.techchallenge.appgestaodeeventos.controller.exception.ControllerNotFoundException;
import com.techchallenge.appgestaodeeventos.dto.UsuarioDTO;
import com.techchallenge.appgestaodeeventos.entities.Usuario;
import com.techchallenge.appgestaodeeventos.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Page<UsuarioDTO> findAll(Pageable pageable) {
        Page<Usuario> usuarios = repository.findAll(pageable);
        return usuarios.map(this::toUsuarioDTO);
    }

    public UsuarioDTO findById(Long idUsuario) {
        var usuario = repository.findById(idUsuario).orElseThrow(() -> new ControllerNotFoundException("Usuario com nao foi encontrado"));
        return toUsuarioDTO(usuario);
    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = toUsuario(usuarioDTO);
        usuario = repository.save(usuario);
        return toUsuarioDTO(usuario);
    }

    public UsuarioDTO update(Long idUsuario, UsuarioDTO usuarioDTO) {
        try {
            Usuario listaUsuario = repository.getReferenceById(idUsuario);
            listaUsuario.setNome(usuarioDTO.nome());
            listaUsuario.setEmail(usuarioDTO.email());
            listaUsuario.setCpf(usuarioDTO.cpf());
            listaUsuario.setDataNascimento(usuarioDTO.dataNascimento());
            listaUsuario.setLogin(usuarioDTO.login());
            listaUsuario.setSenha(usuarioDTO.senha());
            listaUsuario = repository.save(listaUsuario);
            return toUsuarioDTO(listaUsuario);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuario com id:" + idUsuario + "não encontrado");
        }
    }

    public void delete(Long idUsuario) { repository.deleteById(idUsuario); }

    private UsuarioDTO toUsuarioDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getDataNascimento(),
                usuario.getLogin(),
                usuario.getSenha()
        );
    }

    private Usuario toUsuario(UsuarioDTO usuarioDTO) {
        return new Usuario(
                usuarioDTO.idUsuario(),
                usuarioDTO.nome(),
                usuarioDTO.email(),
                usuarioDTO.cpf(),
                usuarioDTO.login(),
                usuarioDTO.senha()
        );
    }

}