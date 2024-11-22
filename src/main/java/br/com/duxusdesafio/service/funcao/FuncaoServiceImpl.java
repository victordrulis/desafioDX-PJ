package br.com.duxusdesafio.service.funcao;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.ComposicaoTime;
import br.com.duxusdesafio.business.model.Funcao;
import br.com.duxusdesafio.business.model.Integrante;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.business.repository.funcao.FuncaoRepository;
import br.com.duxusdesafio.business.validator.funcao.FuncaoSalvarValidatorImpl;
import br.com.duxusdesafio.service.time.TimeServiceImpl;
import br.com.duxusdesafio.view.funcao.FuncaoDto;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FuncaoServiceImpl implements FuncaoService {
    private final FuncaoRepository repository;
    private final FuncaoSalvarValidatorImpl funcaoValidator;

    public FuncaoServiceImpl(FuncaoRepository repository, FuncaoSalvarValidatorImpl funcaoValidator) {
        this.repository = repository;
        this.funcaoValidator = funcaoValidator;
    }
    
    @Override
    public void salvar(FuncaoDto funcaoDto) throws BusinessException {
        repository.save(Funcao.builder()
                .id(funcaoDto.getId())
                .nome(funcaoDto.getNome())
                .build());
    }

    @Override
    public List<Funcao> obterTodos() throws BusinessException {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public FuncaoDto obter(Long id) throws BusinessException {
        funcaoValidator.validarNulo(id);
        return getFuncao(id)
                .map(FuncaoDto::from)
                .orElse(null);
    }

    @Override
    public void excluir(Long id) throws BusinessException {
        funcaoValidator.validarNulo(id);
        Funcao funcao = getFuncao(id).orElse(null);
        funcaoValidator.validarObjetoExiste(funcao);

        repository.delete(funcao);
    }

    private Optional<Funcao> getFuncao(Long id) {
        return repository.findById(id);
    }

    public String obterfuncaoMaisComum(List<Time> todosOsTimes, LocalDate dataInicial, LocalDate dataFinal) {
        Optional<Funcao> funcaoMaisComum = obterFuncaoComMaiorOcorrenciaNoPeriodo(todosOsTimes, dataInicial, dataFinal);

        return funcaoMaisComum
                .map(Funcao::getNome)
                .orElse(null);
    }

    private Optional<Funcao> obterFuncaoComMaiorOcorrenciaNoPeriodo(List<Time> todosOsTimes, LocalDate dataInicial, LocalDate dataFinal) {
        Map<Funcao, Long> funcaoOcorrenciasEmTimes = obterContagemDeFuncaoUtilizadasNoPeriodo(todosOsTimes, dataInicial, dataFinal);

        return funcaoOcorrenciasEmTimes.entrySet().stream()
                .max(Map.Entry.comparingByValue()) // Encontra a funcao com o maior número de repetições
                .map(Map.Entry::getKey);
    }

    public Map<Funcao, Long> obterContagemDeFuncaoUtilizadasNoPeriodo(List<Time> todosOsTimes, LocalDate dataInicial, LocalDate dataFinal) {
        return TimeServiceImpl.getTimeNoPeriodo(todosOsTimes, dataInicial, dataFinal)
                .flatMap(time -> time.getComposicaoTimes().stream())
                .filter(Objects::nonNull)
                .map(ComposicaoTime::getIntegrante)
                .filter(Objects::nonNull)
                .map(Integrante::getFuncao)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

}
