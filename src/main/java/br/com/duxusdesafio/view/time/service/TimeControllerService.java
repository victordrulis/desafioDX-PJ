package br.com.duxusdesafio.view.time.service;

import br.com.duxusdesafio.view.time.TimeDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface TimeControllerService {
    ResponseEntity<?> obter(Long id);
    ResponseEntity<?> listar(LocalDate data);
    ResponseEntity<?> listar();
    ResponseEntity<?> salvar(TimeDto timeDto);
    ResponseEntity<?> excluir(Long id);
}
