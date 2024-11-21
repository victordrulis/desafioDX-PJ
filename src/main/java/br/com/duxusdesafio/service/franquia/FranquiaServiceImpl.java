package br.com.duxusdesafio.service.franquia;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.*;
import br.com.duxusdesafio.business.repository.franquia.FranquiaRepository;
import br.com.duxusdesafio.business.validator.franquia.FranquiaValidatorImpl;
import br.com.duxusdesafio.service.time.TimeServiceImpl;
import br.com.duxusdesafio.view.franquia.FranquiaDto;
import com.google.common.collect.Lists;
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
    private final FranquiaRepository repository;
    private final FranquiaValidatorImpl franquiaValidator;

    public FranquiaServiceImpl(FranquiaRepository repository, FranquiaValidatorImpl franquiaValidator) {
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
        franquiaValidator.validarNulo(id);
        Franquia franquia = getFranquia(id).orElse(null);
        franquiaValidator.validarObjetoExiste(franquia);

        repository.delete(franquia);
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
        Map<Franquia, Long> funcaoOcorrenciasEmTimes = TimeServiceImpl.getTimeNoPeriodo(todosOsTimes, dataInicial, dataFinal)
                .flatMap(time -> time.getComposicaoTimes().stream())
                .filter(Objects::nonNull)
                .map(ComposicaoTime::getIntegrante)
                .filter(Objects::nonNull)
                .map(Integrante::getFranquia)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // Conta quantas vezes cada função aparece

        return funcaoOcorrenciasEmTimes.entrySet().stream()
                .max(Map.Entry.comparingByValue()) // Encontra a franquia com o maior número de repetições
                .map(Map.Entry::getKey);
    }
}
