package br.com.duxusdesafio.view.time;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.view.time.service.TimeControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/time")
public class TimeController {

    private final TimeControllerService timeControllerService;

    public TimeController(TimeControllerService timeControllerService) {
        this.timeControllerService = timeControllerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterPorId(@PathVariable Long id) {
        return timeControllerService.obter(id);
    }

    @GetMapping({"/listar", "/listar/{data}"})
    public ResponseEntity<?> listar(@PathVariable(required = false) LocalDate data) {
        return timeControllerService.listar(data);
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody TimeDto timeDto) throws BusinessException {
        return timeControllerService.salvar(timeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) throws BusinessException {
        return timeControllerService.excluir(id);
    }
}
