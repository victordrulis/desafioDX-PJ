package br.com.duxusdesafio.business.validator.franquia;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Franquia;
import br.com.duxusdesafio.business.repository.franquia.FranquiaRepository;
import br.com.duxusdesafio.business.repository.integrante.IntegranteRepository;
import br.com.duxusdesafio.business.validator.api.BusinessValidator;
import br.com.duxusdesafio.business.validator.api.Validador;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FranquiaExcluirValidatorImpl extends BusinessValidator implements Validador {

    public static final String ERRO_AO_EXCLUIR = "Erro ao excluir";

    private final FranquiaRepository franquiaRepository;
    private final IntegranteRepository integranteRepository;

    @Override
    public void validar(Object object) throws BusinessException {
        isNotInstancia(Franquia.class, object);
        Franquia franquia = (Franquia) object;

        validarSeFranquiaExiste(franquia);
        validarSeExisteAlgumIntegranteRelacionado(franquia);
    }

    private void validarSeExisteAlgumIntegranteRelacionado(Franquia franquia) {
        if(integranteRepository.findByFranquiaIn(Lists.newArrayList(franquia)).stream().findAny().isPresent()) {
            throw new BusinessException(ERRO_AO_EXCLUIR + " - Existem integrantes para esta franquia.");
        }
    }

    private void validarSeFranquiaExiste(Franquia franquia) {
        if(!franquiaRepository.existsById(franquia.getId())) {
            throw new BusinessException(ERRO_AO_EXCLUIR + " - Franquia não existe.");
        }
    }
}
