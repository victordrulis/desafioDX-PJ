package br.com.duxusdesafio.service.funcao;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Funcao;
import br.com.duxusdesafio.business.repository.funcao.FuncaoRepository;
import br.com.duxusdesafio.business.validator.funcao.FuncaoValidatorImpl;
import br.com.duxusdesafio.view.funcao.FuncaoDto;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncaoServiceImpl implements FuncaoService {
    private final FuncaoRepository repository;
    private final FuncaoValidatorImpl funcaoValidator;

    public FuncaoServiceImpl(FuncaoRepository repository, FuncaoValidatorImpl funcaoValidator) {
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
}
