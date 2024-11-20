package br.com.duxusdesafio.view.integrante;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.view.integrante.service.IntegranteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/integrante")
public class IntegranteController {

    private final IntegranteControllerService integranteControllerService;

    public IntegranteController(IntegranteControllerService integranteControllerService) {
        this.integranteControllerService = integranteControllerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterPorId(@PathVariable Long id) {
        return integranteControllerService.obter(id);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return integranteControllerService.listar();
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody IntegranteDto integranteDto) throws BusinessException {
        return integranteControllerService.salvar(integranteDto);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) throws BusinessException {
        return integranteControllerService.excluir(id);
    }
}
