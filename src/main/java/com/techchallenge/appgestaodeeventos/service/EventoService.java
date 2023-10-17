package com.techchallenge.appgestaodeeventos.service;

import com.techchallenge.appgestaodeeventos.controller.exception.ControllerNotFoundException;
import com.techchallenge.appgestaodeeventos.dto.EnderecoDTO;
import com.techchallenge.appgestaodeeventos.dto.EventoDTO;
import com.techchallenge.appgestaodeeventos.entities.Endereco;
import com.techchallenge.appgestaodeeventos.entities.Evento;
import com.techchallenge.appgestaodeeventos.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Page<EventoDTO> findAll(Pageable pageable) {
        Page<Evento> eventos = eventoRepository.findAll(pageable);
        return eventos.map(this::toDTO);
    }

    public EventoDTO findById(Long id){
        Evento evento = eventoRepository.findById(id).
                orElseThrow(() -> new ControllerNotFoundException("Usuário não encontrado com o ID: " + id));
        return toDTO(evento);
    }

    public EventoDTO save(EventoDTO eventoDTO){
        Evento evento = toEntity(eventoDTO);
        evento = eventoRepository.save(evento);
        return toDTO(evento);
    }

    public EventoDTO update(Long id, EventoDTO eventoDTO){
        try {
            Evento evento = eventoRepository.getReferenceById(id);
            evento.setDescricao(eventoDTO.descricao());
            evento.setLotacao(eventoDTO.lotacao());
            evento.setDataInicio(eventoDTO.dataInicio());
            evento.setDataTermino(eventoDTO.dataTermino());
            evento.setEventoAberto(eventoDTO.eventoAberto());
            evento.getEndereco().atualizarInformacoes(eventoDTO.enderecoDTO());

            evento = eventoRepository.save(evento);
            return toDTO(evento);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuário não encontrado com o ID: " + id);
        }
    }

    public void delete(Long id){
        eventoRepository.deleteById(id);
    }
    private Evento toEntity(EventoDTO eventoDTO){
        return new Evento(eventoDTO.descricao(),
                eventoDTO.lotacao(),
                eventoDTO.dataInicio(),
                eventoDTO.dataTermino(),
                eventoDTO.eventoAberto(),
                new Endereco(
                        eventoDTO.enderecoDTO().logradouro(),
                        eventoDTO.enderecoDTO().bairro(),
                        eventoDTO.enderecoDTO().cep(),
                        eventoDTO.enderecoDTO().cidade(),
                        eventoDTO.enderecoDTO().uf(),
                        eventoDTO.enderecoDTO().complemento(),
                        eventoDTO.enderecoDTO().numero()
                ));
    }
    private EventoDTO toDTO(Evento evento){
        return new EventoDTO(
                evento.getDescricao(),
                evento.getLotacao(),
                evento.getDataInicio(),
                evento.getDataTermino(),
                evento.getEventoAberto(),
                new EnderecoDTO(
                        evento.getEndereco().getLogradouro(),
                        evento.getEndereco().getBairro(),
                        evento.getEndereco().getCep(),
                        evento.getEndereco().getCidade(),
                        evento.getEndereco().getUf(),
                        evento.getEndereco().getComplemento(),
                        evento.getEndereco().getNumero()
                ));
    }
}
