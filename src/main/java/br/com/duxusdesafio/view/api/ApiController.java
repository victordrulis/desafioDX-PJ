package br.com.duxusdesafio.view.api;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Integrante;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.service.ApiService;
import br.com.duxusdesafio.service.integrante.IntegranteServiceImpl;
import br.com.duxusdesafio.service.time.TimeService;
import br.com.duxusdesafio.view.api.service.ApiControllerServiceImpl;
import br.com.duxusdesafio.view.integrante.IntegranteDto;
import br.com.duxusdesafio.view.time.TimeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    @GetMapping("/time-da-data")
    public ResponseEntity<?> timeDaData(@RequestParam(required = false)  LocalDate data) throws BusinessException {
        TimeDto timeDto = Optional.ofNullable(apiService.timeDaData(data, timeService.obterTodos()))
                .map(TimeDto::from)
                .orElseGet(TimeDto::new);

        return apiControllerService.gerarResponse(timeDto, HttpStatus.OK);
    }

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times
     * dentro do período
     */
    @GetMapping("/integrante-mais-usado")
    public ResponseEntity<?> integranteMaisUsado(@RequestParam(required = false) LocalDate dataInicial, @RequestParam(required = false) LocalDate dataFinal) {
        List<Time> timesNoPeriodo = timeService.obterTodosNoPeriodo(dataInicial, dataFinal);
        IntegranteDto integranteMaisUsado = IntegranteDto.from(Optional.ofNullable(apiService.integranteMaisUsado(dataInicial, dataFinal, timesNoPeriodo))
                .orElseGet(Integrante::new));
        return apiControllerService.gerarResponse(integranteMaisUsado, HttpStatus.OK);
    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum
     * dentro do período
     */
    @GetMapping("/integrante-do-time-mais-comum")
    public ResponseEntity<?> integrantesDoTimeMaisComum(@RequestParam(required = false) LocalDate dataInicial, @RequestParam(required = false) LocalDate dataFinal) {
        List<Time> timesNoPeriodo = timeService.obterTodosNoPeriodo(dataInicial, dataFinal);
        List<String> integrantesDoTimeMaisComum = apiService.integrantesDoTimeMaisComum(dataInicial, dataFinal, timesNoPeriodo);
        return apiControllerService.gerarResponse(integrantesDoTimeMaisComum, HttpStatus.OK);
    }

    /**
     * Vai retornar a função mais comum nos times dentro do período
     */
    @GetMapping("/funcao-mais-comum")
    public ResponseEntity<?> funcaoMaisComum(@RequestParam(required = false) LocalDate dataInicial, @RequestParam(required = false) LocalDate dataFinal){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    @GetMapping("/franquia-mais-famosa")
    public ResponseEntity<?> franquiaMaisFamosa(@RequestParam(required = false) LocalDate dataInicial, @RequestParam(required = false) LocalDate dataFinal) {
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
