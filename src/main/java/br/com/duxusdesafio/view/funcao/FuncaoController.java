package br.com.duxusdesafio.view.funcao;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.view.funcao.service.FuncaoControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/funcao")
public class FuncaoController {

    private final FuncaoControllerService funcaoControllerService;

    public FuncaoController(FuncaoControllerService funcaoControllerService) {
        this.funcaoControllerService = funcaoControllerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterPorId(@PathVariable Long id) {
        return funcaoControllerService.obter(id);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return funcaoControllerService.listar();
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody FuncaoDto funcaoDto) throws BusinessException {
        return funcaoControllerService.salvar(funcaoDto);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) throws BusinessException {
        return funcaoControllerService.excluir(id);
    }

}
