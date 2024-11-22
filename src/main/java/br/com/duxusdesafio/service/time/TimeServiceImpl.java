package br.com.duxusdesafio.service.time;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.business.repository.time.TimeRepository;
import br.com.duxusdesafio.business.validator.api.BusinessValidator;
import br.com.duxusdesafio.business.validator.time.TimeValidatorImpl;
import br.com.duxusdesafio.utils.DateUtils;
import br.com.duxusdesafio.view.time.TimeDto;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TimeServiceImpl implements TimeService {

    private final TimeRepository repository;
    private final TimeValidatorImpl timeValidator;

    public TimeServiceImpl(TimeRepository repository, TimeValidatorImpl timeValidator) {
        this.repository = repository;
        this.timeValidator = timeValidator;
    }

    @Override
    public void salvar(TimeDto timeDto) throws BusinessException {
        repository.save(Time.builder()
                .id(timeDto.getId())
                .descricao(timeDto.getDescricao())
                .data(timeDto.getData())
                .build());
    }

    @Override
    public List<Time> obterTodos() throws BusinessException {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public List<Time> obterTodosPorData(LocalDate data) throws BusinessException {
        BusinessValidator.validarNulo(data);

        return obterTodos().stream()
                .filter(time -> time.getData().isEqual(data))
                .collect(Collectors.toList());
    }

    @Override
    public List<Time> obterTodosNoPeriodo(LocalDate dataInicial, LocalDate dataFinal) throws BusinessException {
        DateUtils.validarDataInicialAposDataFinal(dataInicial, dataFinal);

        return obterTodos().stream()
                .filter(time -> DateUtils.isDataNoPeriodo(time.getData(), dataInicial, dataFinal))
                .collect(Collectors.toList());
    }

    @Override
    public Time obter(Long id) throws BusinessException {
        BusinessValidator.validarNulo(id);
        return getTime(id).orElse(null);
    }

    @Override
    public void excluir(Long id) throws BusinessException {
        BusinessValidator.validarNulo(id);
        Time time = getTime(id).orElse(null);
        BusinessValidator.validarObjetoExiste(time);

        repository.delete(time);
    }

    private Optional<Time> getTime(Long id) {
        return repository.findById(id);
    }

    public static Stream<Time> getTimeNoPeriodo(List<Time> times, LocalDate dataInicial, LocalDate dataFinal) {
        return times.stream()
                .filter(predicatePeriodoTime(dataInicial, dataFinal));
    }

    private static Predicate<Time> predicatePeriodoTime(LocalDate dataInicial, LocalDate dataFinal) {
        return time -> DateUtils.isDataNoPeriodo(time.getData(), dataInicial, dataFinal) && time.getComposicaoTimes() != null;
    }
}
