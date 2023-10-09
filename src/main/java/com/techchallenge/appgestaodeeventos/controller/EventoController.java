package com.techchallenge.appgestaodeeventos.controller;

import com.techchallenge.appgestaodeeventos.dto.EventoDTO;
import com.techchallenge.appgestaodeeventos.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public ResponseEntity<Page<EventoDTO>> findAll(@PageableDefault(size = 10, page = 0, sort= "id")
                                                       Pageable pageable) {
        Page<EventoDTO> eventos = eventoService.findAll(pageable);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> findById(@PathVariable Long id) {
        EventoDTO eventoDTO = eventoService.findById(id);
        return ResponseEntity.ok(eventoDTO);
    }

    @PostMapping
    public ResponseEntity<EventoDTO> save(@RequestBody EventoDTO eventoDTO){
        EventoDTO savedEvento = eventoService.save(eventoDTO);
        return new ResponseEntity<>(savedEvento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> update(@PathVariable Long id, @RequestBody EventoDTO eventoDTO) {
        EventoDTO updatedEvento = eventoService.update(id, eventoDTO);
        return ResponseEntity.ok(updatedEvento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
