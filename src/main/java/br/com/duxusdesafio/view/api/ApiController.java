package br.com.duxusdesafio.view.api;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Integrante;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.service.ApiService;
import br.com.duxusdesafio.service.integrante.IntegranteServiceImpl;
import br.com.duxusdesafio.service.time.TimeService;
import br.com.duxusdesafio.view.api.service.ApiControllerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ApiControllerServiceImpl apiControllerService;
    private final ApiService apiService;
    private final TimeService timeService;
    private final IntegranteServiceImpl integranteService;

    public ApiController(ApiControllerServiceImpl apiControllerService, ApiService apiService, TimeService timeService, IntegranteServiceImpl integranteService) {
        this.apiControllerService = apiControllerService;
        this.apiService = apiService;
        this.timeService = timeService;
        this.integranteService = integranteService;
    }

    /**
     * Vai retornar um Time, com a composição do time daquela data
     */
    @GetMapping("/time-da-data/{data}")
    public ResponseEntity<?> timeDaData(@PathVariable LocalDate data) throws BusinessException {
        return apiControllerService.gerarResponse(apiService.timeDaData(data, timeService.obterTodos()), HttpStatus.ACCEPTED);
    }

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times
     * dentro do período
     */
    @GetMapping("/integrante-mais-usado/{dataInicial}/{dataFinal}")
    public ResponseEntity<?> integranteMaisUsado(@PathVariable LocalDate dataInicial, @PathVariable LocalDate dataFinal) {
        List<Time> timesNoPeriodo = timeService.obterTodosNoPeriodo(dataInicial, dataFinal);
        Integrante integranteMaisUsado = integranteService.obterIntegranteComMaiorOcorrencia(timesNoPeriodo, dataInicial, dataFinal).orElse(null);
        return apiControllerService.gerarResponse(integranteMaisUsado, HttpStatus.ACCEPTED);
    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum
     * dentro do período
     */
    @GetMapping("/integrante-do-time-mais-comum")
    public ResponseEntity<?> integrantesDoTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar a função mais comum nos times dentro do período
     */
    @GetMapping("/funcao-mais-comum")
    public ResponseEntity<?> funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    @GetMapping("/franquia-mais-famosa")
    public ResponseEntity<?> franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // TODO Implementar método seguindo as instruções!
        return null;
    }


    /**
     * Vai retornar o número (quantidade) de Franquias dentro do período
     */
    @GetMapping("/contagem-por-franquia")
    public ResponseEntity<?> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    @GetMapping("/contagem-por-funcao")
    public ResponseEntity<?> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }
}
