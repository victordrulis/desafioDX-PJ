package br.com.duxusdesafio.view.funcao.service;

import br.com.duxusdesafio.service.funcao.FuncaoService;
import br.com.duxusdesafio.view.api.service.ApiControllerServiceAbstract;
import br.com.duxusdesafio.view.funcao.FuncaoDto;
import br.com.duxusdesafio.view.funcao.validator.FuncaoApiValidatorImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FuncaoControllerServiceImpl extends ApiControllerServiceAbstract implements FuncaoControllerService {
    
    private final FuncaoService funcaoService;
    private final FuncaoApiValidatorImpl funcaoApiValidator;

    public FuncaoControllerServiceImpl(FuncaoService funcaoService, FuncaoApiValidatorImpl funcaoApiValidator) {
        this.funcaoService = funcaoService;
        this.funcaoApiValidator = funcaoApiValidator;
    }

    @Override
    public ResponseEntity<?> obter(Long id) {
        FuncaoDto funcaoDto = funcaoService.obter(id);
        return super.gerarResponse(funcaoDto, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> listar() {
        return super.ok(funcaoService.obterTodos());
    }

    @Override
    public ResponseEntity<?> salvar(FuncaoDto funcaoDto) {
        funcaoApiValidator.validarFormulario(funcaoDto);
        funcaoService.salvar(funcaoDto);

        return super.ok(null);
    }

    @Override
    public ResponseEntity<?> excluir(Long id) {
        funcaoService.excluir(id);
        return super.okVazio();
    }
}
