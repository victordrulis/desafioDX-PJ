package br.com.duxusdesafio.service.integrante;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Integrante;
import br.com.duxusdesafio.business.repository.integrante.IntegranteRepository;
import br.com.duxusdesafio.business.validator.integrante.IntegranteValidatorImpl;
import br.com.duxusdesafio.view.integrante.IntegranteDto;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntegranteServiceImpl implements IntegranteService {
    private final IntegranteRepository repository;
    private final IntegranteValidatorImpl integranteValidator;

    public IntegranteServiceImpl(IntegranteRepository repository, IntegranteValidatorImpl integranteValidator) {
        this.repository = repository;
        this.integranteValidator = integranteValidator;
    }
    
    @Override
    public void salvar(IntegranteDto integranteDto) throws BusinessException {
        repository.save(Integrante.builder()
                .id(integranteDto.getId())
                .nome(integranteDto.getNome())
                .build());
    }

    @Override
    public List<Integrante> obterTodos() throws BusinessException {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public IntegranteDto obter(Long id) throws BusinessException {
        integranteValidator.validarNulo(id);
        return getIntegrante(id)
                .map(IntegranteDto::from)
                .orElse(null);
    }

    @Override
    public void excluir(Long id) throws BusinessException {
        integranteValidator.validarNulo(id);
        Integrante integrante = getIntegrante(id).orElse(null);
        integranteValidator.validarObjetoNaoExiste(integrante);

        repository.delete(integrante);
    }

    private Optional<Integrante> getIntegrante(Long id) {
        return repository.findById(id);
    }
}
