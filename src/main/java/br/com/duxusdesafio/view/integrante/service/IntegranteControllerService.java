package br.com.duxusdesafio.view.integrante.service;

import br.com.duxusdesafio.view.integrante.IntegranteDto;
import org.springframework.http.ResponseEntity;

public interface IntegranteControllerService {
    ResponseEntity<?> obter(Long id);
    ResponseEntity<?> listar();
    ResponseEntity<?> salvar(IntegranteDto integranteDto);
    ResponseEntity<?> excluir(Long id);
}
