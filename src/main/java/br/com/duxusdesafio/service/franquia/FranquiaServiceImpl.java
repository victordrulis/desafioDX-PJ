package br.com.duxusdesafio.service.franquia;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.ComposicaoTime;
import br.com.duxusdesafio.business.model.Franquia;
import br.com.duxusdesafio.business.model.Integrante;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.business.repository.franquia.FranquiaRepository;
import br.com.duxusdesafio.business.validator.api.ApiValidator;
import br.com.duxusdesafio.business.validator.api.ValidadorStrategy;
import br.com.duxusdesafio.business.validator.franquia.FranquiaSalvarValidatorImpl;
import br.com.duxusdesafio.service.time.TimeServiceImpl;
import br.com.duxusdesafio.utils.AcoesEnum;
import br.com.duxusdesafio.view.franquia.FranquiaDto;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FranquiaServiceImpl implements FranquiaService {
    private final Logger logger = LoggerFactory.getLogger(ApiValidator.class);

    private final ValidadorStrategy validadorStratey;
    private final FranquiaRepository repository;
    private final FranquiaSalvarValidatorImpl franquiaValidator;

    public FranquiaServiceImpl(ValidadorStrategy validadorStratey, FranquiaRepository repository, FranquiaSalvarValidatorImpl franquiaValidator) {
        this.validadorStratey = validadorStratey;
        this.repository = repository;
        this.franquiaValidator = franquiaValidator;
    }
    
    @Override
    public void salvar(FranquiaDto franquiaDto) throws BusinessException {
        repository.save(Franquia.builder()
                .id(franquiaDto.getId())
                .nome(franquiaDto.getNome())
                .build());
    }

    @Override
    public List<Franquia> obterTodos() throws BusinessException {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public FranquiaDto obter(Long id) throws BusinessException {
        franquiaValidator.validarNulo(id);
        return getFranquia(id)
                .map(FranquiaDto::from)
                .orElse(null);
    }

    @Override
    public void excluir(Long id) throws BusinessException {
        Franquia franquia = getFranquia(id).orElse(null);

        try {
            validadorStratey.getValidadores(Franquia.class)
                    .get(AcoesEnum.EXCLUIR)
                    .validar(franquia);

            repository.delete(franquia);

        } catch (BusinessException e) {
            logger.error("Erro ao validar", e);
            throw e;
        }
    }

    private Optional<Franquia> getFranquia(Long id) {
        return repository.findById(id);
    }

    public String obterFranquiaMaisFamosa(List<Time> todosOsTimes, LocalDate dataInicial, LocalDate dataFinal) {
        Optional<Franquia> franquiaMaisFamosa = obterFranquiaComMaiorOcorrenciaNoPeriodo(todosOsTimes, dataInicial, dataFinal);

        return franquiaMaisFamosa
                .map(Franquia::getNome)
                .orElse(null);
    }

    private Optional<Franquia> obterFranquiaComMaiorOcorrenciaNoPeriodo(List<Time> todosOsTimes, LocalDate dataInicial, LocalDate dataFinal) {
        Map<Franquia, Long> funcaoOcorrenciasEmTimes = getContagemPorFranquia(todosOsTimes, dataInicial, dataFinal);

        return funcaoOcorrenciasEmTimes.entrySet().stream()
                .max(Map.Entry.comparingByValue()) // Encontra a franquia com o maior número de repetições
                .map(Map.Entry::getKey);
    }

    public Map<Franquia, Long> getContagemPorFranquia(List<Time> todosOsTimes, LocalDate dataInicial, LocalDate dataFinal) {
        return TimeServiceImpl.getTimeNoPeriodo(todosOsTimes, dataInicial, dataFinal)
                .flatMap(time -> time.getComposicaoTimes().stream())
                .filter(Objects::nonNull)
                .map(ComposicaoTime::getIntegrante)
                .filter(Objects::nonNull)
                .map(Integrante::getFranquia)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
