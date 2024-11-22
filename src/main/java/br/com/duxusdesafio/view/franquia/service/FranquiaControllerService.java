package br.com.duxusdesafio.view.franquia.service;

import br.com.duxusdesafio.view.franquia.FranquiaDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface FranquiaControllerService {
    ResponseEntity<?> obter(Long id);
    ResponseEntity<?> listar();
    ResponseEntity<?> salvar(FranquiaDto franquiaDto);
    ResponseEntity<?> excluir(Long id);
}
