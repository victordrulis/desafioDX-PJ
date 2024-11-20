package br.com.duxusdesafio.service;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Integrante;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.business.validator.api.ApiValidator;
import br.com.duxusdesafio.utils.CollectionUtils;
import br.com.duxusdesafio.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados solicitados no desafio!
 */
@Service
public class ApiService {

    private final Logger LOGGER = LoggerFactory.getLogger(ApiService.class);

    private final ApiValidator apiValidator;

    public ApiService(ApiValidator apiValidator) {
        this.apiValidator = apiValidator;
    }

    /**
     * Vai retornar um Time, com a composição do time daquela data
     */
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes) throws BusinessException {
        apiValidator.validarTimeDaData(data, todosOsTimes);
        return todosOsTimes.stream()
                .filter(time -> time.getData().equals(Optional.ofNullable(data).orElse(LocalDate.now())))
                .findFirst()
                .orElse(null);
    }

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times
     * dentro do período
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        try {
            DateUtils.validarDataNaoNula(dataInicial);
            DateUtils.validarDataNaoNula(dataFinal);
            CollectionUtils.validarListaVazia(todosOsTimes);
        } catch (BusinessException exception) {
            LOGGER.error("Argumentos inválidos na busca de time na data.", exception);
            return null;
        }

        List<Time> timesNoPeriodo = todosOsTimes.stream()
                .filter(time -> time.getData().isAfter(dataInicial))
                .filter(time -> time.getData().isBefore(dataFinal))
                .collect(Collectors.toList());



        return null;
    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum
     * dentro do período
     */
    public List<String> integrantesDoTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar a função mais comum nos times dentro do período
     */
    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // TODO Implementar método seguindo as instruções!
        return null;
    }


    /**
     * Vai retornar o número (quantidade) de Franquias dentro do período
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Cria um response com os dados obtidos
     *
     * @param dados
     * @param statusRequest
     * @return Response para o controller
     */
    public ResponseEntity<?> gerarResponse(Object dados, HttpStatus statusRequest) {
        return new ResponseEntity<>(dados, statusRequest);
    }

}
