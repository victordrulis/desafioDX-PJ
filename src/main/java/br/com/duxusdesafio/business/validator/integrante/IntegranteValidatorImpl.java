package br.com.duxusdesafio.business.validator.integrante;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Franquia;
import br.com.duxusdesafio.business.validator.api.BusinessValidator;
import br.com.duxusdesafio.view.franquia.FranquiaDto;
import org.springframework.stereotype.Component;

@Component
public class IntegranteValidatorImpl extends BusinessValidator implements IntegranteValidator {
    @Override
    public void validar(Franquia franquia) throws BusinessException {

    }

    @Override
    public void validarFormulario(FranquiaDto franquiaDto) throws BusinessException {

    }
}
