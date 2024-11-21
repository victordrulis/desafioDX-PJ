package br.com.duxusdesafio.view.time.service;

import br.com.duxusdesafio.service.time.TimeService;
import br.com.duxusdesafio.view.api.service.ApiControllerServiceAbstract;
import br.com.duxusdesafio.view.time.TimeDto;
import br.com.duxusdesafio.view.time.validator.TimeApiValidatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class TimeControllerServiceImpl extends ApiControllerServiceAbstract implements TimeControllerService{

    private static final Logger logger = LoggerFactory.getLogger(TimeControllerServiceImpl.class);

    private final TimeService timeService;
    private final TimeApiValidatorImpl timeApiValidator;

    public TimeControllerServiceImpl(TimeService timeService, TimeApiValidatorImpl timeApiValidator) {
        this.timeService = timeService;
        this.timeApiValidator = timeApiValidator;
    }

    @Override
    public ResponseEntity<?> obter(Long id) {
        try {
            TimeDto timeDto = TimeDto.from(timeService.obter(id));
            return super.gerarResponse(timeDto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            logger.error("Erro ao obter time", e);
        }

        return super.gerarResponse(null, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<?> listar(LocalDate data) {
        return isNull(data) ? listar() : super.ok(timeService.obterTodosPorData(data).stream()
                .map(TimeDto::from)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> listar() {
        return super.ok(timeService.obterTodos().stream()
                .map(TimeDto::from)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> salvar(TimeDto timeDto) {
        timeApiValidator.validarFormulario(timeDto);
        timeService.salvar(timeDto);

        return super.okVazio();
    }

    @Override
    public ResponseEntity<?> excluir(Long id) {
        timeService.excluir(id);
        return super.okVazio();
    }
}
