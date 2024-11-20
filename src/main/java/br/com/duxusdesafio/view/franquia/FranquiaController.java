package br.com.duxusdesafio.view.franquia;

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
    public ResponseEntity<?> salvar(@RequestBody FranquiaDto franquiaDto) {
        return franquiaControllerService.salvar(franquiaDto);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        return franquiaControllerService.excluir(id);
    }

}
