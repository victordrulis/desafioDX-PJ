package br.com.duxusdesafio.service.integrante;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.ComposicaoTime;
import br.com.duxusdesafio.business.model.Integrante;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.business.repository.integrante.IntegranteRepository;
import br.com.duxusdesafio.business.validator.integrante.IntegranteValidatorImpl;
import br.com.duxusdesafio.utils.DateUtils;
import br.com.duxusdesafio.view.integrante.IntegranteDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class IntegranteServiceImpl implements IntegranteService {
    private final IntegranteRepository repository;
    private final IntegranteValidatorImpl integranteValidator;

    public IntegranteServiceImpl(IntegranteRepository repository, IntegranteValidatorImpl integranteValidator) {
        this.repository = repository;
        this.integranteValidator = integranteValidator;
    }
    
    @Override
    public void salvar(IntegranteDto integranteDto) throws BusinessException {
        repository.save(Integrante.builder()
                .id(integranteDto.getId())
                .nome(integranteDto.getNome())
                .build());
    }

    @Override
    public List<Integrante> obterTodos() throws BusinessException {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public IntegranteDto obter(Long id) throws BusinessException {
        integranteValidator.validarNulo(id);
        return getIntegrante(id)
                .map(IntegranteDto::from)
                .orElse(null);
    }

    @Override
    public void excluir(Long id) throws BusinessException {
        integranteValidator.validarNulo(id);
        Integrante integrante = getIntegrante(id).orElse(null);
        integranteValidator.validarObjetoExiste(integrante);

        repository.delete(integrante);
    }

    private Optional<Integrante> getIntegrante(Long id) {
        return repository.findById(id);
    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum dentro do período
     */
    public List<String> obterComposicaoTimeMaisComum(List<Time> times, LocalDate dataInicial, LocalDate dataFinal) {
        Optional<Set<Integrante>> composicaoTimeMaisComum = getComposicaoTimeMaisComum(times, dataInicial, dataFinal);

        return composicaoTimeMaisComum
                .map(lista -> lista.stream()
                        .map(Integrante::getNome)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    /**
     * Vai retornar o integrante que tiver presente na maior quantidade de times dentro do período
     */
    public Optional<Integrante> obterIntegranteComMaiorOcorrencia(List<Time> times, LocalDate dataInicial, LocalDate dataFinal) {

        return times.stream()
                .filter(predicatePeriodoTime(dataInicial, dataFinal))
                .flatMap(time -> time.getComposicaoTimes().stream())
                .filter(composicao -> composicao != null && composicao.getIntegrante() != null)
                .collect(Collectors.groupingBy(composicao -> composicao.getIntegrante().getId(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> getIntegrantePorId(entry.getKey(), times));
    }

    /**
     * Método auxiliar para recuperar o Integrante a partir de seu ID
     */
    private Integrante getIntegrantePorId(Long id, List<Time> times) {
        return times.stream()
                .flatMap(time -> time.getComposicaoTimes().stream())
                .map(ComposicaoTime::getIntegrante)
                .filter(integrante -> integrante.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    /**
     * Encontra na lista de times a composicao de integrantes mais comum.
     * Extrair as listas de integrantes dos times e contar quantas vezes cada lista de integrantes se repete
     */
    Optional<Set<Integrante>> getComposicaoTimeMaisComum(List<Time> times, LocalDate dataInicial, LocalDate dataFinal) {
        Map<Set<Integrante>, Long> composicoesRepetidas = times.stream()
                .filter(predicatePeriodoTime(dataInicial, dataFinal))
                .map(Time::getComposicaoTimes)
                .map(composicaoTimeSet ->
                        composicaoTimeSet.stream()
                                .map(ComposicaoTime::getIntegrante)
                                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingLong(Integrante::getId))))
                )
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        return composicoesRepetidas.entrySet().stream()
                .max(Map.Entry.comparingByValue()) // Encontra a composição com o maior número de repetições
                .map(Map.Entry::getKey);
    }

    private Predicate<Time> predicatePeriodoTime(LocalDate dataInicial, LocalDate dataFinal) {
        return time -> DateUtils.isDataNoPeriodo(time.getData(), dataInicial, dataFinal) && time.getComposicaoTimes() != null;
    }
}
