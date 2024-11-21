package br.com.duxusdesafio.service.integrante;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.ComposicaoTime;
import br.com.duxusdesafio.business.model.Integrante;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.business.repository.integrante.IntegranteRepository;
import br.com.duxusdesafio.business.validator.integrante.IntegranteValidatorImpl;
import br.com.duxusdesafio.utils.DateUtils;
import br.com.duxusdesafio.view.integrante.IntegranteDto;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    /**
     * Encontra o integrante com maior ocorrência, considerando o periodo,
     * através da coleta todas as composições de todos os times em uma lista
     */
    public Optional<Integrante> obterIntegranteComMaiorOcorrencia(List<Time> times, LocalDate dataInicial, LocalDate dataFinal) {

        return times.stream()
                .filter(time -> DateUtils.isDataNoPeriodo(time.getData(), dataInicial, dataFinal) && time.getComposicaoTime() != null)
                .flatMap(time -> time.getComposicaoTime().stream())
                .filter(composicao -> composicao != null && composicao.getIntegrante() != null)
                .collect(Collectors.groupingBy(composicao -> composicao.getIntegrante().getId(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> getIntegrantePorId(entry.getKey(), times));
    }

    /**
     * Método auxiliar para recuperar o Integrante a partir de seu ID
     */
    private Integrante getIntegrantePorId(Long id, List<Time> times) {
        return times.stream()
                .flatMap(time -> time.getComposicaoTime().stream())
                .map(ComposicaoTime::getIntegrante)
                .filter(integrante -> integrante.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
