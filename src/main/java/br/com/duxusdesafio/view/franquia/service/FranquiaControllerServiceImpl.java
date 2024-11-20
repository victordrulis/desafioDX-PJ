package br.com.duxusdesafio.view.franquia.service;

import br.com.duxusdesafio.service.franquia.FranquiaService;
import br.com.duxusdesafio.view.api.service.ApiControllerServiceImpl;
import br.com.duxusdesafio.view.franquia.FranquiaDto;
import br.com.duxusdesafio.view.franquia.validator.FranquiaApiValidatorImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FranquiaControllerServiceImpl extends ApiControllerServiceImpl implements FranquiaControllerService {

    private final FranquiaService franquiaService;
    private final FranquiaApiValidatorImpl franquiaApiValidator;

    public FranquiaControllerServiceImpl(FranquiaService franquiaService, FranquiaApiValidatorImpl franquiaApiValidator) {
        this.franquiaService = franquiaService;
        this.franquiaApiValidator = franquiaApiValidator;
    }

    @Override
    public ResponseEntity<?> obter(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> listar(LocalDate data) {
        return super.ok(franquiaService.obterTodos());
    }

    @Override
    public ResponseEntity<?> listar() {
        return null;
    }

    @Override
    public ResponseEntity<?> salvar(FranquiaDto franquiaDto) {
        return null;
    }

    @Override
    public ResponseEntity<?> excluir(Long id) {
        return null;
    }
}
