package com.techchallenge.appgestaodeeventos.controller;

import com.techchallenge.appgestaodeeventos.dto.UsuarioDTO;
import com.techchallenge.appgestaodeeventos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    public UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> findAll(@PageableDefault(size = 10, page = 0, sort= "idUsuario")
                                                   Pageable pageable) {
        Page<UsuarioDTO> usuarios = usuarioService.findAll(pageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long idUsuario) {
        var usuario = usuarioService.findById(idUsuario);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO = usuarioService.save(usuarioDTO);
        return ResponseEntity.status(201).body(usuarioDTO);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> update(@RequestBody UsuarioDTO usuarioDTO, @PathVariable Long idUsuario) {
        usuarioDTO = usuarioService.update(idUsuario, usuarioDTO);
        return ResponseEntity.ok().body(usuarioDTO);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> delete(@RequestBody UsuarioDTO usuarioDTO, @PathVariable Long idUsuario) {
        usuarioService.delete(idUsuario);
        return ResponseEntity.noContent().build();
    }
}