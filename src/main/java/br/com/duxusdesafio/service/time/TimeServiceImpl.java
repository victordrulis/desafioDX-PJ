package br.com.duxusdesafio.service.time;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.business.repository.time.TimeRepository;
import br.com.duxusdesafio.business.validator.time.TimeValidatorImpl;
import br.com.duxusdesafio.view.time.TimeDto;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        timeValidator.validarNulo(data);

        return obterTodos().stream()
                .filter(time -> time.getData().isEqual(data))
                .collect(Collectors.toList());
    }

    @Override
    public TimeDto obter(Long id) throws BusinessException {
        timeValidator.validarNulo(id);
        return getTime(id)
                .map(TimeDto::from)
                .orElse(null);
    }

    @Override
    public void excluir(Long id) throws BusinessException {
        timeValidator.validarNulo(id);
        Time time = getTime(id).orElse(null);
        timeValidator.validarObjetoNaoExiste(time);

        repository.delete(time);
    }

    private Optional<Time> getTime(Long id) {
        return repository.findById(id);
    }
}
