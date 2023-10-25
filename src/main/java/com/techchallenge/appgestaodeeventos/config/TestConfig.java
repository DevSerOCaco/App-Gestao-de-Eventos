package com.techchallenge.appgestaodeeventos.config;

import com.techchallenge.appgestaodeeventos.entities.Endereco;
import com.techchallenge.appgestaodeeventos.entities.Evento;
import com.techchallenge.appgestaodeeventos.entities.Usuario;
import com.techchallenge.appgestaodeeventos.repository.EventoRepository;
import com.techchallenge.appgestaodeeventos.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    public void populateDatabase() {
        // Populando Eventos
        for (int i = 1; i <= 5; i++) {
            Evento evento = new Evento(
                    "Evento " + i,
                    100 + i,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(7),
                    true,
                    new Endereco("Rua " + i, "Bairro " + i, "CEP" + i, "Cidade " + i, "UF " + i, "Complemento " + i, "Número " + i)
            );

            eventoRepository.save(evento);
        }

        // Populando Usuários
        for (int i = 1; i <= 5; i++) {
            Usuario usuario = new Usuario(
                    (long) i,
                    "Usuário " + i,
                    "usuario" + i + "@example.com",
                    "1234567890" + i,
                    LocalDate.of(1990, 1, 1).plusYears(i),
                    "user" + i,
                    "password" + i,
                    new Endereco("Rua " + i, "Bairro " + i, "CEP" + i, "Cidade " + i, "UF " + i, "Complemento " + i, "Número " + i)
            );

            usuarioRepository.save(usuario);
        }
    }
}