package br.com.duxusdesafio.view.funcao.service;

import br.com.duxusdesafio.view.funcao.FuncaoDto;
import org.springframework.http.ResponseEntity;

public interface FuncaoControllerService {
    ResponseEntity<?> obter(Long id);
    ResponseEntity<?> listar();
    ResponseEntity<?> salvar(FuncaoDto funcaoDto);
    ResponseEntity<?> excluir(Long id);
}
