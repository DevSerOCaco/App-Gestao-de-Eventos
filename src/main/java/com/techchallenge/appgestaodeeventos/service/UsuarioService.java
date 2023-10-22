package com.techchallenge.appgestaodeeventos.service;

import com.techchallenge.appgestaodeeventos.controller.exception.ControllerNotFoundException;
import com.techchallenge.appgestaodeeventos.dto.EnderecoDTO;
import com.techchallenge.appgestaodeeventos.dto.UsuarioDTO;
import com.techchallenge.appgestaodeeventos.entities.Endereco;
import com.techchallenge.appgestaodeeventos.entities.Usuario;
import com.techchallenge.appgestaodeeventos.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Page<UsuarioDTO> findAll(Pageable pageable) {
        Page<Usuario> usuarios = repository.findAll(pageable);
        return usuarios.map(this::toUsuarioDTO);
    }

    public UsuarioDTO findById(Long idUsuario) {
        Usuario usuario = repository.findById(idUsuario).orElseThrow(() -> new ControllerNotFoundException("Usuario não encontrado"));
        return toUsuarioDTO(usuario);
    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = toUsuario(usuarioDTO);
        usuario = repository.save(usuario);
        return toUsuarioDTO(usuario);
    }

    public UsuarioDTO update(Long idUsuario, UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = repository.getReferenceById(idUsuario);
            usuario.setNome(usuarioDTO.nome());
            usuario.setEmail(usuarioDTO.email());
            usuario.setCpf(usuarioDTO.cpf());
            usuario.setDataNascimento(usuarioDTO.dataNascimento());
            usuario.setLogin(usuarioDTO.login());
            usuario.setSenha(usuarioDTO.senha());
            usuario.getEndereco().atualizarInformacoes(usuarioDTO.enderecoDTO());
            usuario = repository.save(usuario);

            return toUsuarioDTO(usuario);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuario com id:" + idUsuario + " não encontrado");
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
                usuario.getSenha(),
                new EnderecoDTO(
                        usuario.getEndereco().getLogradouro(),
                        usuario.getEndereco().getBairro(),
                        usuario.getEndereco().getCep(),
                        usuario.getEndereco().getCidade(),
                        usuario.getEndereco().getUf(),
                        usuario.getEndereco().getComplemento(),
                        usuario.getEndereco().getNumero()
                )
        );
    }

    private Usuario toUsuario(UsuarioDTO usuarioDTO) {
        return new Usuario(
                usuarioDTO.idUsuario(),
                usuarioDTO.nome(),
                usuarioDTO.email(),
                usuarioDTO.cpf(),
                usuarioDTO.dataNascimento(),
                usuarioDTO.login(),
                usuarioDTO.senha(),
                new Endereco(
                    usuarioDTO.enderecoDTO().logradouro(),
                    usuarioDTO.enderecoDTO().bairro(),
                    usuarioDTO.enderecoDTO().cep(),
                    usuarioDTO.enderecoDTO().cidade(),
                    usuarioDTO.enderecoDTO().uf(),
                    usuarioDTO.enderecoDTO().complemento(),
                    usuarioDTO.enderecoDTO().numero()
            ));
        }

}