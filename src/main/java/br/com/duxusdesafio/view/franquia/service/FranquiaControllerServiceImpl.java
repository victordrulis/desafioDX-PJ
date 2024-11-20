package br.com.duxusdesafio.view.franquia.service;

import br.com.duxusdesafio.service.franquia.FranquiaService;
import br.com.duxusdesafio.view.api.service.ApiControllerServiceImpl;
import br.com.duxusdesafio.view.franquia.FranquiaDto;
import br.com.duxusdesafio.view.franquia.validator.FranquiaApiValidatorImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        FranquiaDto franquiaDto = franquiaService.obter(id);
        return super.gerarResponse(franquiaDto, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> listar() {
        return super.ok(franquiaService.obterTodos());
    }

    @Override
    public ResponseEntity<?> salvar(FranquiaDto franquiaDto) {
        franquiaApiValidator.validarFormulario(franquiaDto);
        franquiaService.salvar(franquiaDto);

        return super.ok(null);
    }

    @Override
    public ResponseEntity<?> excluir(Long id) {
        franquiaService.excluir(id);
        return super.okVazio();
    }
}
