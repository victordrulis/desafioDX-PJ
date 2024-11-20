package br.com.duxusdesafio.service.franquia;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Franquia;
import br.com.duxusdesafio.business.repository.franquia.FranquiaRepository;
import br.com.duxusdesafio.business.validator.franquia.FranquiaValidatorImpl;
import br.com.duxusdesafio.view.franquia.FranquiaDto;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FranquiaServiceImpl implements FranquiaService {
    private final FranquiaRepository repository;
    private final FranquiaValidatorImpl franquiaValidator;

    public FranquiaServiceImpl(FranquiaRepository repository, FranquiaValidatorImpl franquiaValidator) {
        this.repository = repository;
        this.franquiaValidator = franquiaValidator;
    }
    
    @Override
    public void salvar(FranquiaDto franquiaDto) throws BusinessException {
        repository.save(Franquia.builder()
                .id(franquiaDto.getId())
                .nome(franquiaDto.getNome())
                .build());
    }

    @Override
    public List<Franquia> obterTodos() throws BusinessException {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public FranquiaDto obter(Long id) throws BusinessException {
        franquiaValidator.validarNulo(id);
        return getFranquia(id)
                .map(FranquiaDto::from)
                .orElse(null);
    }

    @Override
    public void excluir(Long id) throws BusinessException {
        franquiaValidator.validarNulo(id);
        Franquia franquia = getFranquia(id).orElse(null);
        franquiaValidator.validarObjetoNaoExiste(franquia);

        repository.delete(franquia);
    }

    private Optional<Franquia> getFranquia(Long id) {
        return repository.findById(id);
    }
}
