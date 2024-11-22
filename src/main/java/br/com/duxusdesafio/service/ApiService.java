package br.com.duxusdesafio.service;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.exception.TecException;
import br.com.duxusdesafio.business.model.Integrante;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.business.validator.api.ApiValidator;
import br.com.duxusdesafio.service.franquia.FranquiaServiceImpl;
import br.com.duxusdesafio.service.funcao.FuncaoServiceImpl;
import br.com.duxusdesafio.service.integrante.IntegranteServiceImpl;
import br.com.duxusdesafio.utils.CollectionUtils;
import br.com.duxusdesafio.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados solicitados no desafio!
 */
@Service
public class ApiService {

    public static final String ARGUMENTOS_INVALIDOS_NA_BUSCA_DE_INTEGRANTE_MAIS_USADO_NO_PERIODO = "Argumentos inválidos na busca de integrante mais usado no periodo.";
    private final Logger LOGGER = LoggerFactory.getLogger(ApiService.class);

    private final ApiValidator apiValidator;
    private final IntegranteServiceImpl integranteService;
    private final FuncaoServiceImpl funcaoService;
    private final FranquiaServiceImpl franquiaService;

    public ApiService(ApiValidator apiValidator, IntegranteServiceImpl integranteService, FuncaoServiceImpl funcaoService, FranquiaServiceImpl franquiaService) {
        this.apiValidator = apiValidator;
        this.integranteService = integranteService;
        this.funcaoService = funcaoService;
        this.franquiaService = franquiaService;
    }

    /**
     * Vai retornar um Time, com a composição do time daquela data
     */
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes) throws BusinessException {
        apiValidator.validarTimeDaData(data, todosOsTimes);
        return todosOsTimes.stream()
                .filter(time -> time.getData().equals(data))
                .findFirst()
                .orElse(null);
    }

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times dentro do período
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        try {
            DateUtils.validarDataNaoNula(dataInicial);
            DateUtils.validarDataInicialAposDataFinal(dataInicial, dataFinal);
            CollectionUtils.validarListaVazia(todosOsTimes);
        } catch (BusinessException exception) {
            LOGGER.error(ARGUMENTOS_INVALIDOS_NA_BUSCA_DE_INTEGRANTE_MAIS_USADO_NO_PERIODO, exception);
            return null;
        }

        return integranteService.obterIntegranteComMaiorOcorrencia(todosOsTimes, dataInicial, dataFinal).orElse(null);
    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum
     * dentro do período
     */
    public List<String> integrantesDoTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        try {
            DateUtils.validarDataNaoNula(dataInicial);
            DateUtils.validarDataInicialAposDataFinal(dataInicial, dataFinal);
            CollectionUtils.validarListaVazia(todosOsTimes);
        } catch (BusinessException exception) {
            LOGGER.error(ARGUMENTOS_INVALIDOS_NA_BUSCA_DE_INTEGRANTE_MAIS_USADO_NO_PERIODO, exception);
            return null;
        }

        return integranteService.obterComposicaoTimeMaisComum(todosOsTimes, dataInicial, dataFinal);
    }

    /**
     * Vai retornar a função mais comum nos times dentro do período
     */
    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        try {
            DateUtils.validarDataNaoNula(dataInicial);
            DateUtils.validarDataInicialAposDataFinal(dataInicial, dataFinal);
            CollectionUtils.validarListaVazia(todosOsTimes);
        } catch (BusinessException exception) {
            LOGGER.error(ARGUMENTOS_INVALIDOS_NA_BUSCA_DE_INTEGRANTE_MAIS_USADO_NO_PERIODO, exception);
            return null;
        }

        return funcaoService.obterfuncaoMaisComum(todosOsTimes, dataInicial, dataFinal);
    }

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        try {
            DateUtils.validarDataNaoNula(dataInicial);
            DateUtils.validarDataInicialAposDataFinal(dataInicial, dataFinal);
            CollectionUtils.validarListaVazia(todosOsTimes);
        } catch (BusinessException exception) {
            LOGGER.error(ARGUMENTOS_INVALIDOS_NA_BUSCA_DE_INTEGRANTE_MAIS_USADO_NO_PERIODO, exception);
            return null;
        }

        return franquiaService.obterFranquiaMaisFamosa(todosOsTimes, dataInicial, dataFinal);
    }

    /**
     * Vai retornar o número (quantidade) de Franquias dentro do período
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        try {
            DateUtils.validarDataNaoNula(dataInicial);
            DateUtils.validarDataInicialAposDataFinal(dataInicial, dataFinal);
            CollectionUtils.validarListaVazia(todosOsTimes);
        } catch (BusinessException exception) {
            LOGGER.error(ARGUMENTOS_INVALIDOS_NA_BUSCA_DE_INTEGRANTE_MAIS_USADO_NO_PERIODO, exception);
            return null;
        }

        return franquiaService.getContagemPorFranquia(todosOsTimes, dataInicial, dataFinal).entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getNome(),
                        Map.Entry::getValue,
                        Long::sum
                ));
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        try {
            DateUtils.validarDataNaoNula(dataInicial);
            DateUtils.validarDataInicialAposDataFinal(dataInicial, dataFinal);
            CollectionUtils.validarListaVazia(todosOsTimes);
        } catch (BusinessException exception) {
            LOGGER.error(ARGUMENTOS_INVALIDOS_NA_BUSCA_DE_INTEGRANTE_MAIS_USADO_NO_PERIODO, exception);
            return null;
        }

        return funcaoService.obterContagemDeFuncaoUtilizadasNoPeriodo(todosOsTimes, dataInicial, dataFinal).entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getNome(),
                        Map.Entry::getValue,
                        Long::sum
                ));
    }

}
