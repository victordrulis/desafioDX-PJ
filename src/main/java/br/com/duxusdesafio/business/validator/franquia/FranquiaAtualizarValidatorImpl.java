package br.com.duxusdesafio.business.validator.franquia;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Franquia;
import br.com.duxusdesafio.business.validator.api.BusinessValidator;
import br.com.duxusdesafio.business.validator.api.Validador;
import org.springframework.stereotype.Component;

@Component
public class FranquiaAtualizarValidatorImpl extends BusinessValidator implements Validador {

    @Override
    public void validar(Object object) throws BusinessException {
        isNotInstancia(Franquia.class, object);
    }
}
