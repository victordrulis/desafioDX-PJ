package br.com.duxusdesafio.view.integrante.service;

import br.com.duxusdesafio.service.integrante.IntegranteService;
import br.com.duxusdesafio.view.api.service.ApiControllerServiceImpl;
import br.com.duxusdesafio.view.integrante.IntegranteDto;
import br.com.duxusdesafio.view.integrante.validator.IntegranteApiValidatorImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IntegranteControllerServiceImpl extends ApiControllerServiceImpl implements IntegranteControllerService {

    private final IntegranteService integranteService;
    private final IntegranteApiValidatorImpl integranteApiValidator;

    public IntegranteControllerServiceImpl(IntegranteService integranteService, IntegranteApiValidatorImpl integranteApiValidator) {
        this.integranteService = integranteService;
        this.integranteApiValidator = integranteApiValidator;
    }

    @Override
    public ResponseEntity<?> obter(Long id) {
        IntegranteDto integranteDto = integranteService.obter(id);
        return super.gerarResponse(integranteDto, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> listar() {
        return super.ok(integranteService.obterTodos());
    }

    @Override
    public ResponseEntity<?> salvar(IntegranteDto integranteDto) {
        integranteApiValidator.validarFormulario(integranteDto);
        integranteService.salvar(integranteDto);

        return super.ok(null);
    }

    @Override
    public ResponseEntity<?> excluir(Long id) {
        integranteService.excluir(id);
        return super.okVazio();
    }
}
