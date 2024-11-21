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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
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
    public Optional<List<String>> obterIntegrantesDoTimeMaisComumNoPeriodo(List<Time> times, LocalDate dataInicial, LocalDate dataFinal) {
        Map<Time, Set<String>> timesComIntegrantes = times.stream()
                .filter(time -> DateUtils.isDataNoPeriodo(time.getData(), dataInicial, dataFinal) && time.getComposicaoTimes() != null)
                .collect(Collectors.toMap(Function.identity(), time -> time.getComposicaoTimes().stream()
                        .map(composicao -> composicao.getIntegrante().getNome())
                        .collect(Collectors.toSet())));

        return timesComIntegrantes.entrySet().stream()
                .max(Comparator.comparingInt(entry -> entry.getValue().size()))
                .map(Map.Entry::getValue)
                .map(ArrayList::new);
    }

    /**
     * Vai retornar o integrante que tiver presente na maior quantidade de times dentro do período
     */
    public Optional<Integrante> obterIntegranteComMaiorOcorrencia(List<Time> times, LocalDate dataInicial, LocalDate dataFinal) {

        return times.stream()
                .filter(time -> DateUtils.isDataNoPeriodo(time.getData(), dataInicial, dataFinal) && time.getComposicaoTimes() != null)
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
}
