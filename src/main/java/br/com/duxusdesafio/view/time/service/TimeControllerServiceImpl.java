package br.com.duxusdesafio.view.time.service;

import br.com.duxusdesafio.service.time.TimeService;
import br.com.duxusdesafio.view.api.service.ApiControllerServiceImpl;
import br.com.duxusdesafio.view.time.TimeDto;
import br.com.duxusdesafio.view.time.validator.TimeApiValidatorImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.util.Objects.isNull;

@Service
public class TimeControllerServiceImpl extends ApiControllerServiceImpl implements TimeControllerService{

    private final TimeService timeService;
    private final TimeApiValidatorImpl timeApiValidator;

    public TimeControllerServiceImpl(TimeService timeService, TimeApiValidatorImpl timeApiValidator) {
        this.timeService = timeService;
        this.timeApiValidator = timeApiValidator;
    }

    @Override
    public ResponseEntity<?> obter(Long id) {
        TimeDto timeDto = timeService.obter(id);
        return super.gerarResponse(timeDto, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> listar(LocalDate data) {
        return isNull(data) ? listar() : super.ok(timeService.obterTodosPorData(data));
    }

    @Override
    public ResponseEntity<?> listar() {
        return super.ok(timeService.obterTodos());
    }

    @Override
    public ResponseEntity<?> salvar(TimeDto timeDto) {
        timeApiValidator.validarFormulario(timeDto);
        timeService.salvar(timeDto);

        return super.ok(null);
    }

    @Override
    public ResponseEntity<?> excluir(Long id) {
        // TODO implementar
        return super.okVazio();
    }
}
