package br.com.duxusdesafio.view.franquia;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.view.franquia.service.FranquiaControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/franquia")
public class FranquiaController {

    private final FranquiaControllerService franquiaControllerService;

    public FranquiaController(FranquiaControllerService franquiaControllerService) {
        this.franquiaControllerService = franquiaControllerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterPorId(@PathVariable Long id) {
        return franquiaControllerService.obter(id);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return franquiaControllerService.listar();
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody FranquiaDto franquiaDto) throws BusinessException {
        return franquiaControllerService.salvar(franquiaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) throws BusinessException {
        return franquiaControllerService.excluir(id);
    }

}
